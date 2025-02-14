package ai.qodo.joke;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataSanitizer implements Scrubber {

    /**
     * Implements the scrub interface requirement using a declarative approach with
     * Java streams to process and sanitize the input joke text.
     *
     * @param joke the text to be checked and scrubbed
     * @return the scrubbed text with any matching swear words replaced
     * @throws NullPointerException if joke is null
     */
    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Joke text cannot be null");

        return Arrays.stream(joke.split("\\s+"))
                    .map(word -> swearWords.contains(word.toLowerCase())
                         ? REDACTED_WORD
                         : word)
                    .collect(Collectors.joining(" "));
    }
}