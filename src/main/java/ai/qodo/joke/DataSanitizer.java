package ai.qodo.joke;

import java.util.Objects;

public class DataSanitizer implements Scrubber {

    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Joke cannot be null");

        String result = joke;
        for (String swearWord : swearWords) {
            // Create a regex pattern that matches the word with word boundaries
            // This ensures we match only whole words, not parts of words
            String pattern = "\\b" + swearWord + "\\b";
            result = result.replaceAll("(?i)" + pattern, REDACTED_WORD);
        }

        return result;
    }
}

