import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ViewSeatMap {
    Scanner sc;
    
    public ViewSeatMap(Scanner sc) {
        this.sc = sc;
    }

    public void viewSeatMap() {
        System.out.print("Enter Show Time to view seats (e.g., 10 AM, 1 PM): ");
        sc.nextLine();
        String showTime = sc.nextLine();
        String[][][] seatMap = new String[3][5][8];
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 8; k++) {
                    seatMap[i][j][k] = (char)('A' + j) + String.valueOf(k + 1); 
                }
            }
        }
        String query = "SELECT screen_no, seat_row, seat_no FROM bookings WHERE show_time = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, showTime);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int screenIndex = rs.getInt("screen_no") - 1;
                int rowIndex = rs.getString("seat_row").charAt(0) - 'A';
                int colIndex = rs.getInt("seat_no") - 1;
                
                seatMap[screenIndex][rowIndex][colIndex] = "X"; 
            }
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
            return;
        }
        System.out.println("\n--- MULTIPLEX SEAT MAP FOR " + showTime + " ---");
        for(int i = 0; i < seatMap.length; i++) {
            System.out.println("\nScreen No : " + (i + 1));
            for(int j = 0; j < seatMap[i].length; j++) {
                for(int k = 0; k < seatMap[i][j].length; k++){
                    System.out.print(seatMap[i][j][k] + "\t");
                }
                System.out.println();
            }
        }
        System.out.println("--------------------------------------\n");
    }
}