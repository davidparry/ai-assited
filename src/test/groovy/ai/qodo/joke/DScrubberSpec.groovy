package ai.qodo.joke


class DScrubberSpec extends ScrubberSpec {

    @Override
    Scrubber scrubberFactory() {
        return new DScrubber()
    }

}
