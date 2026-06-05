import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String id, String title, String author) {
        if (id.length() != 5) {
            throw new IllegalArgumentException("Book ID must be exactly 5 characters long.");
        }
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }

    public void borrow() {
        if (isAvailable) isAvailable = false;
        else throw new IllegalStateException("Book is not available.");
    }

    public void returnBook() { isAvailable = true; }

    public void printBookInfo() {
        String status = isAvailable ? "Available" : "Borrowed";
        System.out.println("ID: " + id + " | Title: " + title + " | Author: " + author + " | Status: " + status);
    }
}

class Member {
    private String memberId;
    private String name;
    private List<Book> borrowedBooks;

    public Member(String memberId, String name) {
        if (memberId.length() != 6) {
            throw new IllegalArgumentException("Member ID must be exactly 6 characters long.");
        }
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }

    public void borrowBook(Book book) {
        if (borrowedBooks.size() >= 3)
            throw new IllegalStateException("Borrow limit reached. A member can borrow up to 3 books.");
        if (!book.isAvailable())
            throw new IllegalStateException("This book is already borrowed.");
        borrowedBooks.add(book);
        book.borrow();
    }

    public void returnBook(Book book) {
        if (!borrowedBooks.contains(book))
            throw new IllegalArgumentException("This book was not borrowed by this member.");
        borrowedBooks.remove(book);
        book.returnBook();
    }

    public void printMemberInfo() {
        System.out.print("Member ID: " + memberId + " | Name: " + name + " | Borrowed Books: ");
        if (borrowedBooks.isEmpty()) System.out.println("None");
        else {
            for (Book book : borrowedBooks) System.out.print(book.getTitle() + " ");
            System.out.println();
        }
    }
}

class Library {
    private String name;
    private List<Book> books;
    private List<Member> members;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) { books.add(book); }
    public void addMember(Member member) { members.add(member); }

    public Book findBookById(String bookId) {
        for (Book book : books) if (book.getId().equals(bookId)) return book;
        return null;
    }

    public Member findMemberById(String memberId) {
        for (Member member : members) if (member.getMemberId().equals(memberId)) return member;
        return null;
    }

    public void borrowBook(String memberId, String bookId) {
        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);

        if (member == null) { System.out.println("Member not found."); return; }
        if (book == null) { System.out.println("Book not found."); return; }

        try {
            member.borrowBook(book);
            System.out.println("Book borrowed successfully.");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    public void returnBook(String memberId, String bookId) {
        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);

        if (member == null) { System.out.println("Member not found."); return; }
        if (book == null) { System.out.println("Book not found."); return; }

        try {
            member.returnBook(book);
            System.out.println("Book returned successfully.");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    public void printBooks() {
        System.out.println("\nBooks in Library:");
        for (Book book : books) book.printBookInfo();
    }

    public void printMembers() {
        System.out.println("\nMembers of Library:");
        for (Member member : members) member.printMemberInfo();
    }

    public void printLibraryInfo() {
        System.out.println("\nLibrary Name: " + name);
        printBooks();
        printMembers();
    }
}

public class Lib {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library("Central Library");

        library.addBook(new Book("12345", "Great Student", "Mosawer Wadan"));
        library.addBook(new Book("54321", "Learning Java", "Mohammad"));
        library.addBook(new Book("01234", "Git and GitHub", "Ahmad"));
        library.addBook(new Book("02345", "Easy Java Programming", "Ali"));
        library.addBook(new Book("03456", "How to Become a Teacher", "Wadan"));

        library.addMember(new Member("000001", "Massoud Wadan"));
        library.addMember(new Member("000002", "Mohammad Wadan"));

        boolean running = true;

        while (running) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. View all books");
            System.out.println("2. View all members");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. View library information");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice;
            if(scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }
            } else {
                System.out.println("No input detected. Exiting.");
                break;
            }

            switch (choice) {
                case 1 -> library.printBooks();
                case 2 -> library.printMembers();
                case 3 -> {
                    System.out.print("Enter member ID: ");
                    if (!scanner.hasNextLine()) { System.out.println("No input detected."); break; }
                    String borrowMemberId = scanner.nextLine();

                    System.out.print("Enter book ID: ");
                    if (!scanner.hasNextLine()) { System.out.println("No input detected."); break; }
                    String borrowBookId = scanner.nextLine();

                    library.borrowBook(borrowMemberId, borrowBookId);
                }
                case 4 -> {
                    System.out.print("Enter member ID: ");
                    if (!scanner.hasNextLine()) { System.out.println("No input detected."); break; }
                    String returnMemberId = scanner.nextLine();

                    System.out.print("Enter book ID: ");
                    if (!scanner.hasNextLine()) { System.out.println("No input detected."); break; }
                    String returnBookId = scanner.nextLine();

                    library.returnBook(returnMemberId, returnBookId);
                }
                case 5 -> library.printLibraryInfo();
                case 6 -> {
                    running = false;
                    System.out.println("Thank you for using the Library Management System.");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
