import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Book {
    String title;
    String author;
    int quantity;

    Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }
}
public class LibrarySystem {
    private static Map<String, Book> library = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Books");
            System.out.println("2. Borrow Books");
            System.out.println("3. Return Books");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    borrowBook(scanner);
                    break;
                case 3:
                    returnBook(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Book book = library.get(title);

        if (book != null) {
            book.quantity += quantity;
            System.out.println("Updated the quantity of \"" + title + "\" by " + author + ".");
        } else {
            library.put(title, new Book(title, author, quantity));
            System.out.println("Added \"" + title + "\" by " + author + " to the library.");
        }
    }

    private static void borrowBook(Scanner scanner) {
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine();
        System.out.print("Enter number of books to borrow: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Book book = library.get(title);

        if (book != null && book.quantity >= quantity) {
            book.quantity -= quantity;
            System.out.println("Successfully borrowed " + quantity + " copy/copies of \"" + title + "\".");
        } else if (book == null) {
            System.out.println("The book \"" + title + "\" is not available in the library.");
        } else {
            System.out.println("Not enough copies of \"" + title + "\" to borrow. Available quantity: " + book.quantity);
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
        System.out.print("Enter number of books to return: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Book book = library.get(title);

        if (book != null) {
            book.quantity += quantity;
            System.out.println("Successfully returned " + quantity + " copy/copies of \"" + title + "\".");
        } else {
            System.out.println("The book \"" + title + "\" does not belong to this library.");
        }
    }
}
