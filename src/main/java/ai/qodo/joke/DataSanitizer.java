package ai.qodo.joke;


import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataSanitizer implements Scrubber {

    @Override
    public String scrub(String joke) {
        Objects.requireNonNull(joke, "Input joke cannot be null");

        if (joke.isEmpty()) {
            return joke;
        }

        return Arrays.stream(joke.split("\\s+"))
                .map(this::scrubWord)
                .collect(Collectors.joining(" "));
    }

    private String scrubWord(String word) {
        return swearWords.stream()
                .anyMatch(swear -> word.toLowerCase().equals(swear.toLowerCase()))
                ? REDACTED_WORD
                : word;
    }

}