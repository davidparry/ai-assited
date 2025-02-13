package ai.qodo.joke;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LogCleaner implements Scrubber {

    @Override
    public String scrub(String joke) {
        Set<String> swearWordsSet = swearWords;
        return Arrays.stream(joke.split("\\s+"))
            .map(word -> swearWordsSet.contains(word.toLowerCase()) ? REDACTED_WORD : word)
            .collect(Collectors.joining(" "));

    }

}