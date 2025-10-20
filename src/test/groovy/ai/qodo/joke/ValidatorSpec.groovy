package ai.qodo.joke

import spock.lang.Specification

class ValidatorSpec extends Specification {

    def "should create always valid validator"() {
        given:
        def validator = Validator.alwaysValid()

        expect:
        validator.isValid(null)
        validator.isValid("test")
        validator.isValid(123)
        validator.isValid([])
    }

    def "should create always invalid validator"() {
        given:
        def validator = Validator.alwaysInvalid()

        expect:
        !validator.isValid(null)
        !validator.isValid("test")
        !validator.isValid(123)
        !validator.isValid([])
    }

    def "should combine validators with AND"() {
        given:
        def notNullValidator = { it != null } as Validator<String>
        def notEmptyValidator = { it != "" } as Validator<String>
        def combinedValidator = notNullValidator.and(notEmptyValidator)

        expect:
        combinedValidator.isValid("test")
        !combinedValidator.isValid(null)
        !combinedValidator.isValid("")
    }

    def "should combine validators with OR"() {
        given:
        def isNullValidator = { it == null } as Validator<String>
        def isEmptyValidator = { it == "" } as Validator<String>
        def combinedValidator = isNullValidator.or(isEmptyValidator)

        expect:
        combinedValidator.isValid(null)
        combinedValidator.isValid("")
        !combinedValidator.isValid("test")
    }

    def "should negate validator"() {
        given:
        def notNullValidator = { it != null } as Validator<String>
        def negatedValidator = notNullValidator.negate()

        expect:
        negatedValidator.isValid(null)
        !negatedValidator.isValid("test")
    }

    def "should chain multiple validator operations"() {
        given:
        def lengthValidator = { it != null && it.length() > 3 } as Validator<String>
        def startsWithValidator = { it != null && it.startsWith("test") } as Validator<String>
        def chainedValidator = lengthValidator.and(startsWithValidator).negate()

        expect:
        !chainedValidator.isValid("testing")  // meets both conditions, but negated
        chainedValidator.isValid("test")      // meets length but not startsWith
        chainedValidator.isValid("tes")       // meets neither condition
        chainedValidator.isValid(null)        // meets neither condition
    }

    def "should work with custom validator implementations"() {
        given:
        def evenNumberValidator = { it != null && it % 2 == 0 } as Validator<Integer>
        def positiveNumberValidator = { it != null && it > 0 } as Validator<Integer>
        def combinedValidator = evenNumberValidator.and(positiveNumberValidator)

        expect:
        combinedValidator.isValid(2)
        combinedValidator.isValid(4)
        !combinedValidator.isValid(1)   // odd
        !combinedValidator.isValid(-2)  // negative
        !combinedValidator.isValid(null)
    }
}