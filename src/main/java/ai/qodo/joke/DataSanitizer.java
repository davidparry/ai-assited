package ai.qodo.joke;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataSanitizer implements Scrubber {


    @Override
    public String scrub(String joke) {
        // Throw NullPointerException if joke is null as specified in the javadoc
        Objects.requireNonNull(joke, "Joke cannot be null");

        if (joke.isEmpty()) {
            return joke;
        }

        // Build a regex pattern from the swearWords set, handling case insensitivity and word boundaries
        StringBuilder patternBuilder = new StringBuilder();
        boolean first = true;
        for (String word : swearWords) {
            if (!first) {
                patternBuilder.append("|");
            }
            patternBuilder.append("\\b").append(Pattern.quote(word)).append("\\b");
            first = false;
        }

        if (patternBuilder.length() == 0) {
            // No swear words defined; nothing to redact
            return joke;
        }

        Pattern pattern = Pattern.compile(patternBuilder.toString(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(joke);

        // Replace each match with REDACTED_WORD from the interface constant
        return matcher.replaceAll(REDACTED_WORD);
    }
}
