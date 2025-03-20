package ai.qodo.joke;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DataSanitizer implements Scrubber {

  private static final String REDACTED_WORD = "REDACTED";

  public DataSanitizer() {
  }

  @Override
  public String scrub(String joke) {
    Objects.requireNonNull(joke, "Joke cannot be null");

    if (swearWords.isEmpty()) {
      return joke;
    }

    // Create a pattern to match whole words only
    var wordBoundaryPattern =
        swearWords.stream().map(word -> "\\b" + Pattern.quote(word) + "\\b").collect(Collectors.joining("|"));

    var pattern = Pattern.compile(wordBoundaryPattern, Pattern.CASE_INSENSITIVE);
    var matcher = pattern.matcher(joke);

    return matcher.replaceAll(REDACTED_WORD);
  }
}
