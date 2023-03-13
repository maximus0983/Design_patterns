package com.epam.session;

public class AccessChecker {
    private static AccessChecker instance;

    private AccessChecker() {
    }

    public static AccessChecker getInstance() {
        if (instance == null) {
            instance = new AccessChecker();
        }
        return instance;
    }

    public boolean mayAccess(User user, String path) {
        ServerConfig config = ServerConfig.getInstance();
        String userLevel = config.getAccessLevel(user);
        String requiredLevel = config.getRequiredLevel(path);
        return userLevel.equals(requiredLevel) || userLevel.equals("admin");

    }
}
