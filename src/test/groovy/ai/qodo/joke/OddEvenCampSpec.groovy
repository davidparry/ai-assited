package ai.qodo.joke

import spock.lang.Specification

class OddEvenCampSpec extends Specification {

    OddEvenCamp oddEvenCamp

    def setup() {
        oddEvenCamp = new OddEvenCampImpl()
    }

    def "should identify odd numbers correctly"() {
        expect:
        oddEvenCamp.isOdd(number) == expected

        where:
        number | expected
        1      | true
        3      | true
        5      | true
        -1     | true
        -3     | true
        0      | false
        2      | false
        4      | false
        -2     | false
        -4     | false
    }

    def "should identify even numbers correctly"() {
        expect:
        oddEvenCamp.isEven(number) == expected

        where:
        number | expected
        0      | true
        2      | true
        4      | true
        -2     | true
        -4     | true
        1      | false
        3      | false
        5      | false
        -1     | false
        -3     | false
    }

    def "should categorize numbers correctly"() {
        expect:
        oddEvenCamp.categorize(number) == expected

        where:
        number | expected
        1      | "ODD"
        2      | "EVEN"
        3      | "ODD"
        4      | "EVEN"
        0      | "EVEN"
        -1     | "ODD"
        -2     | "EVEN"
    }

    def "should filter odd numbers from list"() {
        given:
        def numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        when:
        def result = oddEvenCamp.filterOdd(numbers)

        then:
        result == [1, 3, 5, 7, 9]
    }

    def "should filter even numbers from list"() {
        given:
        def numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        when:
        def result = oddEvenCamp.filterEven(numbers)

        then:
        result == [2, 4, 6, 8, 10]
    }

    def "should count odd numbers in list"() {
        given:
        def numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        when:
        def count = oddEvenCamp.countOdd(numbers)

        then:
        count == 5
    }

    def "should count even numbers in list"() {
        given:
        def numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        when:
        def count = oddEvenCamp.countEven(numbers)

        then:
        count == 5
    }

    def "should find first odd number in list"() {
        given:
        def numbers = [2, 4, 6, 7, 8, 9]

        when:
        def result = oddEvenCamp.findFirstOdd(numbers)

        then:
        result.isPresent()
        result.get() == 7
    }

    def "should find first even number in list"() {
        given:
        def numbers = [1, 3, 5, 6, 7, 9]

        when:
        def result = oddEvenCamp.findFirstEven(numbers)

        then:
        result.isPresent()
        result.get() == 6
    }

    def "should return empty optional when no odd number found"() {
        given:
        def numbers = [2, 4, 6, 8]

        when:
        def result = oddEvenCamp.findFirstOdd(numbers)

        then:
        !result.isPresent()
    }

    def "should return empty optional when no even number found"() {
        given:
        def numbers = [1, 3, 5, 7]

        when:
        def result = oddEvenCamp.findFirstEven(numbers)

        then:
        !result.isPresent()
    }

    def "should validate valid number lists"() {
        given:
        def validNumbers = [1, 2, 3, 4, 5]

        when:
        def isValid = oddEvenCamp.validateNumbers(validNumbers)

        then:
        isValid
    }

    def "should invalidate null list"() {
        when:
        def isValid = oddEvenCamp.validateNumbers(null)

        then:
        !isValid
    }

    def "should handle empty list correctly"() {
        given:
        def emptyList = []

        expect:
        oddEvenCamp.filterOdd(emptyList) == []
        oddEvenCamp.filterEven(emptyList) == []
        oddEvenCamp.countOdd(emptyList) == 0
        oddEvenCamp.countEven(emptyList) == 0
        !oddEvenCamp.findFirstOdd(emptyList).isPresent()
        !oddEvenCamp.findFirstEven(emptyList).isPresent()
        oddEvenCamp.validateNumbers(emptyList)
    }

    def "should handle negative numbers correctly"() {
        given:
        def numbers = [-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5]

        expect:
        oddEvenCamp.filterOdd(numbers) == [-5, -3, -1, 1, 3, 5]
        oddEvenCamp.filterEven(numbers) == [-4, -2, 0, 2, 4]
        oddEvenCamp.countOdd(numbers) == 6
        oddEvenCamp.countEven(numbers) == 5
    }

    def "should throw exception for invalid input in categorize"() {
        given:
        def invalidCamp = new OddEvenCampImpl(
            { it == null } as Validator<Integer>,  // validator that only accepts null
            { true } as Validator<List<Integer>>
        )

        when:
        invalidCamp.categorize(5)

        then:
        thrown(IllegalArgumentException)
    }

    def "should throw exception for invalid list in filter operations"() {
        given:
        def invalidCamp = new OddEvenCampImpl(
            { true } as Validator<Integer>,
            { false } as Validator<List<Integer>>  // validator that always fails
        )

        when:
        invalidCamp.filterOdd([1, 2, 3])

        then:
        thrown(IllegalArgumentException)
    }
}