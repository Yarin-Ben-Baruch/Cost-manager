package il.ac.hit.Model;

import java.util.Objects;

/**
 * A class that holds the existing table data in the database related to the user data.
 */
public class User {

    private String m_Username;
    private String m_Password;

    /**
     * A constructor that updates the user's name and i_Password User expense information.
     * @param i_Username
     * @param i_Password
     */
    public User(String i_Username, String i_Password) {
        setUserName(i_Username);
        setPassword(i_Password);
    }

    public String getUserName() {
        return m_Username;
    }

    public String getPassword() {
        return m_Password;
    }

    private void setUserName(String i_Username) {
        this.m_Username = i_Username;
    }

    private void setPassword(String i_Password) {
        this.m_Password = i_Password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(m_Username, user.m_Username) && Objects.equals(m_Password, user.m_Password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_Username, m_Password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + m_Username + '\'' +
                ", password='" + m_Password + '\'' +
                '}';
    }
}
