package ai.qodo.joke;

import java.util.Set;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LogCleaner implements Scrubber {
    

    @Override
    public String scrub(String joke) {
        if (joke == null) {
            throw new NullPointerException("Input joke must not be null");
        }
        
        // Split on word boundaries, replace swear words, and rejoin
        return Arrays.stream(joke.split("\\b"))
                .map(word -> swearWords.contains(word.toLowerCase()) ? REDACTED_WORD : word)
                .collect(Collectors.joining());
    }
}
