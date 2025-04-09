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
                .map(word -> switch(word) {
                    case String s when containsSwearWord(s) -> REDACTED_WORD;
                    default -> word;
                })
                .collect(Collectors.joining(" "));
    }

    private boolean containsSwearWord(String word) {
        // Remove any punctuation for checking
        String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
        return !cleanWord.isEmpty() && swearWords.contains(cleanWord);
    }
}
