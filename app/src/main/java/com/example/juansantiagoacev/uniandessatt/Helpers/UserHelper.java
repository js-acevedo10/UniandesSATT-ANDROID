package com.example.juansantiagoacev.uniandessatt.Helpers;

import com.example.juansantiagoacev.uniandessatt.DTO.User;

/**
 * Created by juansantiagoacev on 5/11/16.
 */
public class UserHelper {

    public static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserHelper.currentUser = currentUser;
    }
}
