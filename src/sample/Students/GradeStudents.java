package sample.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Classes.DegreeTable;
import sample.Classes.StudentProfile;
import sample.Utilities.GetBigData;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class GradeStudents implements Initializable {
    //region Table
    @FXML private TableView<DegreeTable> DegreesTable;
    @FXML private TableColumn<DegreeTable, Integer> DegreeFinalCol;
    @FXML private TableColumn<DegreeTable, Integer> DegreeMaxCol;
    @FXML private TableColumn<DegreeTable, Integer> DegreeCurrCol;
    @FXML private TableColumn<DegreeTable, Date> DegreeDateCol;
    @FXML private TableColumn<DegreeTable, String> DegreeNameCol;
    //endregion

    StudentProfile student;
    public void SendData(StudentProfile student) {
        this.student = student;
        refreshTimelineTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GenerateTableColumns();
    }

    private void GenerateTableColumns() {
        DegreeFinalCol.setCellValueFactory(new PropertyValueFactory<>("isFinal"));
        DegreeMaxCol.setCellValueFactory(new PropertyValueFactory<>("degreeMax"));
        DegreeCurrCol.setCellValueFactory(new PropertyValueFactory<>("degreeCurr"));
        DegreeDateCol.setCellValueFactory(new PropertyValueFactory<>("degreeDate"));
        DegreeNameCol.setCellValueFactory(new PropertyValueFactory<>("degreeName"));
    }

    private void refreshTimelineTable() {
        ObservableList<DegreeTable> data = FXCollections.observableArrayList();

        data.addAll(GetBigData.GetOwnedExams(student.getStudentID()));

        DegreesTable.setItems(data);
    }
}
