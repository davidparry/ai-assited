package ai.qodo.joke

import spock.lang.Specification

class CommandFlagsSpec extends Specification {
    // Handle null input arguments array
    def "should handle null input arguments array gracefully"() {
        when:
        CommandFlags commandFlags = new CommandFlags(null)

        then:
        commandFlags.hasFlag("any") == false
        commandFlags.getFlagValue("any").isEmpty()

        and: "toString should not throw exceptions"
        commandFlags.toString() != null
    }
}
