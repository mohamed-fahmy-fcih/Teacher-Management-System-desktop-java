package sample.Classes;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import sample.Admin.AdminController;

public class Assistance {
    private int AssID;
    private String AssName;
    private String AssAddress;
    private String AssDate;
    private String AssSSN;
    private int AssSalary;

    public Assistance(int assID, String assName, String assAddress, String assDate, String assSSN, int assSalary) {
        AssID = assID;
        AssName = assName;
        AssAddress = assAddress;
        AssDate = assDate;
        AssSSN = assSSN;
        AssSalary = assSalary;
    }

    public int getAssSalary() {
        return AssSalary;
    }

    public void setAssSalary(int assSalary) {
        AssSalary = assSalary;
    }

    public int getAssID() {
        return AssID;
    }

    public void setAssID(int assID) {
        AssID = assID;
    }

    public String getAssName() {
        return AssName;
    }

    public void setAssName(String assName) {
        AssName = assName;
    }

    public String getAssAddress() {
        return AssAddress;
    }

    public void setAssAddress(String assAddress) {
        AssAddress = assAddress;
    }

    public String getAssDate() {
        return AssDate;
    }

    public void setAssDate(String assDate) {
        AssDate = assDate;
    }

    public String getAssSSN() {
        return AssSSN;
    }

    public void setAssSSN(String assSSN) {
        AssSSN = assSSN;
    }
}
