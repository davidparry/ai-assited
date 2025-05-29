package ai.qodo.joke;


public class LogCleaner implements Scrubber {

    @Override
    public String scrub(String joke) {
        if (joke == null) {
            throw new NullPointerException("Joke cannot be null");
        }
        
        String[] words = joke.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            // Remove punctuation for checking but keep original for replacement
            String cleanWord = word.replaceAll("[^a-zA-Z]", "");
            
            if (swearWords.contains(cleanWord)) {
                result.append(REDACTED_WORD);
            } else {
                result.append(words[i]);
            }
            
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
}