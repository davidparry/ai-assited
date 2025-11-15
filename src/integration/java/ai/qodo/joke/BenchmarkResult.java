package ai.qodo.joke;

import java.util.Map;

public record BenchmarkResult(String fasterImplementation,
                              double percentageDifference,
                              Map<String, BenchmarkMetrics> metrics) {
}
