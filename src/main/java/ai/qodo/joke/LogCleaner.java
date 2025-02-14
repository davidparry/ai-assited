package ai.qodo.joke;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogCleaner implements Scrubber {

    @Override
    public String scrub(String joke) {
        if (joke == null) {
            throw new NullPointerException("joke cannot be null");
        }

        Set<String> swearWords = Scrubber.swearWords;
        String regex = swearWords.stream()
            .map(Pattern::quote)
            .collect(Collectors.joining("|", "\\b(", ")\\b"));

        // Use (?i) flag for case-insensitive matching
        return joke.replaceAll("(?i)" + regex, REDACTED_WORD);
    }
}