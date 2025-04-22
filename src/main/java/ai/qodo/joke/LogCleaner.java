package ai.qodo.joke;


public class LogCleaner implements Scrubber {

    @Override
    public String scrub(String joke) {
        System.out.println(joke + "bad system out");
        return null;
    }
}