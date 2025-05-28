package ai.qodo.joke;


public class DataSanitizer implements Scrubber {

    @Override
    public String scrub(String joke) {
        if (joke == null) {
            throw new NullPointerException("joke is null");
        }
        
        return Arrays.stream(joke.split("\\s+"))
            .map(word -> {
                String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                return swearWords.contains(cleanWord) ? REDACTED_WORD : word;
            })
            .collect(Collectors.joining(" "));
        
        return result.toString();
    }
}