import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private final String id;
    private final String title;
    private final String author;
    private boolean available;

    public Book(String id, String title, String author) {
        if (id == null || id.length() != 5) {
            throw new IllegalArgumentException("Book ID must be exactly 5 characters long.");
        }

        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() {
        if (!available) {
            throw new IllegalStateException("Book is already borrowed.");
        }
        available = false;
    }

    public void returnBook() {
        available = true;
    }

    public void printInfo() {
        String status = available ? "Available" : "Borrowed";
        System.out.printf("%-8s %-30s %-25s %-10s%n", id, title, author, status);
    }
}

class Member {
    private final String memberId;
    private final String name;
    private final List<Book> borrowedBooks = new ArrayList<>();

    public Member(String memberId, String name) {
        if (memberId == null || memberId.length() != 6) {
            throw new IllegalArgumentException("Member ID must be exactly 6 characters long.");
        }

        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public void borrowBook(Book book) {
        if (borrowedBooks.size() >= 3) {
            throw new IllegalStateException("Borrow limit reached. A member can borrow up to 3 books.");
        }

        book.borrow();
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        if (!borrowedBooks.contains(book)) {
            throw new IllegalArgumentException("This book was not borrowed by this member.");
        }

        borrowedBooks.remove(book);
        book.returnBook();
    }

    public void printInfo() {
        System.out.printf("%-10s %-25s Borrowed books: ", memberId, name);

        if (borrowedBooks.isEmpty()) {
            System.out.println("None");
        } else {
            for (Book book : borrowedBooks) {
                System.out.print(book.getId() + " ");
            }
            System.out.println();
        }
    }
}

class Library {
    private final String name;
    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();

    public Library(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        if (findBookById(book.getId()) != null) {
            System.out.println("A book with this ID already exists.");
            return;
        }

        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void addMember(Member member) {
        if (findMemberById(member.getMemberId()) != null) {
            System.out.println("A member with this ID already exists.");
            return;
        }

        members.add(member);
        System.out.println("Member added successfully.");
    }

    public Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    public void searchBook(String bookId) {
        Book book = findBookById(bookId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        printBookHeader();
        book.printInfo();
    }

    public void borrowBook(String memberId, String bookId) {
        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        try {
            member.borrowBook(book);
            System.out.println("Book borrowed successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void returnBook(String memberId, String bookId) {
        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        try {
            member.returnBook(book);
            System.out.println("Book returned successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void printBooks() {
        System.out.println("\nBooks in " + name);
        printBookHeader();

        for (Book book : books) {
            book.printInfo();
        }
    }

    public void printMembers() {
        System.out.println("\nLibrary Members");
        System.out.println("------------------------------------------------------------");

        for (Member member : members) {
            member.printInfo();
        }
    }

    private void printBookHeader() {
        System.out.printf("%-8s %-30s %-25s %-10s%n", "ID", "Title", "Author", "Status");
        System.out.println("----------------------------------------------------------------------------");
    }
}

public class Lib {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library("Central Library");

    public static void main(String[] args) {
        seedData();

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    library.printBooks();
                    break;
                case 2:
                    library.printMembers();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    addMember();
                    break;
                case 5:
                    searchBook();
                    break;
                case 6:
                    borrowBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    System.out.println("Thank you for using the Library Management System.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 8.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n========================================");
        System.out.println("       Library Management System");
        System.out.println("========================================");
        System.out.println("1. View all books");
        System.out.println("2. View all members");
        System.out.println("3. Add a new book");
        System.out.println("4. Add a new member");
        System.out.println("5. Search book by ID");
        System.out.println("6. Borrow a book");
        System.out.println("7. Return a book");
        System.out.println("8. Exit");
        System.out.println("----------------------------------------");
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static void addBook() {
        String id = readText("Enter book ID (5 characters): ");
        String title = readText("Enter book title: ");
        String author = readText("Enter author name: ");

        try {
            library.addBook(new Book(id, title, author));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addMember() {
        String id = readText("Enter member ID (6 characters): ");
        String name = readText("Enter member name: ");

        try {
            library.addMember(new Member(id, name));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchBook() {
        String bookId = readText("Enter book ID: ");
        library.searchBook(bookId);
    }

    private static void borrowBook() {
        String memberId = readText("Enter member ID: ");
        String bookId = readText("Enter book ID: ");
        library.borrowBook(memberId, bookId);
    }

    private static void returnBook() {
        String memberId = readText("Enter member ID: ");
        String bookId = readText("Enter book ID: ");
        library.returnBook(memberId, bookId);
    }

    private static void seedData() {
        library.addBook(new Book("12345", "Great Student", "Mosawer Wadan"));
        library.addBook(new Book("54321", "Learning Java", "Mohammad"));
        library.addBook(new Book("01234", "Git and GitHub", "Ahmad"));
        library.addBook(new Book("02345", "Easy Java Programming", "Ali"));
        library.addBook(new Book("03456", "How to Become a Teacher", "Wadan"));

        library.addMember(new Member("000001", "Massoud Wadan"));
        library.addMember(new Member("000002", "Mohammad Wadan"));
    }
}
