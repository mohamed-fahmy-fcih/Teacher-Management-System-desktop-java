package sample.Utilities;

public class User {
    private static boolean isAdmin;

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
