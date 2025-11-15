package ai.qodo.joke;

import java.util.List;
import java.util.Optional;

/**
 * Interface for categorizing numbers into odd and even camps.
 * This interface follows Java 21 functional programming principles.
 */
public interface OddEvenCamp {
    
    /**
     * Determines if a number belongs to the odd camp.
     *
     * @param number the number to check
     * @return true if the number is odd, false otherwise
     */
    boolean isOdd(int number);
    
    /**
     * Determines if a number belongs to the even camp.
     *
     * @param number the number to check
     * @return true if the number is even, false otherwise
     */
    boolean isEven(int number);
    
    /**
     * Categorizes a number into its appropriate camp.
     *
     * @param number the number to categorize
     * @return "ODD" if the number is odd, "EVEN" if the number is even
     */
    String categorize(int number);
    
    /**
     * Filters a list of numbers to return only odd numbers.
     *
     * @param numbers the list of numbers to filter
     * @return a list containing only odd numbers
     */
    List<Integer> filterOdd(List<Integer> numbers);
    
    /**
     * Filters a list of numbers to return only even numbers.
     *
     * @param numbers the list of numbers to filter
     * @return a list containing only even numbers
     */
    List<Integer> filterEven(List<Integer> numbers);
    
    /**
     * Counts the number of odd numbers in a list.
     *
     * @param numbers the list of numbers to count
     * @return the count of odd numbers
     */
    long countOdd(List<Integer> numbers);
    
    /**
     * Counts the number of even numbers in a list.
     *
     * @param numbers the list of numbers to count
     * @return the count of even numbers
     */
    long countEven(List<Integer> numbers);
    
    /**
     * Finds the first odd number in a list.
     *
     * @param numbers the list of numbers to search
     * @return an Optional containing the first odd number, or empty if none found
     */
    Optional<Integer> findFirstOdd(List<Integer> numbers);
    
    /**
     * Finds the first even number in a list.
     *
     * @param numbers the list of numbers to search
     * @return an Optional containing the first even number, or empty if none found
     */
    Optional<Integer> findFirstEven(List<Integer> numbers);
    
    /**
     * Validates that all numbers in a list are valid (non-null).
     *
     * @param numbers the list of numbers to validate
     * @return true if all numbers are valid, false otherwise
     */
    boolean validateNumbers(List<Integer> numbers);
}