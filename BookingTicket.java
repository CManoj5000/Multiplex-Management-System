import java.util.Scanner;

public class BookingTicket {
    String name;
    String phoneNo;
    String movieName;
    int screen;
    String seatNo;
    String bookingId;
    Scanner sc;
    BookingTicket(Scanner sc) {
        this.sc = sc;
        System.out.println("Kindly Enter The Details Given Below To Plcae Your Ticket ! ");
        System.out.println("Name : ");
        name = sc.next();
        System.out.println("Phone Number : ");
        phoneNo = sc.next();
        System.out.println("Movie Name : ");
        System.out.println("Screen No : ");
        screen = sc.nextInt();
        System.out.println("Seat No : ");
        seatNo = sc.next();
        System.out.println("Booking Id : ");
        bookingId = sc.next();
        }
    public void bookingTicket(CineData data) {
        String key = seatNo;
        for(int i = 0; i < data.seatMap.length; i++) {
            for(int j = 0; j < data.seatMap[i].length; j++) {
                if(key ==  data.seatMap[screen][i][j])
                    data.seatMap[screen][i][j] = "X";
            }
        }
        
    }

}
