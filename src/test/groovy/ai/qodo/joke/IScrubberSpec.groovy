package ai.qodo.joke


class IScrubberSpec extends ScrubberSpec {

    @Override
    Scrubber scrubberFactory() {
        return new IScrubber()
    }

}
