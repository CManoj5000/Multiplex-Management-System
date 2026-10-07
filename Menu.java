import java.util.Scanner;

public class Menu {
    CineData data;
    Scanner sc;
    Menu() {
        data = new CineData();
        sc = new Scanner(System.in);
    }
    public void menu() {
        do {
            System.out.println("* * * * * CINEMAX BOOKING SYSTEM * * * * *");
            System.out.println("1. View Seat Map            5. Customers Per Show");
            System.out.println("2. Booking Tickets          6. Search Customer");
            System.out.println("3. Cancel Ticket            7. Weakly Sales Report");
            System.out.println("4. Available Seats          8. Exit");
            System.out.println("Kindly Enter Your Choice : ");
            int ch = sc.nextInt();
            switch(ch) {
                case 1 :
                    ViewSeatMap map = new ViewSeatMap();
                    map.viewSeatMap(data);
                    break;
                case 2 :
                    BookingTicket book = new BookingTicket(sc);
                    book.bookingTicket(data);
                    break;
                case 3 :
                    CancelTicket cancel = new CancelTicket(sc);
                    cancel.cancelTicket(data);
                    break;
                case 4 :
                    AvailableSeats seats = new AvailableSeats(sc);
                    seats.availableSeats(data);
                    break;
                case 5 :
                    CustomersPerShow customers = new CustomersPerShow(sc);
                    customers.customersPerShow(data);
                    break;
                case 6 :
                    SearchCustomer search = new SearchCustomer(sc);
                    search.searchCustomer(data);
                    break;
                case 7 :
                    WeaklySalesReport report = new WeaklySalesReport(sc);
                    report.weaklySalesReport(data);
                    break;
                case 8 :
                    return;
                default :
                    System.out.println("Please Enter A Valid Number ! ");
            }
        } while(true);
    }
}
    