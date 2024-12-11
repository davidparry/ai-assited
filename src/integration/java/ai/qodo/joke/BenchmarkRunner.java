package ai.qodo.joke;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Warmup(iterations = 10, time = 5)
@Measurement(iterations = 10, time = 5)
@Fork(value = 5, jvmArgsAppend = {"-Djmh.ignoreLock=true", "-XX:+UseG1GC", "-Xms2g", "-Xmx2g", "-XX:+UseCompressedOops", "-XX" + ":+DisableExplicitGC", "-XX:+AlwaysPreTouch", "-XX:+UseStringDeduplication", "-XX:G1HeapRegionSize=32m", "-XX:+TieredCompilation", "-XX:+UseNUMA"})
public class BenchmarkRunner {
    public static String WORDS;

    static {
        StringBuilder builder = new StringBuilder();
        try (InputStream inputStream = BenchmarkRunner.class.getResourceAsStream("/words.txt"); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        WORDS = builder.toString();
    }

    Scrubber logCleaner = new LogCleaner();
    Scrubber dataSanitizer = new DataSanitizer();

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder().include(BenchmarkRunner.class.getSimpleName()).result("build/benchmark-results.scsv") // Output file
                .resultFormat(ResultFormatType.SCSV).jvmArgs("-server")// Use SCSV format
                .forks(1).build();

        Collection<RunResult> results = new Runner(opt).run();

        // Analyze results and calculate performance difference
        double logCleaner = 0;
        double dataSanitizer = 0;
        for (RunResult result : results) {
            String benchmarkName = result.getParams().getBenchmark();
            double score = result.getPrimaryResult().getScore();
            double error = result.getPrimaryResult().getStatistics().getMeanErrorAt(0.99); // 99% confidence interval
            double errorRatio = (error / score) * 100;

            if (benchmarkName.endsWith("logCleaner")) {
                logCleaner = score;
            } else if (benchmarkName.endsWith("dataSanitizer")) {
                dataSanitizer = score;
            }

            System.out.printf("%s:%n", benchmarkName);
            System.out.printf("  Error Ratio: %.2f%%%n", errorRatio);
            if (errorRatio >= 6.0) {
                System.out.printf("  WARNING: Error ratio of %.2f%% is questionable (>5%%)%n", errorRatio);
            }
        }


        if (logCleaner > 0 && dataSanitizer > 0) {
            if (logCleaner < dataSanitizer) {
                double slowerPercentage = ((dataSanitizer - logCleaner) / logCleaner) * 100;
                System.out.printf("logCleaner is %.2f%% faster than dataSanitizer.%n", slowerPercentage);
            } else if (logCleaner > dataSanitizer) {
                double slowerPercentage = ((logCleaner - dataSanitizer) / dataSanitizer) * 100;
                System.out.printf("dataSanitizer is %.2f%% faster than logCleaner.%n", slowerPercentage);
            } else {
                System.out.println("Both scrubbers have the same performance.");
            }
        } else {
            System.out.println("Benchmark results could not be calculated.");
        }


    }

    @Setup(Level.Trial)             // Changed to Trial level
    public void setup() {
        // Pre-initialize scrubbers
        logCleaner.scrub("test");    // Warm up the scrubbers
        dataSanitizer.scrub("test");
    }

    @Benchmark
    public void logCleaner() {
        logCleaner.scrub(BenchmarkRunner.WORDS);
    }

    @Benchmark
    public void dataSanitizer() {
        dataSanitizer.scrub(BenchmarkRunner.WORDS);
    }


}
