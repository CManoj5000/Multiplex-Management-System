import java.util.Scanner;

public class SearchCustomer {
    Scanner sc;
    SearchCustomer(Scanner sc) {
        this.sc = sc;
    } 
    public void searchCustomer(CineData data) {
        System.out.println("Please Enter Your Name : ");
        String key = sc.next();
        for(int i = 0; i < data.persons.length; i++) {
            for(int j = 0; j < data.persons[i].length; j++) {
                if(data.persons[i][j] == key) {
                    System.out.println("A Customer found At : "+data.time[i]);
                    return;
                }
            }
            System.out.println("No Customer Found With This Name : "+key);
        }
    }
}