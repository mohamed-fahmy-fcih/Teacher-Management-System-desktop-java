package sample.Classes;

import java.sql.Date;

public class StudentProfile {
    private int studentID;
    private int studentGradeID;
    private int studentGroupID;
    private String studentName;
    private String studentGrade;
    private String studentGroup;
    private String studentSchool;
    private String studentNumber;
    private String studentParentNumber;
    private Date studentJoinedDate;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentGradeID() {
        return studentGradeID;
    }

    public void setStudentGradeID(int studentGradeID) {
        this.studentGradeID = studentGradeID;
    }

    public Date getStudentJoinedDate() {
        return studentJoinedDate;
    }

    public void setStudentJoinedDate(Date studentJoinedDate) {
        this.studentJoinedDate = studentJoinedDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public String getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(String studentSchool) {
        this.studentSchool = studentSchool;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentParentNumber() {
        return studentParentNumber;
    }

    public void setStudentParentNumber(String studentParentNumber) {
        this.studentParentNumber = studentParentNumber;
    }

    public int getStudentGroupID() {
        return studentGroupID;
    }

    public void setStudentGroupID(int studentGroupID) {
        this.studentGroupID = studentGroupID;
    }
}
