package sample.Students.Update;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Classes.DegreeTable;
import sample.Classes.StudentProfile;
import sample.SceneManager;
import sample.Utilities.ComboBoxHandler;
import sample.Utilities.DatabaseTables;
import sample.Utilities.GetBigData;
import sample.Utilities.SQLQueries;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ExamUpdateStudent implements Initializable {
    //region Table
    @FXML private TableView<DegreeTable> DegreesTable;
    @FXML private TableColumn<DegreeTable, Integer> DegreeFinalCol;
    @FXML private TableColumn<DegreeTable, Integer> DegreeMaxCol;
    @FXML private TableColumn<DegreeTable, Integer> DegreeCurrCol;
    @FXML private TableColumn<DegreeTable, Date> DegreeDateCol;
    @FXML private TableColumn<DegreeTable, String> DegreeNameCol;
    //endregion

    //region Text Fields
    @FXML private ComboBox ExamComboBox;
    @FXML private TextField ExamCurrentDegree;
    //endregion

    StudentProfile student;
    public void SendData(StudentProfile student) {
        this.student = student;
        refreshTimelineTable();
        SetComboGrade();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DegreeFinalCol.setCellValueFactory(new PropertyValueFactory<>("isFinal"));
        DegreeMaxCol.setCellValueFactory(new PropertyValueFactory<>("degreeMax"));
        DegreeCurrCol.setCellValueFactory(new PropertyValueFactory<>("degreeCurr"));
        DegreeDateCol.setCellValueFactory(new PropertyValueFactory<>("degreeDate"));
        DegreeNameCol.setCellValueFactory(new PropertyValueFactory<>("degreeName"));
    }

    Map<Object, Object> ExamComboMap = new HashMap<>();
    private void SetComboGrade() {

        System.out.println(student.getStudentID());
        ExamComboMap = SQLQueries.GetMapString("Exam_ID", "Exam_Name", DatabaseTables.EXAMS, "Grade_Id", student.getStudentGradeID());

        System.out.println(ExamComboMap.size());

        ComboBoxHandler.SetUpComboBox(ExamComboMap, ExamComboBox);
    }

    private void refreshTimelineTable() {
        ObservableList<DegreeTable> data = FXCollections.observableArrayList();

        data.addAll(GetBigData.GetOwnedExams(student.getStudentID()));

        DegreesTable.setItems(data);
    }

    public void SaveExamData()
    {
        int resultComboBox = (Integer)ComboBoxHandler.CheckValueChecked(ExamComboMap, ExamComboBox);

        if (resultComboBox == -1) {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","اضافة درجات للطالب", "يرجى اختيار الامتحان", false, true);
            return;
        }

        if (ExamCurrentDegree.getText().isEmpty())
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","اضافة درجات للطالب", "يرجى ادخال درجة الامتحان", false, true);
            return;
        }

        int examDegree = Integer.parseInt(ExamCurrentDegree.getText());

        try {

            if (!SceneManager.openAlertBox("AlertBox/AlertBox.fxml","اضافة درجات للطالب", "هل انت تريد اضافة درجة الامتحان؟", true, false))
                return;

            ArrayList<Object> dataToInsert = new ArrayList<>();
            dataToInsert.add(student.getStudentID());
            dataToInsert.add(resultComboBox);
            dataToInsert.add(Date.valueOf(LocalDate.now()).toString());
            dataToInsert.add(examDegree);
            SQLQueries.Insert(DatabaseTables.STUDENTS_EXAMS, dataToInsert);

            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","اضافة درجات للطالب", "تم اضافة درجة الامتحان للطالب", false, false);
        } catch (Exception e) {
            e.printStackTrace();
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","اضافة درجات للطالب", "حدث خطأ فى اضافة درجة الامتحان فى قاعدة البيانات", false, true);
        }

        refreshTimelineTable();
    }
}