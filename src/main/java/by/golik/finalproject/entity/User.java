package by.golik.finalproject.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * entity represents user
 * @author Nikita Golik
 */
public class User {
    /**
     * name of user(login)
     */
    private String userName;
    /**
     * email of user
     */
    private String email;
    /**
     * role of user
     */
    private Role role;
    /**
     * date of registration
     */
    private Date registrationDate;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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

        if (!Objects.equals(userName, user.userName)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(role, user.role)) return false;
        return Objects.equals(registrationDate, user.registrationDate);
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        return result;
    }
}
