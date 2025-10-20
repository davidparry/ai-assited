package ai.qodo.joke

import spock.lang.Specification

class CommandFlagsSpec extends Specification {

    def "testDefaultValuesWithNoFlags"() {
        given:
        String[] args = []

        when:
        def commandFlags = new CommandFlags(args)

        then:
        !commandFlags.hasFlag("anyFlag")
        commandFlags.getFlagValue("anyFlag").isEmpty()
    }

    def "testReturnsCorrectFlagValue"() {
        given:
        String[] args = ["-foo", "bar", "-baz", "qux"]

        when:
        def commandFlags = new CommandFlags(args)

        then:
        commandFlags.hasFlag("foo")
        commandFlags.getFlagValue("foo").isPresent()
        commandFlags.getFlagValue("foo").get() == "bar"

        commandFlags.hasFlag("baz")
        commandFlags.getFlagValue("baz").isPresent()
        commandFlags.getFlagValue("baz").get() == "qux"
    }
}
