package com.epam.session;

public class InsufficientRightsException extends RuntimeException {
    private User user;
    private String path;

    public InsufficientRightsException(User user, String path) {
        super(String.format("User '%s' does not have sufficient rights to access path '%s'.", user.getUsername(), path));
        this.user = user;
        this.path = path;
    }

    public User getUser() {
        return user;
    }

    public String getPath() {
        return path;
    }
}
