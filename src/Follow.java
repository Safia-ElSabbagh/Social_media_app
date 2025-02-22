public class Follow {
    private final int followerId;
    private final int followingId;

    // Constructor
    public Follow(int followerId, int followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    // Getters (No setters because IDs shouldn't change after creation)
    public int getFollowerId() {
        return followerId;
    }

    public int getFollowingId() {
        return followingId;
    }

    // Override toString() for easy debugging
    @Override
    public String toString() {
        return "Follower ID: " + followerId + " is following User ID: " + followingId;
    }

}