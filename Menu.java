import java.util.Scanner;

public class Menu {
    public void menu() {
        Scanner sc = new Scanner(System.in);
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
                    map.viewSeatMap();
                    break;
                case 2 :
                    BookingTicket book = new BookingTicket();
                    book.bookingTicket();
                    break;
                case 3 :
                    CancelTicket cancel = new CancelTicket();
                    cancel.cancelTicket();
                    break;
                case 4 :
                    AvailableSeats seats = new AvailableSeats();
                    seats.availableSeats();
                    break;
                case 5 :
                    CustomersPerShow customers = new CustomersPerShow();
                    customers.customersPerShow();
                    break;
                case 6 :
                    SearchCustomer search = new SearchCustomer();
                    search.searchCustomer();
                    break;
                case 7 :
                    WeaklySalesReport report = new WeaklySalesReport();
                    report.weaklySalesReport();
                    break;
                case 8 :
                    return;
                default :
                    System.out.println("Please Enter A Valid Number ! ");
            }
        } while(true);
    }
}
    