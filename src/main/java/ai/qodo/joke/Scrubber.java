package ai.qodo.joke;

import java.util.Set;

public interface Scrubber {
    String REDACTED_WORD = "REDACTED";
    Set<String> swearWords = new SwearwordSet();

    String scrub(String joke);

}
