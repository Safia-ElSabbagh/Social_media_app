import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        JDBC dbManager = new JDBC();
        Controller controller = new Controller();
        int choice;

        int userId = 0;
        boolean loggedIn = false;
        String loggedInUser = null;

        System.out.println("\n════════════════════════════════════");
        System.out.println("    🌐 Welcome to Mini Social App    ");
        System.out.println("════════════════════════════════════");

        while (true) {
            // Display menu
            System.out.println("[1] Register");
            System.out.println("[2] Login");
            if (loggedIn) {  // Show these options only if logged in
                System.out.println("[3] Post a Message");
                System.out.println("[4] View My Posts");
                System.out.println("[5] Follow a User");
                System.out.println("[6] Unfollow a User");
                System.out.println("[7] Logout");
            }
            System.out.println("[0] Exit");
            System.out.println("════════════════════════════════════");
            System.out.print("👉 Select an option: ");

            // Handle user input safely
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                continue;
            }

            // Handle menu choices
            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    if (controller.registerUser(username, password, email)) {
                        userId = JDBC.getUserId(username);
                    }
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    if (controller.loginUser(loginUsername, loginPassword)) {
                        loggedIn = true;
                        loggedInUser = loginUsername;
                        userId = JDBC.getUserId(loginUsername);
                    }
                    break;

                case 3:
                    if (!loggedIn) {
                        System.out.println("⚠️ You need to log in first.");
                        break;
                    }
                    System.out.print("📝 Enter your post: ");
                    String content = scanner.nextLine();
                    if (controller.addPost(userId, content)) {
                    }
                    break;

                case 4:
                    if (!loggedIn) {
                        System.out.println("⚠️ You need to log in first.");
                        break;
                    }
                    controller.GetPostByUser(userId);
                    break;

                case 5:
                    if (!loggedIn) {
                        System.out.println("⚠️ You need to log in first.");
                        break;
                    }
                    System.out.print("👤 Enter username to follow: ");
                    String followUsername = scanner.nextLine();
                    int followId = JDBC.getUserId(followUsername);
                    if (controller.followUser(userId, followId)) {
                        System.out.println("✅ You are now following " + followUsername);
                    }
                    break;

                case 6:
                    if (!loggedIn) {
                        System.out.println("⚠️ You need to log in first.");
                        break;
                    }
                    System.out.print("👤 Enter username to unfollow: ");
                    String unfollowUsername = scanner.nextLine();
                    int unfollowId = JDBC.getUserId(unfollowUsername);
                    if (controller.unfollowUser(userId, unfollowId)) {
                        System.out.println("✅ You have unfollowed " + unfollowUsername);
                    }
                    break;

                case 7:
                    loggedIn = false;
                    loggedInUser = null;
                    System.out.println("🔒 You have logged out.");
                    break;

                case 0:
                    System.out.println("👋 Thank you for using Mini Social App! Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("⚠️ Invalid option. Please select a valid number.");
            }
        }
    }
}
