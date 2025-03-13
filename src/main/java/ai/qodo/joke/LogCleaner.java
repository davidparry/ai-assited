package ai.qodo.joke;


import java.util.regex.Pattern;

public class LogCleaner implements Scrubber {

  @Override
  public String scrub(String joke) {
    // Create a pattern that matches whole words from the swearWords set
    return swearWords.stream().reduce(joke,
        (text, swearWord) -> Pattern.compile("\\b" + Pattern.quote(swearWord) + "\\b", Pattern.CASE_INSENSITIVE)
            .matcher(text).replaceAll(REDACTED_WORD));
  }


}