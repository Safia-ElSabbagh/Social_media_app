import java.util.List;

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

    // ✅ User login
    public boolean loginUser(String username, String password) {
        boolean isAuthenticated = dbManager.loginUser(username, password);
        if (isAuthenticated) {
            System.out.println("✅ Login successful!");
        } else {
            System.out.println("❌ Incorrect username or password.");
        }
        return isAuthenticated;
    }


    // ✅ Create a new post
    public boolean addPost(int userId, String content) {

        if (content.isEmpty()) {
            System.out.println("❌ Post content cannot be empty.");
            return false;
        }

        Post post = new Post();
        post.setUserId(userId);
        post.setContent(content);

        boolean success = dbManager.addPost(post);
        if (success) {
            System.out.println("✅ Post created successfully!");
        } else {
            System.out.println("❌ Failed to create post.");
        }
        return success;
    }

    // ✅ display user posts (sorted by timestamp)
    public void GetPostByUser(int userId) {
        List<Post> userPosts = dbManager.getPostsByUser(userId);
        if (userPosts.isEmpty()) {
            System.out.println("📢 No posts found for this user.");
        }

    }


    // ✅ Follow a user
    public boolean followUser(int followerId, int followingId) {
        if (followerId == followingId) {
            System.out.println("❌ You cannot follow yourself.");
            return false;
        }

        boolean success = dbManager.followUser(followerId, followingId);
        if (success) {
            System.out.println("✅ Successfully followed the user.");
        } else {
            System.out.println("❌ Failed to follow. You may already be following this user.");
        }
        return success;
    }

    // ✅ Unfollow a user
    public boolean unfollowUser(int followerId, int followingId) {
        boolean success = dbManager.unfollowUser(followerId, followingId);
        if (success) {
            System.out.println("✅ Successfully unfollowed the user.");
        } else {
            System.out.println("❌ Failed to unfollow. You may not be following this user.");
        }
        return success;
    }



}
