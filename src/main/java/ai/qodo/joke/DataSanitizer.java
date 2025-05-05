package ai.qodo.joke;

import java.util.Objects;

public class DataSanitizer implements Scrubber {

    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Joke cannot be null");

        String result = joke;
        for (String word : swearWords) {
            // Replace the word with REDACTED
            // Using word boundary to ensure we match whole words only
            result = result.replaceAll("(?i)\\b" + word + "\\b", REDACTED_WORD);
        }

        return result;
    }
}
