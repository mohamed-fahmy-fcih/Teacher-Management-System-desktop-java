package sample.Classes;

public class Book {
    private int bookID;
    private String bookName;
    private double fullPrice;
    private int grade;

    public Book() {
        this.bookID = 0;
        this.bookName = "";
        this.fullPrice = 0;
        this.grade = -1;
    }

    public Book(int bookID, String bookName, double fullPrice, int grade) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.fullPrice = fullPrice;
        this.grade = grade;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
