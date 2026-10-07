import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BookingTicket {
    Scanner sc;

    public BookingTicket(Scanner sc) {
        this.sc = sc;
    }

    public void bookingTicket() {
        System.out.println("\n--- BOOK YOUR TICKET ---");
        
        // 1. Get Input (with a fix for the scanner bug)
        System.out.print("Customer Name: ");
        String name = sc.nextLine();
        if (name.trim().isEmpty()) name = sc.nextLine(); 
        
        System.out.print("Email: ");
        String email = sc.nextLine();
        
        System.out.print("Phone Number (10 digits): ");
        String phone = sc.nextLine();
        
        System.out.print("Movie Name: ");
        String movie = sc.nextLine();
        
        System.out.print("Show Time (e.g., 10 AM, 1 PM, 4 PM): ");
        String time = sc.nextLine();
        
        System.out.print("Screen No (1-3): ");
        int screen = sc.nextInt();
        
        System.out.print("Row (A-E): ");
        char row = sc.next().toUpperCase().charAt(0);
        
        System.out.print("Seat No (1-8): ");
        int seat = sc.nextInt();
        sc.nextLine(); // Consume newline for next time

        // 2. Validate Range
        if (screen < 1 || screen > 3 || row < 'A' || row > 'E' || seat < 1 || seat > 8) {
            System.out.println("Invalid input! Screens (1-3), Rows (A-E), Seats (1-8).");
            return;
        }

        // 3. Generate Booking ID (PDF Rule: 3 letters movie + screen + row + seat + last 4 phone)
        String moviePrefix = movie.length() >= 3 ? movie.substring(0, 3).toUpperCase() : movie.toUpperCase();
        String phoneSuffix = phone.length() >= 4 ? phone.substring(phone.length() - 4) : "0000";
        String bookingId = moviePrefix + screen + row + seat + "-" + phoneSuffix;

        // 4. Check if seat is free and Save to Database
        try (Connection conn = DatabaseConnection.getConnection()) {
            
            // Check if already booked
            String checkQuery = "SELECT * FROM bookings WHERE screen_no = ? AND seat_row = ? AND seat_no = ? AND show_time = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, screen);
            checkStmt.setString(2, String.valueOf(row));
            checkStmt.setInt(3, seat);
            checkStmt.setString(4, time);
            
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                System.out.println("Sorry, Seat " + row + seat + " on Screen " + screen + " is already booked for " + time + "!");
                return;
            }

            // Insert new booking
            String insertQuery = "INSERT INTO bookings (booking_id, customer_name, email, phone, movie_name, show_time, screen_no, seat_row, seat_no) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, bookingId);
            insertStmt.setString(2, name);
            insertStmt.setString(3, email);
            insertStmt.setString(4, phone);
            insertStmt.setString(5, movie);
            insertStmt.setString(6, time);
            insertStmt.setInt(7, screen);
            insertStmt.setString(8, String.valueOf(row));
            insertStmt.setInt(9, seat);
            
            insertStmt.executeUpdate();
            
            System.out.println("\nTicket Booked Successfully!");
            System.out.println("Your Booking ID is: " + bookingId);
            
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}