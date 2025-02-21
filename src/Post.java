import java.time.LocalDateTime;

public class Post {
    private int postId, userId;
    private String content;
    private LocalDateTime timeStamp;

    // Constructor
    public Post(int postId, int userId, String content, LocalDateTime timeStamp) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    // Getters and Setters
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    // Override toString() to display post details easily
    @Override
    public String toString() {
        return "Post ID: " + postId + ", User ID: " + userId + ", Content: \"" + content + "\", Timestamp: " + timeStamp;
    }
}
