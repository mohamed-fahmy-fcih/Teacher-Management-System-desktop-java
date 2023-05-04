package sample.Admin;

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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListOfStudentController implements Initializable {
    //region Table
    @FXML
    private TableView<StudentClass> Table;
    @FXML private TableColumn<StudentClass, Integer> ColumnID;
    @FXML private TableColumn<StudentClass, String> ColumnName;
    @FXML private TableColumn<StudentClass, String> ColumnGroup;
    @FXML private TableColumn<StudentClass, String> ColumnGrade;
    //endregion

    ArrayList<StudentProfile> students = new ArrayList<>();
    public void SendData(ArrayList<StudentProfile> _students) {
        students = _students;
        refreshTimelineTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        ColumnGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
    }

    private void refreshTimelineTable() {
        ObservableList<StudentClass> data = FXCollections.observableArrayList();

        for (StudentProfile student : students) {
            StudentClass sc = new StudentClass(
                    student.getStudentID(),
                    student.getStudentName(),
                    student.getStudentGrade(),
                    student.getStudentGroup()
            );
            data.add(sc);
        }
        //data.addAll(GetBigData.GetOwnedAttendance(student.getStudentID()));

        Table.setItems(data);
    }

    public class StudentClass
    {
        private int id;
        private String name;
        private String grade;
        private String group;

        public StudentClass(int id, String name, String grade, String group) {
            this.id = id;
            this.name = name;
            this.grade = grade;
            this.group = group;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }
    }
}
