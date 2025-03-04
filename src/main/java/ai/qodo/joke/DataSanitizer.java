package ai.qodo.joke;

import java.util.Objects;

public class DataSanitizer implements Scrubber {

    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Joke text cannot be null");

        return swearWords.stream()
            .reduce(joke,
                (text, swearWord) -> text.replaceAll(
                    "(?i)\\b" + swearWord + "\\b",
                    REDACTED_WORD
                )
            );
    }

}