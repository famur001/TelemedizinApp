package com.example.telemedizin.Common;

import com.example.telemedizin.Model.User;

public class Common {

    public static User currentUser = null;
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    public static User getCurrentUser() { return currentUser; }
    public static boolean isLogged() { return currentUser == null ? false : true; }
    public static void logOut() { currentUser = null; }

}
