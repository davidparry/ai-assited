package ai.qodo.joke

import spock.lang.Specification

class CommandFlagsSpec extends Specification {
    // Constructor handles null args array
    def "should create empty instance when given null arguments array"() {
        when:
        def flags = new CommandFlags(null)

        then:
        flags.toString() == "CommandFlags{flags={}}"
        !flags.hasFlag("any")
        !flags.getFlagValue("any").isPresent()
    }
    // should take a empty array
    def "should create instance and handle empty array without errors"() {
        given:
        def args = [] as String[]

        when:
        def flags = new CommandFlags(args)

        then:
        !flags.hasFlag("anyFlag")
        flags.getFlagValue("anyFlag").isEmpty()
    }
}
