package ai.qodo.joke;

import java.util.Objects;
import java.util.regex.Pattern;

public class LogCleaner implements Scrubber {

    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Joke cannot be null");

        return swearWords.stream()
                .reduce(joke, (text, word) -> Pattern.compile("\\b" + Pattern.quote(word) + "\\b", Pattern.CASE_INSENSITIVE)
                        .matcher(text).replaceAll(REDACTED_WORD), (s1, s2) -> s1);
    }
}