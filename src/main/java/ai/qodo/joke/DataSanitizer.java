package ai.qodo.joke;

import java.util.Objects;
import java.util.regex.Pattern;

public class DataSanitizer implements Scrubber {


    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Joke cannot be null");

        return swearWords.stream().reduce(joke, (text, swearWord) -> Pattern.compile("\\b" + Pattern.quote(swearWord) + "\\b", Pattern.CASE_INSENSITIVE).matcher(text).replaceAll(REDACTED_WORD));
    }
}
