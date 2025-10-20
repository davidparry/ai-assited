package ai.qodo.joke;

/**
 * A functional interface for validating input values.
 * This interface follows Java 21 functional programming principles.
 *
 * @param <T> the type of input to validate
 */
@FunctionalInterface
public interface Validator<T> {
    
    /**
     * Validates the given input.
     *
     * @param input the input to validate
     * @return true if the input is valid, false otherwise
     */
    boolean isValid(T input);
    
    /**
     * Creates a validator that always returns true.
     *
     * @param <T> the type of input
     * @return a validator that accepts all inputs
     */
    static <T> Validator<T> alwaysValid() {
        return input -> true;
    }
    
    /**
     * Creates a validator that always returns false.
     *
     * @param <T> the type of input
     * @return a validator that rejects all inputs
     */
    static <T> Validator<T> alwaysInvalid() {
        return input -> false;
    }
    
    /**
     * Combines this validator with another using logical AND.
     *
     * @param other the other validator
     * @return a validator that returns true only if both validators return true
     */
    default Validator<T> and(Validator<T> other) {
        return input -> this.isValid(input) && other.isValid(input);
    }
    
    /**
     * Combines this validator with another using logical OR.
     *
     * @param other the other validator
     * @return a validator that returns true if either validator returns true
     */
    default Validator<T> or(Validator<T> other) {
        return input -> this.isValid(input) || other.isValid(input);
    }
    
    /**
     * Creates a validator that negates this validator's result.
     *
     * @return a validator that returns the opposite of this validator
     */
    default Validator<T> negate() {
        return input -> !this.isValid(input);
    }
}