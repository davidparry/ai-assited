package ai.qodo.joke

import spock.lang.Specification

class CommandFlagsSpec extends Specification {
    // Parse command line arguments with flags and values correctly
    def "should parse flags with values from command line arguments"() {
        given:
        String[] args = ["-f", "value1", "-g", "value2", "-h"]

        when:
        CommandFlags commandFlags = new CommandFlags(args)

        then:
        commandFlags.hasFlag("f") == true
        commandFlags.getFlagValue("f").get() == "value1"
        commandFlags.hasFlag("g") == true
        commandFlags.getFlagValue("g").get() == "value2"
        commandFlags.hasFlag("h") == false
        !commandFlags.getFlagValue("h").isPresent()
        !commandFlags.hasFlag("nonexistent")
        !commandFlags.getFlagValue("nonexistent").isPresent()
    }
    // Handle null input arguments array
    def "should handle null input arguments array gracefully"() {
        when:
        CommandFlags commandFlags = new CommandFlags(null)

        then:
        !commandFlags.hasFlag("any")
        !commandFlags.getFlagValue("any").isPresent()
        commandFlags.toString() == "CommandFlags{flags={}}"
    }
}
