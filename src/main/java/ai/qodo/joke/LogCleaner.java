package ai.qodo.joke;

import java.util.regex.Pattern;

public class LogCleaner implements Scrubber {

    @Override
    private final Map<String, Pattern> swearPatterns;

    public LogCleaner() {
        swearPatterns = swearWords.stream()
            .collect(Collectors.toMap(
                word -> word,
                word -> Pattern.compile("(?i)\\b" + Pattern.quote(word) + "\\b")
            ));
    }

    public String scrub(String joke) {
        if (joke == null) {
            throw new NullPointerException("Input joke cannot be null");
        }

        String result = joke;
        for (Map.Entry<String, Pattern> entry : swearPatterns.entrySet()) {
            result = entry.getValue().matcher(result).replaceAll(REDACTED_WORD);
        }
        return result;
    }
        return result;
    }

}