package ai.qodo.joke;


public class DataSanitizer implements Scrubber {

    @Override
    public String scrub(String joke) {
        if (joke == null) {
            throw new NullPointerException("joke cannot be null");
        }
        
        String[] words = joke.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                result.append(" ");
            }
            
            // Check if the word (case-insensitive) is in the swear words set
            if (swearWords.contains(words[i].toLowerCase())) {
                result.append(REDACTED_WORD);
            } else {
                result.append(words[i]);
            }
        }
        
        return result.toString();
    }
}