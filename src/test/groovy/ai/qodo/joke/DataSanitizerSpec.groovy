package ai.qodo.joke


class DataSanitizerSpec extends ScrubberSpec {

    @Override
    Scrubber scrubberFactory() {
        return new DataSanitizer()
    }

}
