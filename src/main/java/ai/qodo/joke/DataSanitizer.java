package ai.qodo.joke;

import java.util.Objects;
import java.util.regex.Pattern;

public class DataSanitizer implements Scrubber {

    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Input joke must not be null");
        // Replace each swear word (case-insensitive, whole word) with REDACTED_WORD
        var jokeText = joke;
        for (var swear : swearWords) {
            var pattern = Pattern.compile("\\b" + Pattern.quote(swear) + "\\b", Pattern.CASE_INSENSITIVE);
            jokeText = pattern.matcher(jokeText).replaceAll(REDACTED_WORD);
        }
        return jokeText;
    }
}