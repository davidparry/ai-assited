package ai.qodo.joke;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataSanitizer implements Scrubber {

    /**
     * Implements the scrub method using a declarative approach with Java streams.
     * Splits the input on whitespace, processes each word, and rejoins.
     *
     * @param joke the text to be checked and scrubbed
     * @return the scrubbed text with any matching swear words replaced
     * @throws NullPointerException if joke is null
     */
    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Input joke cannot be null");

        return Arrays.stream(joke.split("\\s+"))
                    .map(this::processWord)
                    .collect(Collectors.joining(" "));
    }

    /**
     * Processes a single word, checking if it matches any swear word
     * and replacing it if necessary.
     *
     * @param word the word to process
     * @return either REDACTED_WORD if the word is in swearWords, or the original word
     */
    private String processWord(String word) {
        return swearWords.contains(word.toLowerCase())
               ? REDACTED_WORD
               : word;
    }
}