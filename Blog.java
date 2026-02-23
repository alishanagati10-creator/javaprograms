import java.util.*;
import java.time.LocalDate;

class Blog {
    int id;
    String title;
    String content;
    String author;
    String category;
    LocalDate date;

    Blog(int id, String title, String content, String author, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.date = LocalDate.now();
    }

    void display() {
        System.out.println("-----------------------");
        System.out.println("ID       : " + id);
        System.out.println("Title    : " + title);
        System.out.println("Author   : " + author);
        System.out.println("Category : " + category);
        System.out.println("Date     : " + date);
        System.out.println("Content  : " + content);
    }
}

public class BlogApp {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Blog> blogs = new ArrayList<>();
    static int blogId = 1;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== BLOG APPLICATION ===");
            System.out.println("1. Admin Login");
            System.out.println("2. User View");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> adminMenu();
                case 2 -> userMenu();
                case 3 -> {
                    System.out.println("Thank you!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    
    static void adminMenu() {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Add Blog");
            System.out.println("2. Edit Blog");
            System.out.println("3. Delete Blog");
            System.out.println("4. View All Blogs");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addBlog();
                case 2 -> editBlog();
                case 3 -> deleteBlog();
                case 4 -> viewAllBlogs();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }


    static void userMenu() {
        while (true) {
            System.out.println("\n--- USER MENU ---");
            System.out.println("1. View All Blogs");
            System.out.println("2. View Blog by ID");
            System.out.println("3. Search Blog by Title");
            System.out.println("4. Filter by Category");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> viewAllBlogs();
                case 2 -> viewBlogById();
                case 3 -> searchByTitle();
                case 4 -> filterByCategory();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    
    static void addBlog() {
        sc.nextLine();
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter content: ");
        String content = sc.nextLine();
        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        System.out.print("Enter category: ");
        String category = sc.nextLine();

        blogs.add(new Blog(blogId++, title, content, author, category));
        System.out.println("Blog added successfully!");
    }

    static void editBlog() {
        System.out.print("Enter Blog ID to edit: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Blog b : blogs) {
            if (b.id == id) {
                System.out.print("New Title: ");
                b.title = sc.nextLine();
                System.out.print("New Content: ");
                b.content = sc.nextLine();
                System.out.print("New Category: ");
                b.category = sc.nextLine();
                System.out.println("Blog updated!");
                return;
            }
        }
        System.out.println("Blog not found!");
    }

    static void deleteBlog() {
        System.out.print("Enter Blog ID to delete: ");
        int id = sc.nextInt();

        Iterator<Blog> it = blogs.iterator();
        while (it.hasNext()) {
            if (it.next().id == id) {
                it.remove();
                System.out.println("Blog deleted!");
                return;
            }
        }
        System.out.println("Blog not found!");
    }

    static void viewAllBlogs() {
        if (blogs.isEmpty()) {
            System.out.println("No blogs available.");
            return;
        }
        for (Blog b : blogs) {
            b.display();
        }
    }

    static void viewBlogById() {
        System.out.print("Enter Blog ID: ");
        int id = sc.nextInt();

        for (Blog b : blogs) {
            if (b.id == id) {
                b.display();
                return;
            }
        }
        System.out.println("Blog not found!");
    }

    static void searchByTitle() {
        sc.nextLine();
        System.out.print("Enter title keyword: ");
        String key = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Blog b : blogs) {
            if (b.title.toLowerCase().contains(key)) {
                b.display();
                found = true;
            }
        }
        if (!found) System.out.println("No matching blogs found.");
    }

    static void filterByCategory() {
        sc.nextLine();
        System.out.print("Enter category: ");
        String cat = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Blog b : blogs) {
            if (b.category.toLowerCase().equals(cat)) {
                b.display();
                found = true;
            }
        }
        if (!found) System.out.println("No blogs in this category.");
    }
}
