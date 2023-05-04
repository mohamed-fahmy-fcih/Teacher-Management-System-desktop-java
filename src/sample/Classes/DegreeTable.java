package sample.Classes;

import java.sql.Date;

public class DegreeTable {
    private String degreeName;
    private Date degreeDate;
    private int degreeCurr;
    private int degreeMax;
    private int isFinal;

    public DegreeTable(String degreeName, Date degreeDate, int degreeCurr, int degreeMax, int isFinal) {
        this.degreeName = degreeName;
        this.degreeDate = degreeDate;
        this.degreeCurr = degreeCurr;
        this.degreeMax = degreeMax;
        this.isFinal = isFinal;
    }

    //region Getter And Setter
    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Date getDegreeDate() {
        return degreeDate;
    }

    public void setDegreeDate(Date degreeDate) {
        this.degreeDate = degreeDate;
    }

    public int getDegreeCurr() {
        return degreeCurr;
    }

    public void setDegreeCurr(int degreeCurr) {
        this.degreeCurr = degreeCurr;
    }

    public int getDegreeMax() {
        return degreeMax;
    }

    public void setDegreeMax(int degreeMax) {
        this.degreeMax = degreeMax;
    }

    public int getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(int isFinal) {
        this.isFinal = isFinal;
    }
    //endregion
}
