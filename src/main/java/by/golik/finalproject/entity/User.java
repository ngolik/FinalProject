package by.golik.finalproject.entity;

import java.util.Date;
import java.util.Objects;

/**
 * entity represents user
 * @author Nikita Golik
 */
public class User {
    /**
     * name of user(login)
     */
    private String username;
    /**
     * email of user
     */
    private String email;
    /**
     * role of user
     */
    private String role;
    /**
     * date of registration
     */
    private Date registrationDate;

    /**
     * password of user
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;

        if (!Objects.equals(username, user.username)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(role, user.role)) return false;
        return Objects.equals(registrationDate, user.registrationDate);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", registrationDate=" + registrationDate +
                ", password='" + password + '\'' +
                '}';
    }
}
