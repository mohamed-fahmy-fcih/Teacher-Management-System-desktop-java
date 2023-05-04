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

public class QuizUpdateStudent implements Initializable {

    public static class QuizTable {
       private String name;
       private int currDegree;
       private int maxDegree;
       private String date;

        public QuizTable(String name, int currDegree, int maxDegree, String date) {
            this.name = name;
            this.currDegree = currDegree;
            this.maxDegree = maxDegree;
            this.date = date;
        }

        //region Getter And Setter
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCurrDegree() {
            return currDegree;
        }

        public void setCurrDegree(int currDegree) {
            this.currDegree = currDegree;
        }

        public int getMaxDegree() {
            return maxDegree;
        }

        public void setMaxDegree(int maxDegree) {
            this.maxDegree = maxDegree;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
        //endregion
    }

    //region Table
    @FXML private TableView<QuizTable> DegreesTable;
    @FXML private TableColumn<QuizTable, String> DegreeNameCol;
    @FXML private TableColumn<QuizTable, Integer> DegreeCurrCol;
    @FXML private TableColumn<QuizTable, Integer> DegreeMaxCol;
    @FXML private TableColumn<QuizTable, String> DegreeDateCol;
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
        DegreeNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        DegreeCurrCol.setCellValueFactory(new PropertyValueFactory<>("currDegree"));
        DegreeMaxCol.setCellValueFactory(new PropertyValueFactory<>("maxDegree"));
        DegreeDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    Map<Object, Object> ExamComboMap = new HashMap<>();
    private void SetComboGrade() {
        ExamComboMap = SQLQueries.GetMapString("Lec_Id", "Lec_Name", DatabaseTables.LECTURES, "Grade_Id", student.getStudentGradeID());

        System.out.println(ExamComboMap.size());

        ComboBoxHandler.SetUpComboBox(ExamComboMap, ExamComboBox);
    }

    private void refreshTimelineTable() {
        ObservableList<QuizTable> data = FXCollections.observableArrayList();

        data.addAll(GetBigData.GetOwnedQuizs(student.getStudentID()));

        DegreesTable.setItems(data);
    }

    public void SaveQuizData() {
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
            dataToInsert.add(examDegree);
            SQLQueries.Insert(DatabaseTables.LECTURE_QUIZ, dataToInsert);

            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","اضافة درجات للطالب", "تم اضافة درجة الامتحان للطالب", false, false);
        } catch (Exception e) {
            e.printStackTrace();
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","اضافة درجات للطالب", "حدث خطأ فى اضافة درجة الامتحان فى قاعدة البيانات", false, true);
        }

        refreshTimelineTable();
    }
}