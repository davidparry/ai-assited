package ai.qodo.joke;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataSanitizer implements Scrubber {

    private static final String REDACTED_WORD = "REDACTED";

    public DataSanitizer() {
    }


    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Joke cannot be null");

        return Arrays.stream(joke.split("\\s+"))
                .map(word -> containsSwearWord(word) ? REDACTED_WORD : word)
                .collect(Collectors.joining(" "));
    }

    private boolean containsSwearWord(String word) {
        // Remove any punctuation for checking
        String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
        return !cleanWord.isEmpty() && swearWords.contains(cleanWord);
    }
}
