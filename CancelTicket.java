import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class CancelTicket {
    Scanner sc;
    CancelTicket(Scanner sc) {
        this.sc = sc;
    }
    public void cancelTicket() {
        System.out.println("Enter Your Booking Id: ");
        String bookingId = sc.next();
        // Implementation for canceling ticket based on booking ID
        try(Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM bookings WHERE booking_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bookingId);
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected == 1) {
                System.out.println("Booking with ID " + bookingId + " has been cancelled successfully.");
            } else {
                System.out.println("No booking found with ID " + bookingId + ".");
            }
        } catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
