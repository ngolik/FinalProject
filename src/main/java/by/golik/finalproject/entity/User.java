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
    private String sex;
    private Date registration;
    private List<Rating> ratingList;
    private String image;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;

        if (!Objects.equals(userName, user.userName)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(userType, user.userType)) return false;
        if (!Objects.equals(sex, user.sex)) return false;
        if (!Objects.equals(registration, user.registration)) return false;
        if (!Objects.equals(ratingList, user.ratingList)) return false;
        return Objects.equals(image, user.image);
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (registration != null ? registration.hashCode() : 0);
        result = 31 * result + (ratingList != null ? ratingList.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
