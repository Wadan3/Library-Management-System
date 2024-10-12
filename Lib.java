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