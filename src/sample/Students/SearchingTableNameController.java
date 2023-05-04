package sample.Students;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Classes.StudentProfile;
import sample.Utilities.GetBigData;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchingTableNameController implements Initializable {

    @FXML
    public TableView<SearchingTableNames> tableResultSearchName;

    @FXML
     public TableColumn<SearchingTableNames, String> studentName;
    @FXML
     public TableColumn<SearchingTableNames, Integer> studentID;
    @FXML
     public TableColumn<SearchingTableNames, Integer> studentGradeID;
    @FXML
     public TableColumn<SearchingTableNames, String> studentGroup;

    ArrayList<StudentProfile> studentData = new ArrayList<>();

    public void SendData(ArrayList<StudentProfile> student) {
        this.studentData = student;
        refreshTimelineTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        studentGradeID.setCellValueFactory(new PropertyValueFactory<>("studentGradeID"));
        studentGroup.setCellValueFactory(new PropertyValueFactory<>("studentGroup"));
    }

    private void refreshTimelineTable() {
        ObservableList<SearchingTableNames> data = FXCollections.observableArrayList();



        for (int i = 0; i < studentData.size() ; i++){

            SearchingTableNames searchingTableNames =  new SearchingTableNames();

            searchingTableNames.setStudentName(studentData.get(i).getStudentName());
            searchingTableNames.setStudentID(studentData.get(i).getStudentID());
            searchingTableNames.setStudentGradeID(studentData.get(i).getStudentGradeID());
            searchingTableNames.setStudentGroup(studentData.get(i).getStudentGroup());


            data.add(searchingTableNames);


        }



        tableResultSearchName.setItems(data);

    }
}
