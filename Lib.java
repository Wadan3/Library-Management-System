import java.util.ArrayList;
import java.util.List;

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

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrow() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            throw new IllegalStateException("Book is not available.");
        }
    }

    public void returnBook() {
        isAvailable = true;
    }

    public void printBookInfo() {
        System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author + ", Available: " + isAvailable);
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

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        if (borrowedBooks.size() < 3 && book.isAvailable()) {
            borrowedBooks.add(book);
            book.borrow();
        } else {
            throw new IllegalStateException("Cannot borrow book: either limit reached or book not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();
        } else {
            throw new IllegalArgumentException("This book was not borrowed by the member.");
        }
    }

    public void printMemberInfo() {
        System.out.print("Member ID: " + memberId + ", Name: " + name + ", Borrowed Books: ");
        if (borrowedBooks.isEmpty()) {
            System.out.println("None");
        } else {
            for (Book book : borrowedBooks) {
                System.out.print(book.getTitle() + " ");
            }
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

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void borrowBook(Member member, Book book) {
        if (books.contains(book) && members.contains(member)) {
            member.borrowBook(book);
        }
    }

    public void returnBook(Member member, Book book) {
        if (books.contains(book) && members.contains(member)) {
            member.returnBook(book);
        }
    }

    public void printLibraryInfo() {
        System.out.println("Library Name: " + name);
        System.out.println("Books in Library:");
        for (Book book : books) {
            book.printBookInfo();
        }
        System.out.println("Members of Library:");
        for (Member member : members) {
            member.printMemberInfo();
        }
    }
}

public class Lib {
    public static void main(String[] args) {

        Library library = new Library("Central Library");


        Book book1 = new Book("12345", "Great Student", "Mosawer Wadan");
        Book book2 = new Book("54321", "Learning Java", "T.Alireza Nasoodi");
        Book book3 = new Book("01234", "Git and GitHub", "T.Alireza Nasoodi");
        Book book4 = new Book("02345", "Easy Java Programming", "T.Alireza Nasoodi");
        Book book5 = new Book("03456", "How to become a Teacher", "T.Alireza Nasoodi");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);


        Member member1 = new Member("000001", "Massoud Wadan");
        Member member2 = new Member("000002", "Mohammad Wadan");

        library.addMember(member1);
        library.addMember(member2);

        member1.borrowBook(book2);
        member1.borrowBook(book4);
        member2.borrowBook(book5);


        member1.returnBook(book2);

        library.printLibraryInfo();
    }
}
