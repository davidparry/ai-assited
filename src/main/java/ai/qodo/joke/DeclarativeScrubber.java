package ai.qodo.joke;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeclarativeScrubber implements Scrubber {

    public String scrub(String joke) {
        return Stream.of(joke.split("\\s+")).map(word -> swearWords.contains(word.toLowerCase()) ? REDACTED_WORD : word).collect(Collectors.joining(" "));
    }


}