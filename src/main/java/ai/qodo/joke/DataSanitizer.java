package ai.qodo.joke;


public class DataSanitizer implements Scrubber {

    @Override
    public String scrub(String joke) {
        if (joke == null) {
            throw new NullPointerException("joke is null");
        }
        
        String[] words = joke.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                result.append(" ");
            }
            
            // Extract the word without punctuation for checking
            String cleanWord = words[i].replaceAll("[^a-zA-Z]", "").toLowerCase();
            
            if (swearWords.contains(cleanWord)) {
                result.append(REDACTED_WORD);
            } else {
                result.append(words[i]);
            }
        }
        
        return result.toString();
    }
}