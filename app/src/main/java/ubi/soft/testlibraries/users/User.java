package ubi.soft.testlibraries.users;

import java.util.Date;

public class User {

    private String email;
    private Date birthDate;

    public User(String email, Date birthDate) {
        this.email = email;
        this.birthDate = birthDate;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public User withBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}

