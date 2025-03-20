package ai.qodo.joke

import spock.lang.Specification

abstract class ScrubberSpec extends Specification {

    abstract  Scrubber scrubberFactory()

    // Input string with no swear words returns unchanged
    def "should return original string when no swear words present"() {
        given:
        def scrubber = scrubberFactory()
        def input = "This is a clean joke"

        when:
        def result = scrubber.scrub(input)

        then:
        result == input
    }

    // Empty string input returns empty string
    def "should return empty string when input is empty"() {
        given:
        def scrubber = scrubberFactory()
        def input = ""

        when:
        def result = scrubber.scrub(input)

        then:
        result == ""
    }

    // Mixed case swear words are detected and replaced
    def "should replace mixed case swear words with redacted word"() {
        given:
        def scrubber = scrubberFactory()
        def input = "This is a dAmn funny joke"
        def expected = "This is a REDACTED funny joke"

        when:
        def result = scrubber.scrub(input)

        then:
        result == expected
    }

    // Single word input that is a swear word returns REDACTED
    def "should return REDACTED when input is a single swear word"() {
        given:
        def scrubber = scrubberFactory()
        def input = "ass"

        when:
        def result = scrubber.scrub(input)

        then:
        result == "REDACTED"
    }

    // null value passed
    def "should return null when null value passed"() {
        given:
        def scrubber = scrubberFactory()
        def input = null

        when:
        scrubber.scrub(input)

        then:
        thrown(NullPointerException)
    }
}
