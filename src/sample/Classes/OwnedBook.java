package sample.Classes;

import java.sql.Date;

public class OwnedBook extends Book {

    private String bookName;
    private int bookId;
    private double paid;
    private Date date;

    public OwnedBook() {
        this.bookName = "";
        this.bookId = 0;
        this.paid = 0.0;
        this.date = Date.valueOf("2020-1-1");
    }

    public OwnedBook(int bookId, double paid, Date date) {
        this.bookId = bookId;
        this.paid = paid;
        this.date = date;
    }



    public OwnedBook(int bookID, String bookName, double fullPrice, int grade, int bookId, int paid, Date date) {
        super(bookID, bookName, fullPrice, grade);
        this.bookName = bookName;
        this.bookId = bookId;
        this.paid = paid;
        this.date = date;
    }

    @Override
    public String getBookName() {
        return bookName;
    }

    @Override
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
