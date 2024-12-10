package ai.qodo.joke;

public class ImperativeScrubber implements Scrubber {
    public String scrub(String joke) {
        StringBuilder scrubbedText = new StringBuilder();
        String[] wordArray = joke.toLowerCase().split("\\s+");

        for (String word : wordArray) {
            String cleanWord = word.replaceAll("[^a-zA-Z]", "");
            if (swearWords.contains(cleanWord)) {
                scrubbedText.append(REDACTED_WORD);
            } else {
                scrubbedText.append(word);
            }
            scrubbedText.append(" ");
        }

        return scrubbedText.toString().trim();
    }


}