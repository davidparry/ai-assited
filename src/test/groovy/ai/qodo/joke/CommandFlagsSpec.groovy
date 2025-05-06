package ai.qodo.joke

import spock.lang.Specification

class CommandFlagsSpec extends Specification {

    def "should handle null arguments array"() {
        when:
        def flags = new CommandFlags(null)
        
        then:
        !flags.hasFlag("any-flag")
        flags.getFlagValue("any-flag") == Optional.empty()
    }
    
    def "should skip value in next iteration"() {
        given:
        String[] args = ["-flag1", "value1", "-flag2", "value2", "-flag3"]
        
        when:
        def flags = new CommandFlags(args)
        
        then:
        flags.hasFlag("flag1")
        flags.getFlagValue("flag1") == Optional.of("value1")
        
        flags.hasFlag("flag2")
        flags.getFlagValue("flag2") == Optional.of("value2")
        
        flags.hasFlag("flag3")
        flags.getFlagValue("flag3") == Optional.empty()
        
        !flags.hasFlag("value1")
        !flags.hasFlag("value2")
    }
}
