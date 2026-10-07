public class Main {
    public static void main(String[] args) {
        try {
    DatabaseConnection.getConnection();
    System.out.println("Database connected successfully!");
    } catch (Exception e) {
    System.out.println("Connection failed: " + e.getMessage());
    }   
        Menu obj = new Menu();
        obj.menu();
    }
}
