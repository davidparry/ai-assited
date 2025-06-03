package ai.qodo.joke;

import java.util.Objects;
import java.util.regex.Pattern;

public class DataSanitizer implements Scrubber {

    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Input joke must not be null");
        // Replace each swear word (case-insensitive, whole word) with REDACTED_WORD
        return swearWords.stream()
                .reduce(joke, (text, swear) -> Pattern.compile("\\b" + Pattern.quote(swear) + "\\b", Pattern.CASE_INSENSITIVE)
                        .matcher(text).replaceAll(REDACTED_WORD), (s1, s2) -> s1);
    }
}