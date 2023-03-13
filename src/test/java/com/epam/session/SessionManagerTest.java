package com.epam.session;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionManagerTest {
    private ServerConfig config = ServerConfig.getInstance();

    @Test
    public void testCreateSessionWithSufficientRights() {
        // Arrange
        User user = new User("user1", "password");
        String accessedPath = "/home/user/*";
        config.addPathAccessLevel(accessedPath, "user");
        config.addUserAccessLevel(user, "user");
        SessionManager sessionManager = SessionManager.getInstance();

        // Act
        Session session = sessionManager.createSession(user, accessedPath);

        // Assert
        assertNotNull(session);
        assertEquals(session.getUser(), user);
    }

    @Test
    public void testCreateSessionWithInsufficientRights() {
        // Arrange
        User user = new User("user1", "password");
        String accessedPath = "/home/adminConsole/*";
        config.addPathAccessLevel(accessedPath, "admin");
        config.addUserAccessLevel(user, "user");
        SessionManager sessionManager = SessionManager.getInstance();

        // Assert
        // Expected exception
        Exception exception = assertThrows(InsufficientRightsException.class, () -> sessionManager.createSession(user, accessedPath));
        assertEquals("User 'user1' does not have sufficient rights to access path '/home/adminConsole/*'.",exception.getMessage());
    }

}
