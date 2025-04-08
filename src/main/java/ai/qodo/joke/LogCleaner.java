package ai.qodo.joke;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * LogCleaner is an implementation of the Scrubber interface that replaces swear words in a given joke text
 * with the predefined REDACTED_WORD. This implementation uses modern stream operations and functional programming
 * techniques for processing the input text.
 *
 * <p>The process involves splitting the input into words, trimming each one, and checking (case-insensitively)
 * if the word is contained in the defined set of swear words. If a match is found, the word is replaced with
 * REDACTED_WORD.</p>
 */
public class LogCleaner implements Scrubber {


    /**
     * Scrubs the provided joke by replacing any swear words with the REDACTED_WORD.
     *
     * <p>This method splits the input text on whitespace, trims each token, converts it to lowercase for case-insensitive
     * comparison against the internal set of swear words, and reconstructs the text, replacing any swear word occurrences
     * with REDACTED_WORD.</p>
     *
     * @param joke the text to be scrubbed; must not be null
     * @return the scrubbed text with any matching swear words replaced by REDACTED_WORD
     * @throws NullPointerException if joke is null
     */
    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Joke cannot be null");
        return Arrays.stream(joke.split("\\s+"))
                .map(String::trim)
                .map(word -> swearWords.contains(word.toLowerCase()) ? REDACTED_WORD : word)
                .collect(Collectors.joining(" "))
                .strip();
    }
}
