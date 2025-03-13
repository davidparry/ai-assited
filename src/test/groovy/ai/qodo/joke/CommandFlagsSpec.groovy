package ai.qodo.joke

import spock.lang.Specification

class CommandFlagsSpec extends Specification {
    // Parse command line arguments with flags and values correctly
    def "should parse flags with values from command line arguments"() {
        given:
        String[] args = ["-verbose", "true", "-count", "5", "-quiet"]

        when:
        CommandFlags commandFlags = new CommandFlags(args)

        then:
        commandFlags.hasFlag("verbose") == true
        commandFlags.getFlagValue("verbose").isPresent()
        commandFlags.getFlagValue("verbose").get() == "true"

        commandFlags.hasFlag("count") == true
        commandFlags.getFlagValue("count").isPresent()
        commandFlags.getFlagValue("count").get() == "5"

        commandFlags.hasFlag("nonexistent") == false
        commandFlags.getFlagValue("nonexistent").isEmpty()
    }
}
