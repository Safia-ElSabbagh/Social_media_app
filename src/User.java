public class User {
    private static int idCounter = 1; // Static counter for unique IDs
    private int userId;
    private String userName, password, email;

    // Default constructor
    public User() {
    }

    public User(String userName, String password, String email) {
        this.userId = idCounter++;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }


    public void setUserId(int userId) {
        this.userId = userId; // Assign the passed value
    }
    public int getUserId() {
        return userId; // Return the stored userId
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "User ID: " + userId + ", Username: " + userName + ", Email: " + email;
    }


}
