package il.ac.hit;

/**
 * A class that holds the existing table data in the database related to the user data.
 */
public class User {

    private String userName;
    private String password;

    /**
     * A constructor that updates the user's name and password User expense information.
     * @param userName
     * @param password
     */
    public User(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
