package ai.qodo.joke;

public class IScrubber implements Scrubber {

    public String scrub(String joke) {
        StringBuilder scrubbedText = new StringBuilder();
        String[] wordArray = joke.split("\\s+");

        for (String word : wordArray) {
            if (swearWords.contains(word.toLowerCase())) {
                scrubbedText.append(REDACTED_WORD);
            } else {
                scrubbedText.append(word);
            }
            scrubbedText.append(" ");
        }

        return scrubbedText.toString().trim();
    }


}