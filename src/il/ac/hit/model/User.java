package il.ac.hit.model;

import java.util.Objects;

/**
 * A class that holds the existing table data in the database related to the user data.
 */
public class User {

    private String userName;
    private String password;

    /**
     * A constructor that updates the user's name and i_Password User expense information.
     * @param userName New username.
     * @param password The password of the registered user.
     */
    public User(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    /**
     * basic getter.
     * @return String(userName).
     */
    public String getUserName() {
        return userName;
    }

    /**
     * basic getter.
     * @return String(password).
     */
    public String getPassword() {
        return password;
    }

    // basic setter.
    private void setUserName(String userName) {
        this.userName = userName;
    }

    // basic setter.
    private void setPassword(String password) {
        this.password = password;
    }

    /**
     * override to equals, base on User.
     * @param o is User object.
     * @return equals or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(password, user.password);
    }

    /**
     * basic override to hashCode.
     * @return Objects.hash(userName, password).
     */
    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}
