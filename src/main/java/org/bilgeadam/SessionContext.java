package org.bilgeadam;

import org.bilgeadam.entity.User;

public class SessionContext {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        SessionContext.user = user;
    }
}
