package ai.qodo.joke;


import java.util.Set;

public interface Scrubber {
    String REDACTED_WORD = "REDACTED";
    Set<String> swearWords = new SwearwordSet();

    /**
     * Checks if the input joke contains any words matching the swearWords Set
     * and replaces them with REDACTED_WORD. The input will not be null per contract
     *
     * @param joke the text to be checked and scrubbed
     * @return the scrubbed text with any matching swear words replaced
     * @throws NullPointerException if joke is null
     */
    String scrub( String joke);

}
