package com.appiumProject.config;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ConfigLoader {

    public static CapabilitiesConfig load(String fileName) {   
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            InputStream is = ConfigLoader.class.getClassLoader()
                .getResourceAsStream("config/" + fileName);

            if (is == null) {
                throw new RuntimeException("YAML config file not found: " + fileName);
            }

            return mapper.readValue(is, CapabilitiesConfig.class);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config from YAML: " + fileName, e);
        }
    }
}