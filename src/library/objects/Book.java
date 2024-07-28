// Book class

package library.objects;

public class Book implements Cloneable {
    // Title
    private String title;
    // Author
    private String author;
    // PublishDate date
    private String publishDate;
    // Boolean to determine if the book is available
    private boolean isAvailable;

    // Constructor
    public Book(String title, String author, String publishDate) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.isAvailable = true;
    }

    // Getters & Setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // Clone method to create a copy of the book
    // Overrides Cloneable interface's clone method
    @Override
    public Book clone() {
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cannot clone Book", e);
        }
    }

    // Overload of Object's equals method with Book parameters - does not check availability
    // Explanation: There can be few books with the same parameters but different availability
    public boolean equals(Book book) {
        if (this == book) return true;

        return this.title.equals(book.title) &&
                this.author.equals(book.author) &&
                this.publishDate.equals(book.publishDate);
    }

    // toString method
    @Override
    public String toString() {
        return "\"" + title + "\"" +
                "  -  Written by " + author +
                "      Publish Date: " + publishDate +
                "      " + (isAvailable? "AVAILABLE" : "LOANED");
    }

}
