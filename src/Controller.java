public class Controller {

    private JDBC dbManager; // Object to interact with the database

    public Controller() {
        // Initialize database connection
        this.dbManager = new JDBC();
    }

    // ✅ Register a new user after validation
    public boolean registerUser(String username, String password, String email) {
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            System.out.println("❌ Registration failed: Info cannot be empty.");
            return false;
        }
        if (password.length() < 6) {
            System.out.println("❌ Password must be at least 6 characters.");
            return false;
        }

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);

        boolean success = dbManager.registerUser(user);
        if (success) {
            System.out.println("✅ Registration successful!");
        } else {
            System.out.println("❌ Registration failed.");
        }
        return success;
    }

}
