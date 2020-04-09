package ru.sibsutis;

public abstract class User {
    protected String name;
    protected String email;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String email) {
        this(name);
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }
}
