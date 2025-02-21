import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JDBC {

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/social_media",
                "root",
                "fatom-14102017"
        );
    }

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword()); // ⚠ Hash the password before storing!
            pstmt.setString(3, user.getEmail());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password); // ⚠ Compare hashed password in a real app

            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addPost(Post post) {
        String sql = "INSERT INTO posts (userId, content) VALUES (?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, post.getUserId());
            pstmt.setString(2, post.getContent());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Post> getPostsByUser(int userId) {
        String sql = "SELECT * FROM posts WHERE userId = ? ORDER BY timeStamp DESC"; // Sorting by latest timestamp
        List<Post> posts = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                int postId = resultSet.getInt("postId");
                String content = resultSet.getString("content");
                LocalDateTime timeStamp = resultSet.getTimestamp("timeStamp").toLocalDateTime(); // Convert SQL timestamp to Java LocalDateTime

                Post post = new Post(postId, userId, content, timeStamp);
                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts; // Return list of posts (empty if none found)
    }


    public boolean deletePost(int postId) {
        String sql = "DELETE FROM posts WHERE postId =?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, postId);

            int rowsAffected = pstmt.executeUpdate(); // FIXED: use executeUpdate()
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean followUser(int followerId, int followingId) {
        String sql = "INSERT INTO followers (followerId, followingId) VALUES (?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, followerId);
            pstmt.setInt(2, followingId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unfollowUser(int followerId, int followingId) {
        String sql = "DELETE FROM followers WHERE followerId=? AND followingId=?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, followerId);
            pstmt.setInt(2, followingId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
