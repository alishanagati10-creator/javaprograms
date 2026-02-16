import java.util.Scanner;

public class ElectricityBill {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter total units: ");
        int units = input.nextInt();

        
        double bill = (units <= 100) ? (units * 5) :
                     (units <= 200) ? (100 * 5) + (units - 100) * 7 :
                     (100 * 5) + (100 * 7) + (units - 200) * 10;

        System.out.println("The total electricity bill is: Rs. " + bill);
        
        input.close();
    }
}
