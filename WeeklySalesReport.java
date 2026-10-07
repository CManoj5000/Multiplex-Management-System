import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class WeeklySalesReport {
    public void weeklySalesReport() {

        String[] movies = {"Avengers", "Batman", "Inception", "Matrix"};
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] prices = {250, 300, 350, 200};
        int[][] sales = new int[4][7];

        // Get data from database
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT movie_name, day_of_week, tickets_sold FROM weekly_sales";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String movie = rs.getString("movie_name");
                String day = rs.getString("day_of_week");
                int tickets = rs.getInt("tickets_sold");
                int movieIndex = -1;
                int dayIndex = -1;
                for (int i = 0; i < movies.length; i++)
                    if (movies[i].equals(movie))
                        movieIndex = i;
                for (int j = 0; j < days.length; j++)
                    if (days[j].equals(day))
                        dayIndex = j;
                sales[movieIndex][dayIndex] = tickets;
            }
        } 
        catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return;
        }
        System.out.println("\n * * * * * * * * * *  WEEKLY SALES REPORT * * * * * * * * * *");
        // Header
        System.out.printf("%-12s", "Movies");
        for (String day : days)
            System.out.printf("%-8s", day.substring(0, 3));
        System.out.printf("%-8s %-12s%n", "Total", "Revenue");
        System.out.println("------------------------------------------------------------------------------------");

        // Movie rows
        int maxMovieTickets = -1;
        String bestMovie = "";

        for (int i = 0; i < movies.length; i++) {
            System.out.printf("%-12s", movies[i]);
            int movieCollections = 0;
            for (int j = 0; j < days.length; j++) {
                System.out.printf("%-8d", sales[i][j]);
                movieCollections += sales[i][j];
            }
            long revenue = (long) movieCollections * prices[i];
            System.out.printf("%-8d Rs. %d%n", movieCollections, revenue);
            if (movieCollections > maxMovieTickets) {
                maxMovieTickets = movieCollections;
                bestMovie = movies[i];
            }
        }
        // Day totals
        System.out.printf("%-12s", "Day Total");

        int maxDayTickets = -1;
        String busiestDay = "";

        for (int j = 0; j < days.length; j++) {
            int dayCollections = 0;
            for (int i = 0; i < movies.length; i++)
                dayCollections += sales[i][j];
            System.out.printf("%-8d", dayCollections);
            if (dayCollections > maxDayTickets) {
                maxDayTickets = dayCollections;
                busiestDay = days[j];
            }
        }
        System.out.println("\nBest-Selling Movie: " + bestMovie + " (" + maxMovieTickets + " tickets)");
        System.out.println("Busiest Day: " + busiestDay + " (" + maxDayTickets + " tickets)");

        // Transpose
        System.out.println("\n======= TRANSPOSE ========");
        System.out.printf("%-12s", "Day");
        for (String movie : movies)
            System.out.printf("%-12s", movie);
        System.out.println();
        for (int j = 0; j < days.length; j++) {
            System.out.printf("%-12s", days[j]);
            for (int i = 0; i < movies.length; i++) {
                System.out.printf("%-12d", sales[i][j]);
            }
            System.out.println();
        }
    }
}