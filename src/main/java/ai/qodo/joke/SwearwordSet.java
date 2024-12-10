package ai.qodo.joke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SwearwordSet extends HashSet<String> {
    private static final Logger LOGGER = Logger.getLogger(SwearwordSet.class.getName());

    public SwearwordSet() {
        loadFromFile();
    }

    private void loadFromFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/swearwords.txt"); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                add(line.trim());
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading swearwords", e);
        }
    }
}