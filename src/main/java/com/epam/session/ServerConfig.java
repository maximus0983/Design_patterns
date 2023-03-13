package com.epam.session;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServerConfig {
    private static String filePath = "src/main/resources/conf.properties";
    private static ServerConfig instance;
    private Map<User, String> accessLevels = new HashMap<>();
    private Map<String, String> pathAccessLevels = new HashMap<>();

    private ServerConfig() {
        Properties props = new Properties();
        try(InputStream in = new FileInputStream(filePath)) {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ServerConfig getInstance() {
        if (instance == null) {
            instance = new ServerConfig();
        }
        return instance;
    }

    public void addUserAccessLevel(User user, String accessLevel) {
        accessLevels.put(user, accessLevel);
    }

    public void addPathAccessLevel(String path, String accessLevel) {
        pathAccessLevels.put(path, accessLevel);
    }

    public String getAccessLevel(User user) {
        return accessLevels.get(user);
    }

    public String getRequiredLevel(String path) {
        return pathAccessLevels.get(path);
    }
}
