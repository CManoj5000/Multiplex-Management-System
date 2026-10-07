import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class SearchCustomer {
    Scanner sc;
    SearchCustomer(Scanner sc) {
        this.sc = sc;
    } 
    public void searchCustomer() {
        System.out.println("Please Enter Your Booking ID : ");
        String key = sc.next();
        try(Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT customer_name, show_time FROM bookings WHERE booking_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                System.out.println("A Customer found With Booking ID : "+key);
                System.out.println("Customer Name: " + rs.getString("customer_name"));
                System.out.println("Show Time: " + rs.getString("show_time"));
            } else {
                System.out.println("No Customer Found With This Booking ID : "+key);
            }
        } catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}