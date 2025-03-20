package ai.qodo.joke


class LogCleanerSpec extends ScrubberSpec {

    @Override
    Scrubber scrubberFactory() {
        return new LogCleaner()
    }

}
