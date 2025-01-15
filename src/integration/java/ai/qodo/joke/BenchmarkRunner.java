package ai.qodo.joke;

import static ai.qodo.joke.BenchmarkResultsPrinter.printBenchmarkReport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Warmup(iterations = 10, time = 5)
@Measurement(iterations = 10, time = 5)
@Fork(value = 5, jvmArgsAppend = {"-Djmh.ignoreLock=true", "-XX:+UseG1GC", "-Xms2g", "-Xmx2g", "-XX:+UseCompressedOops",
    "-XX" + ":+DisableExplicitGC", "-XX:+AlwaysPreTouch", "-XX:+UseStringDeduplication", "-XX:G1HeapRegionSize=32m",
    "-XX:+TieredCompilation", "-XX:+UseNUMA"})
public class BenchmarkRunner {
  Scrubber logCleaner = new LogCleaner();
  Scrubber dataSanitizer = new DataSanitizer();
  private String data;
  public static void main(String[] args) throws Exception {
    BenchmarkRunner runner = new BenchmarkRunner();
    BenchmarkResult results = runner.runBenchMark();
    printBenchmarkReport(results);
  }


  public BenchmarkResult runBenchMark() throws Exception {
    Options opt = new OptionsBuilder().include(BenchmarkRunner.class.getSimpleName())
        .result("build/benchmark-results.scsv") // Output file
        .resultFormat(ResultFormatType.SCSV).jvmArgs("-server")// Use SCSV format
        .forks(1).build();

    Collection<RunResult> results = new Runner(opt).run();
    var metrics = new HashMap<String, BenchmarkMetrics>();
    var scores = results.stream().collect(Collectors.toMap(result -> result.getParams().getBenchmark(), result -> {
      double score = result.getPrimaryResult().getScore();
      double error = result.getPrimaryResult().getStatistics().getMeanErrorAt(0.99);
      double errorRatio = (error / score) * 100;
      metrics.put(result.getParams().getBenchmark(), new BenchmarkMetrics(score, errorRatio, errorRatio >= 6.0));
      return score;
    }));

    double logCleanerScore = scores.getOrDefault("ai.qodo.joke.BenchmarkRunner.logCleaner", 0.0);
    double dataSanitizerScore = scores.getOrDefault("ai.qodo.joke.BenchmarkRunner.dataSanitizer", 0.0);

    return switch (Double.compare(logCleanerScore, dataSanitizerScore)) {
      case -1 -> new BenchmarkResult(
          "ai.qodo.joke.BenchmarkRunner.logCleaner",  // Lower score is faster
          ((dataSanitizerScore - logCleanerScore) / dataSanitizerScore) * 100,
          metrics
      );
      case 1 -> new BenchmarkResult(
          "ai.qodo.joke.BenchmarkRunner.dataSanitizer",  // Lower score is faster
          ((logCleanerScore - dataSanitizerScore) / logCleanerScore) * 100,
          metrics
      );
      default -> new BenchmarkResult("equal", 0.0, metrics);
    };

  }

  @Setup(Level.Trial)
  public void setup() throws IOException {
    data = Files.readString(Paths.get("src/integration/resources/word_test.txt"));
    logCleaner.scrub("warmup");
    dataSanitizer.scrub("bad words");
  }

  @Benchmark
  public void logCleaner() {
    logCleaner.scrub(data);
  }

  @Benchmark
  public void dataSanitizer() {
    dataSanitizer.scrub(data);
  }

}
