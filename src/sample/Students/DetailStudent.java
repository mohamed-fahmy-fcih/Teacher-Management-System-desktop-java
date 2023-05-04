package sample.Students;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Classes.Attendance;
import sample.Classes.StudentProfile;
import sample.Utilities.GetBigData;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class DetailStudent implements Initializable {
    //region Table
    @FXML private TableView<Attendance> AttendanceTable;
    @FXML private TableColumn<Attendance, Integer> AttendanceMaxDegree;
    @FXML private TableColumn<Attendance, Integer> AttendanceCurrDegree;
    @FXML private TableColumn<Attendance, Date> AttendanceDate;
    @FXML private TableColumn<Attendance, Integer> AttendancePaid;
    @FXML private TableColumn<Attendance, Integer> AttendanceLecNumber;
    //endregion

    StudentProfile student;
    public void SendData(StudentProfile student) {
        this.student = student;
        refreshTimelineTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AttendanceMaxDegree.setCellValueFactory(new PropertyValueFactory<>("quizMaxDegree"));
        AttendanceCurrDegree.setCellValueFactory(new PropertyValueFactory<>("quizCurrentDegree"));
        AttendanceDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        AttendancePaid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        AttendanceLecNumber.setCellValueFactory(new PropertyValueFactory<>("lectureNumber"));
    }

    private void refreshTimelineTable() {
        ObservableList<Attendance> data = FXCollections.observableArrayList();

        data.addAll(GetBigData.GetOwnedAttendance(student.getStudentID()));

        AttendanceTable.setItems(data);
    }
}
