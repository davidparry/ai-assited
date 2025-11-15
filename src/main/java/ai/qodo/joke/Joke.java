package ai.qodo.joke;


public interface Joke {
    /**
     * Generate a joke depending on the implementation:
     * 1. A call to https://evilinsult.com/generate_insult.php?lang=en&type=pirate and returns the string response.
     * 2. A call to an LLM and ask for a Joke and return a response
     * @param name - incorporate into the joke if applicable.
     * @return the joke.
     */
    String joke(String name);
}
