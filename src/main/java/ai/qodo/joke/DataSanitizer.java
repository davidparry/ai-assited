package ai.qodo.joke;


public class DataSanitizer implements Scrubber {

    @Override
    public String scrub(String joke) {
        if (joke == null) {
            throw new NullPointerException("Joke cannot be null");
        }
        
        String[] words = joke.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        return Arrays.stream(words)
            .map(word -> {
                String cleanWord = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
                return swearWords.contains(cleanWord) ? REDACTED_WORD : word;
            })
            .collect(Collectors.joining(" "));
        
        return result.toString();
    }
}