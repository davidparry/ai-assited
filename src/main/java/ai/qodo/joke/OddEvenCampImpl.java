package ai.qodo.joke;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of OddEvenCamp interface using Java 21 functional programming principles.
 * This implementation uses the Validator interface for input validation.
 */
public class OddEvenCampImpl implements OddEvenCamp {
    
    private final Validator<Integer> numberValidator;
    private final Validator<List<Integer>> listValidator;
    
    /**
     * Creates a new OddEvenCampImpl with default validators.
     */
    public OddEvenCampImpl() {
        this.numberValidator = Objects::nonNull;
        this.listValidator = list -> list != null && list.stream().allMatch(Objects::nonNull);
    }
    
    /**
     * Creates a new OddEvenCampImpl with custom validators.
     *
     * @param numberValidator validator for individual numbers
     * @param listValidator validator for lists of numbers
     */
    public OddEvenCampImpl(Validator<Integer> numberValidator, Validator<List<Integer>> listValidator) {
        this.numberValidator = numberValidator;
        this.listValidator = listValidator;
    }
    
    @Override
    public boolean isOdd(int number) {
        return numberValidator.isValid(number) && (number % 2 != 0);
    }
    
    @Override
    public boolean isEven(int number) {
        return numberValidator.isValid(number) && (number % 2 == 0);
    }
    
    @Override
    public String categorize(int number) {
        if (!numberValidator.isValid(number)) {
            throw new IllegalArgumentException("Invalid number: " + number);
        }
        return isOdd(number) ? "ODD" : "EVEN";
    }
    
    @Override
    public List<Integer> filterOdd(List<Integer> numbers) {
        if (!listValidator.isValid(numbers)) {
            throw new IllegalArgumentException("Invalid number list");
        }
        
        return numbers.stream()
                .filter(numberValidator::isValid)
                .filter(this::isOdd)
                .toList();
    }
    
    @Override
    public List<Integer> filterEven(List<Integer> numbers) {
        if (!listValidator.isValid(numbers)) {
            throw new IllegalArgumentException("Invalid number list");
        }
        
        return numbers.stream()
                .filter(numberValidator::isValid)
                .filter(this::isEven)
                .toList();
    }
    
    @Override
    public long countOdd(List<Integer> numbers) {
        if (!listValidator.isValid(numbers)) {
            throw new IllegalArgumentException("Invalid number list");
        }
        
        return numbers.stream()
                .filter(numberValidator::isValid)
                .filter(this::isOdd)
                .count();
    }
    
    @Override
    public long countEven(List<Integer> numbers) {
        if (!listValidator.isValid(numbers)) {
            throw new IllegalArgumentException("Invalid number list");
        }
        
        return numbers.stream()
                .filter(numberValidator::isValid)
                .filter(this::isEven)
                .count();
    }
    
    @Override
    public Optional<Integer> findFirstOdd(List<Integer> numbers) {
        if (!listValidator.isValid(numbers)) {
            return Optional.empty();
        }
        
        return numbers.stream()
                .filter(numberValidator::isValid)
                .filter(this::isOdd)
                .findFirst();
    }
    
    @Override
    public Optional<Integer> findFirstEven(List<Integer> numbers) {
        if (!listValidator.isValid(numbers)) {
            return Optional.empty();
        }
        
        return numbers.stream()
                .filter(numberValidator::isValid)
                .filter(this::isEven)
                .findFirst();
    }
    
    @Override
    public boolean validateNumbers(List<Integer> numbers) {
        return listValidator.isValid(numbers);
    }
}