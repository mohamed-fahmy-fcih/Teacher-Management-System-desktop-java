package sample.Classes;

import java.sql.Date;

public class Attendance {
    private int lectureNumber;
    private int paid;
    private Date date;
    private int quizCurrentDegree;
    private int quizMaxDegree;

    public Attendance (int lectureNumber, int paid, Date date, int quizCurrentDegree, int quizMaxDegree) {
        this.lectureNumber = lectureNumber;
        this.paid = paid;
        this.date = date;
        this.quizCurrentDegree = quizCurrentDegree;
        this.quizMaxDegree = quizMaxDegree;
    }

    //region Getter And Setter
    public int getLectureNumber() {
        return lectureNumber;
    }

    public void setLectureNumber(int lectureNumber) {
        this.lectureNumber = lectureNumber;
    }

    public int getPaid() {
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

    public int getQuizCurrentDegree() {
        return quizCurrentDegree;
    }

    public void setQuizCurrentDegree(int quizCurrentDegree) {
        this.quizCurrentDegree = quizCurrentDegree;
    }

    public int getQuizMaxDegree() {
        return quizMaxDegree;
    }

    public void setQuizMaxDegree(int quizMaxDegree) {
        this.quizMaxDegree = quizMaxDegree;
    }
    //endregion
}
