package org.s367118;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfReader {

    public ConfReader(String filePath) {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private final Properties properties;

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
