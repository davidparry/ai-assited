package ai.qodo.joke;


import java.util.Arrays;
import java.util.stream.Collectors;

public class LogCleaner implements Scrubber {

    /**
     * Checks if the input joke contains any words matching the swearWords Set
     * and replaces them with REDACTED_WORD. The input will not be null per contract the interface.
     *
     * @param joke the text to be checked and scrubbed
     * @return the scrubbed text with any matching swear words replaced
     * @throws NullPointerException if joke is null
     */
    @Override
    public String scrub(String joke) {
        if (joke == null) {
            throw new NullPointerException("Joke cannot be null");
        }

        return Arrays.stream(joke.split("\\s+"))
                .map(word -> swearWords.contains(word) ? REDACTED_WORD : word)
                .collect(Collectors.joining(" "));
    }
}
