package ai.qodo.joke;


public class LogCleaner implements Scrubber {

    @Override
    public String scrub(String joke) {
        System.out.println("Just logging the joke in a system out :-( "+ joke);
        return null;
    }
}