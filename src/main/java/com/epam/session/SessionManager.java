package com.epam.session;

public class SessionManager {
    private static SessionManager instance;
    private AccessChecker accessChecker = AccessChecker.getInstance();

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public Session createSession(User user, String path) {
        if (accessChecker.mayAccess(user, path)) {
            return new Session(user);
        } else {
            throw new InsufficientRightsException(user, path);
        }
    }
}
