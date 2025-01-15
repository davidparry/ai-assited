package ai.qodo.joke

import spock.lang.Specification

class BenchmarkRunnerSpec extends Specification {

    def "should compare performance between logCleaner and dataSanitizer when benchmark runs successfully"() {
        given:
        BenchmarkRunner benchmarkRunner = new BenchmarkRunner()

        when:
        BenchmarkResult result = benchmarkRunner.runBenchMark()

        then:
        noExceptionThrown()
        BenchmarkResultsPrinter.printBenchmarkReport(result)
    }

}
