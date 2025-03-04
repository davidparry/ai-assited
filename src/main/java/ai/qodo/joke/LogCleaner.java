package ai.qodo.joke;

import java.util.Objects;
import java.util.Set;

public class LogCleaner implements Scrubber {

    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "joke cannot be null");

        return swearWords.stream()
            .reduce(joke,
                (text, swearWord) -> text.replaceAll(
                    "(?i)\\b" + swearWord + "\\b",
                    REDACTED_WORD
                )
            );
    }

}