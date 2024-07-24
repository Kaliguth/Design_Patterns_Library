package library;

public class Book implements Cloneable {
    // Title
    private String title;
    // Author
    private String author;
    // Publication date
    private String publicationDate;
    // Boolean to determine if the book is available
    private boolean isAvailable;

    // Constructor
    public Book(String title, String author, String publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.isAvailable = true;
    }

    // Getters & Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean availability) {
        this.isAvailable = availability;
    }

    // Clone method
    // Overrides Cloneable interface's clone()
    @Override
    public Book clone() {
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cannot clone Book", e);
        }
    }

    // Overload of Object's equals method with Book parameter
    public boolean equals(Book book) {
        if (this == book) return true;

        return this.title.equals(book.title) &&
                this.author.equals(book.author) &&
                this.publicationDate.equals(book.publicationDate);
    }

    public String toString() {
        return "title=" + this.title +
                ", author=" + this.author +
                ", publication date=" + this.publicationDate +
                ", available=" + this.isAvailable;
    }

}
