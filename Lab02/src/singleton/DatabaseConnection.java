package singleton;

public class DatabaseConnection {

    // single instance
    private static DatabaseConnection instance;

    // private constructor
    private DatabaseConnection() {
        System.out.println("Database connection created");
    }

    // get instance method
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Connecting to the database...");
    }
}
