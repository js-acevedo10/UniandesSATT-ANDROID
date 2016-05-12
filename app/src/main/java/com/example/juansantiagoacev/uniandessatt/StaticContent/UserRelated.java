package com.example.juansantiagoacev.uniandessatt.StaticContent;

import com.example.juansantiagoacev.uniandessatt.DAO.User;

/**
 * Created by juansantiagoacev on 5/11/16.
 */
public class UserRelated {

    public static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserRelated.currentUser = currentUser;
    }
}
