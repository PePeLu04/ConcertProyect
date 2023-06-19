package org.proyect.Model.Domain;

import java.util.Objects;

public class User extends Person{
    private String username;
    private String password;
    private String role;

    private String name_band;

    public User(String id, String dni, String username, String password, String role, String name_band) {
        super(id, dni);
        this.username = username;
        this.password = password;
        this.role = role;
        this.name_band = name_band;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName_band() {
        return name_band;
    }

    public void setName_band(String name_band) {
        this.name_band = name_band;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
