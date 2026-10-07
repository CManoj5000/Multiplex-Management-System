import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class AvailableSeats {
    Scanner sc;

    AvailableSeats(Scanner sc) {
        this.sc = sc;
    }
    
    public void availableSeats() {
        System.out.print("Enter Show Time (e.g., 10 AM, 1 PM, 4 PM, 7 PM): ");
        sc.nextLine(); // Catches the invisible Enter key
        String showTime = sc.nextLine();
        int totalSeats = 0;
        try(Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("\n--- Availability for " + showTime + " ---");
            // Loop through all 3 screens
            for(int i = 1; i <= 3; i++) {
                String query = "SELECT COUNT(*) AS Customer_count FROM bookings WHERE show_time = ? AND screen_no = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, showTime);
                pstmt.setInt(2, i); 
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()) {
                    int customerCount = rs.getInt("Customer_count");
                    int available = 40 - customerCount;
                    System.out.println("Available Seats On Screen No " + i + " : " + available);
                    totalSeats += available; // Add this screen's availability to the total
                }
            }
            System.out.println("----------------------------------------");
            System.out.println("Total Available Seats In Multiplex: " + totalSeats);
            System.out.println("----------------------------------------\n");
        } 
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}