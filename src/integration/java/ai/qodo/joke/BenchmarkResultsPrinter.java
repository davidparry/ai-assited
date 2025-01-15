package ai.qodo.joke;

public class BenchmarkResultsPrinter {


  public static void printBenchmarkReport(BenchmarkResult results) {
    var separator = "=".repeat(80);

    System.out.println(separator);
    System.out.println("BENCHMARK RESULTS");
    System.out.println(separator);

    System.out.printf("""
        Faster Implementation: %s
        Performance Difference: %.2f%%
        %n""", results.fasterImplementation(), results.percentageDifference());
    System.out.println("\nDetailed Metrics:");
    System.out.println("-".repeat(80));

    results.metrics().forEach((name, metric) -> {
      System.out.printf("""
              Implementation: %s
              Score: %.2f ops/ms
              Error Ratio: %.2f%%
              Status: %s
              
              """, name, metric.score(), metric.errorRatio(),
          metric.hasQuestionableError() ? "⚠️ High Error Rate" : "✓ Acceptable Error Rate");
    });

    System.out.println(separator);
  }

}
