import java.util.Scanner;
public class AvailableSeats {
    Scanner sc;
    AvailableSeats(Scanner sc) {
        this.sc = sc;
    }
    public void availableSeats(CineData viewMap) {
        int count = 0;
        for(int i = 0; i < viewMap.seatMap.length; i++) {
            System.out.println("Screen n0 : " + (i + 1));
            for(int j = 0; j < viewMap.seatMap[i].length; j++) {
                for(int k = 0; k < viewMap.seatMap[i][j].length; k++){
                    if(viewMap.seatMap[i][j][k] != "X") {
                        count++;
                        System.out.print(viewMap.seatMap[i][j][k]);
                    }
                }
            }
            System.out.println("\n Available Seats On Screen No : "+(i + 1)+" Is : "+count);
            count = 0;
        }
    }
}
