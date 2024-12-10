package ai.qodo.joke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface Scrubber {
    String REDACTED_WORD = "REDACTED";
    String scrub(String joke);

    Set<String> swearWords = new HashSet<>();

    static void loadSwearWords() {
        try (InputStream inputStream = Scrubber.class.getResourceAsStream("/swearwords.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                swearWords.add(line.trim());
            }
        } catch (IOException e) {
            Logger.getLogger(Scrubber.class.getName()).log(Level.SEVERE, "Error loading swearwords", e);
        }
    }
}
