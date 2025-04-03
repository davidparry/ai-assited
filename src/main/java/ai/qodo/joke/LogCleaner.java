package ai.qodo.joke;

import java.util.regex.Pattern;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.Set;
import java.util.HashSet;

public class LogCleaner implements Scrubber {
    private static final String REDACTED_WORD = "****";

    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Joke cannot be null");
        
        // Create a regex pattern that matches whole words from the swearWords set
        String regex = String.join("|", 
            swearWords.stream()
                .map(word -> "\\b" + Pattern.quote(word) + "\\b")
                .toList()
        );
        
        // If no swear words in the set, return the original joke
        if (regex.isEmpty()) {
            return joke;
        }
        
        // Create pattern and matcher
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(joke);
        
        // Replace all matches with REDACTED_WORD
        return matcher.replaceAll(REDACTED_WORD);
    }
}
