package ai.qodo.joke;

import java.util.Objects;
import java.util.Arrays;
import java.util.List;

public class LogCleaner implements Scrubber {
    
    private static final String REDACTED_WORD = "REDACTED";
    private static final List<String> swearWords = Arrays.asList(
        "damn", "hell", "crap", "ass", "shit", "bitch"
    );

    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Joke cannot be null");
        
        String result = joke;
        for (String word : swearWords) {
            // Replace the word with REDACTED
            // Using word boundary to ensure we match whole words only
            result = result.replaceAll("\\b" + word + "\\b", REDACTED_WORD);
        }
        
        return result;
    }
}
