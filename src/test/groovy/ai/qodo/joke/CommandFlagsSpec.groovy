package ai.qodo.joke

import spock.lang.Specification

class CommandFlagsSpec extends Specification {


        // Parse command line arguments with flags and values correctly
        def "should parse flags with values correctly"() {
            given:
            String[] args = ["-verbose", "true", "-count", "5", "-quiet"]

            when:
            CommandFlags commandFlags = new CommandFlags(args)

            then:
            commandFlags.hasFlag("verbose") == true
            commandFlags.getFlagValue("verbose").get() == "true"
            commandFlags.hasFlag("count") == true
            commandFlags.getFlagValue("count").get() == "5"
            commandFlags.hasFlag("quiet") == true
            commandFlags.getFlagValue("quiet").isEmpty()
            commandFlags.hasFlag("nonexistent") == false
            commandFlags.getFlagValue("nonexistent").isEmpty()
        }

        // Handle null input in constructor
        def "should handle null input in constructor"() {
            when:
            CommandFlags commandFlags = new CommandFlags(null)

            then:
            commandFlags.hasFlag("any") == false
            commandFlags.getFlagValue("any").isEmpty()
            commandFlags.toString() == "CommandFlags{flags={}}"
        }

}
