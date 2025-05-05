package ai.qodo.joke;


import java.util.Objects;

public class DataSanitizer implements Scrubber {

    @Override
    public String scrub(String joke) {
        // Throw NullPointerException if joke is null as per the contract
        Objects.requireNonNull(joke, "Joke cannot be null");

        // Split the joke into words
        String[] words = joke.split("\\s+");
        StringBuilder scrubbedJoke = new StringBuilder();

        // Check each word against the swearWords set
        for (String word : words) {
            // Clean the word from punctuation for checking
            String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

            // If the cleaned word is in the swearWords set, replace with REDACTED_WORD
            if (swearWords.contains(cleanWord)) {
                scrubbedJoke.append(REDACTED_WORD);
            } else {
                scrubbedJoke.append(word);
            }

            // Add a space after each word except the last one
            scrubbedJoke.append(" ");
        }

        // Remove the trailing space and return the result
        return scrubbedJoke.toString().trim();
    }
}