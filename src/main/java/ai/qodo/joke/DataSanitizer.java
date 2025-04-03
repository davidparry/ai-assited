package ai.qodo.joke;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DataSanitizer implements Scrubber {
    private static final String REDACTED_WORD = "REDACTED";

    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Joke cannot be null");
        
        // Create regex pattern to match whole words only
        var wordBoundaryPatterns = swearWords.stream()
            .map(word -> "\\b" + Pattern.quote(word) + "\\b")
            .collect(Collectors.toSet());
            
        String result = joke;
        
        // Replace each swear word with REDACTED_WORD
        for (String pattern : wordBoundaryPatterns) {
            result = result.replaceAll("(?i)" + pattern, REDACTED_WORD);
        }
        
        return result;
    }
}
