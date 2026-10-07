import java.util.Scanner;

public class CustomersPerShow {
    Scanner sc;
    CustomersPerShow(Scanner sc) {
        this.sc = sc;
    }     

    public void customersPerShow(CineData data) {
        int customerCount = 0;
        for(int i = 0; i < data.persons.length; i++) {
            customerCount = 0;
            for(int j = 0; j < data.persons[i].length; j++) {
                if(data.persons[i][j] != null) customerCount++;
            }
            System.out.println("The No.Of Customers Attended Show On : "+data.time[i]+" iIs : "+customerCount);
        }
    }
}
