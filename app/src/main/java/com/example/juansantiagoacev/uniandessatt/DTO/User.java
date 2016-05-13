package com.example.juansantiagoacev.uniandessatt.DTO;

/**
 * Created by juansantiagoacev on 5/11/16.
 */
public class User {
    public boolean auth;
    public String role, name, id, accessToken;

    public User() {
    }

    public User(boolean auth, String role, String name, String id, String accessToken) {
        this.auth = auth;
        this.role = role;
        this.name = name;
        this.id = id;
        this.accessToken = accessToken;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return name + " | " + role;
    }
}
