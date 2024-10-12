import java.util.ArrayList;
import java.util.List;

class Book {
    private String id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String id, String title, String author) {
        if (id.length() != 5) {
            throw new IllegalArgumentException("It should 5 character!!!");
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