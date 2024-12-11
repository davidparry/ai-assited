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
@Fork(value = 5, jvmArgsAppend = {"-Djmh.ignoreLock=true", "-XX:+UseG1GC", "-Xms2g", "-Xmx2g", "-XX:+UseCompressedOops", "-XX" +
        ":+DisableExplicitGC", "-XX:+AlwaysPreTouch", "-XX:+UseStringDeduplication", "-XX:G1HeapRegionSize=32m", "-XX:+TieredCompilation", "-XX:+UseNUMA"})
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

    Scrubber iScrubber = new IScrubber();
    Scrubber dScrubber = new DScrubber();

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder().include(BenchmarkRunner.class.getSimpleName()).result("build/benchmark-results.scsv") // Output file
                .resultFormat(ResultFormatType.SCSV).jvmArgs("-server")// Use SCSV format
                .forks(1).build();

        Collection<RunResult> results = new Runner(opt).run();

        // Analyze results and calculate performance difference
        double declarativeScore = 0;
        double imperativeScore = 0;

        for (RunResult result : results) {
            String benchmarkName = result.getParams().getBenchmark();
            double score = result.getPrimaryResult().getScore();

            if (benchmarkName.endsWith("declarative")) {
                declarativeScore = score;
            } else if (benchmarkName.endsWith("imperative")) {
                imperativeScore = score;
            }
        }

        if (declarativeScore > 0 && imperativeScore > 0) {
            if (declarativeScore < imperativeScore) {
                double slowerPercentage = ((imperativeScore - declarativeScore) / declarativeScore) * 100;
                System.out.printf("DeclarativeScrubber is %.2f%% faster than ImperativeScrubber.%n", slowerPercentage);
            } else if (declarativeScore > imperativeScore) {
                double slowerPercentage = ((declarativeScore - imperativeScore) / imperativeScore) * 100;
                System.out.printf("ImperativeScrubber is %.2f%% faster than DeclarativeScrubber.%n", slowerPercentage);
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
        iScrubber.scrub("test");    // Warm up the scrubbers
        dScrubber.scrub("test");
    }

    @Benchmark
    public void imperative() {
        iScrubber.scrub(BenchmarkRunner.WORDS);
    }

    @Benchmark
    public void declarative() {
        dScrubber.scrub(BenchmarkRunner.WORDS);
    }


}
