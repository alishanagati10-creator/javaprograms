import java.util.Scanner;


class PaymentMethod {
    void pay() {
        System.out.println("Payment method not selected");
    }
}


class UPI extends PaymentMethod {
    @Override
    void pay() {
        System.out.println("Payment done using UPI");
    }
}


class CreditCard extends PaymentMethod {
    @Override
    void pay() {
        System.out.println("Payment done using Credit Card");
    }
}


class Cash extends PaymentMethod {
    @Override
    void pay() {
        System.out.println("Payment done using Cash");
    }
}


public class PaymentMethodTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PaymentMethod payment; // reference of parent class

        System.out.println("Choose Payment Method:");
        System.out.println("1. UPI");
        System.out.println("2. Credit Card");
        System.out.println("3. Cash");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();

        
        if (choice == 1) {
            payment = new UPI();
        } else if (choice == 2) {
            payment = new CreditCard();
        } else if (choice == 3) {
            payment = new Cash();
        } else {
            payment = new PaymentMethod();
        }

        
        payment.pay();

        sc.close();
    }
}
