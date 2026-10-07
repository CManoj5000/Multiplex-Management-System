import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CustomersPerShow {

    public void customersPerShow() {
        System.out.println("\n--- CUSTOMERS PER SHOW REPORT ---");
        String[] shows = {"10 AM", "1 PM", "4 PM", "7 PM", "10 PM"};
        String[][] customers = new String[5][];
        try(Connection conn = DatabaseConnection.getConnection()) {
            String query_count = "SELECT count(*) AS Customer_count FROM bookings WHERE show_time = ?";
            String query_names = "SELECT customer_name FROM bookings WHERE show_time = ?";
            for(int i = 0; i < shows.length; i++) {
                PreparedStatement pstmt = conn.prepareStatement(query_count);
                pstmt.setString(1, shows[i]);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()) {
                    int customerCount = rs.getInt("Customer_count");
                    customers[i] = new String[customerCount];
                    // Fetch customer names
                    PreparedStatement pstmt_names = conn.prepareStatement(query_names);
                    pstmt_names.setString(1, shows[i]);
                    ResultSet rs_names = pstmt_names.executeQuery();
                    int j = 0;
                    while(rs_names.next() && j < customerCount) {
                        customers[i][j] = rs_names.getString("customer_name");
                        j++;
                    }
                }
            }
            for(int j = 0; j < shows.length; j++) {
                    System.out.println("Show Time: " + shows[j] + " | Customer Count: " + customers[j].length);
                }
                int maxy = Integer.MIN_VALUE;
                int miny = Integer.MAX_VALUE;
                String max_ShowTime = "";
                String min_ShowTime = "";
                System.out.println("\n--- MAXIMUM AND MINIMUM CUSTOMERS PER SHOW ---");
                for(int j = 0; j < shows.length; j++) {
                    if(customers[j].length > maxy){
                        maxy = customers[j].length;
                        max_ShowTime = shows[j];
                    }
                    if(customers[j].length < miny) {
                        miny = customers[j].length;
                        min_ShowTime = shows[j];
                    }
                }
                System.out.println("Maximum customers in " + max_ShowTime + " Of: " + maxy);
                System.out.println("Minimum customers in " + min_ShowTime + " Of: " + miny);
                String longestName = "";
                for(int j = 0; j < shows.length; j++) {
                    for(String name : customers[j]) {
                        if(name.length() > longestName.length()) longestName = name;
                    }
                }
                System.out.println("Longest customer name: " + longestName);
        }
        catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
