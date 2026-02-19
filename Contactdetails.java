import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Contact {
    String phoneNumber;

    public Contact(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Phone: " + phoneNumber;
    }
}

class ContactNotFoundException extends Exception {
    public ContactNotFoundException(String message) {
        super(message);
    }
}

public class ContactBook {
    private Map<String, Contact> contacts = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public ContactBook() {
        addNumbers();
    }

    public void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        if (contacts.containsKey(name)) {
            System.out.println("Contact already exists!");
            return;
        }
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        contacts.put(name, new Contact(phoneNumber));
        System.out.println("Contact added!");
    }

    public void deleteContact() throws ContactNotFoundException {
        System.out.print("Enter name to delete: ");
        String name = scanner.nextLine();
        if (!contacts.containsKey(name)) {
            throw new ContactNotFoundException("Contact not found!");
        }
        contacts.remove(name);
        System.out.println("Contact deleted!");
    }

    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts!");
            return;
        }
        for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void addNumbers() {
        contacts.put("Rahul", new Contact("9999999999"));
        contacts.put("Priya", new Contact("8888888888"));
        contacts.put("Amit", new Contact("7777777777"));
        contacts.put("Sneha", new Contact("6666666666"));
        contacts.put("Rohit", new Contact("5555555555"));
        contacts.put("Nisha", new Contact("4444444444"));
        contacts.put("Vikas", new Contact("3333333333"));
        contacts.put("Sonia", new Contact("2222222222"));
        contacts.put("Abhishek", new Contact("1111111111"));
        contacts.put("Manoj", new Contact("1010101010"));
    }

    public void run() {
        while (true) {
            System.out.println("\n1. Add Contact");
            System.out.println("2. Delete Contact");
            System.out.println("3. Display Contacts");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());
            try {
                switch (choice) {
                    case 1 -> addContact();
                    case 2 -> deleteContact();
                    case 3 -> displayContacts();
                    case 4 -> {
                        System.out.println("Bye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (ContactNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new ContactBook().run();
    }
}
