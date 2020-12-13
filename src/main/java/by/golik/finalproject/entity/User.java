package by.golik.finalproject.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Nikita Golik
 */
public class User {
    private String userName;
    private String email;
    private String userType;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }



    public Date getRegistration() {
        return registrationDate;
    }

    public void setRegistration(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;

        if (!Objects.equals(userName, user.userName)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(userType, user.userType)) return false;
        return Objects.equals(registrationDate, user.registrationDate);
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        return result;
    }
}
