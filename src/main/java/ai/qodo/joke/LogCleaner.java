package ai.qodo.joke;

import java.util.regex.Pattern;

public class LogCleaner implements Scrubber {

    @Override
    public String scrub(String joke) {
        if (joke == null) {
            throw new NullPointerException("Input joke cannot be null");
        }

        String result = joke;
        for (String swearWord : swearWords) {
            String regex = "(?i)\\b" + Pattern.quote(swearWord) + "\\b";
            result = result.replaceAll(regex, REDACTED_WORD);
        }
        return result;
    }

}