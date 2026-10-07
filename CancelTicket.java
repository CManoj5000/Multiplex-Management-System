import java.util.Scanner;

public class CancelTicket {
    Scanner sc;
    CancelTicket(Scanner sc) {
        this.sc = sc;
    }
    public void cancelTicket(CineData data) {
        System.out.println("Enter The Screen & Seats No's To Cancel : ");
        int screen = sc.nextInt() - 1;
        String seatNo = sc.next();
        int row = seatNo.charAt(0) - 'A';
        int col = Integer.parseInt(seatNo.charAt(1) + "");
        data.seatMap[screen][row][col] = seatNo;
        System.out.println("Ticket : "+seatNo+" On Screen : "+(screen + 1) + "Is Cancelled Successfully !");
    }
}
