package student_system;

public class User {
    private String Username;
    private String password;
    private String UserIdcard;
    private String Phonenumber;

    public User() {
    }

    public User(String username, String password, String userIdcard, String phonenumber) {
        Username = username;
        this.password = password;
        UserIdcard = userIdcard;
        Phonenumber = phonenumber;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserIdcard() {
        return UserIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        UserIdcard = userIdcard;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }
}
