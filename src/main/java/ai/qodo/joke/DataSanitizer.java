package ai.qodo.joke;

import java.util.Objects;
import java.util.regex.Pattern;

public class DataSanitizer implements Scrubber {

  @Override
  public String scrub(String joke) {
    // Validate that input is non-null
    Objects.requireNonNull(joke, "Joke cannot be null");

    String result = joke;

    // Iteratively replace each swear word, ensuring whole word match and proper regex quoting.
    for (String word : swearWords) {
      // Pattern ensures we match whole words (using \b) and escapes any regex special characters
      Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b", Pattern.CASE_INSENSITIVE);
      result = pattern.matcher(result).replaceAll(REDACTED_WORD);
    }

    return result;
  }


}