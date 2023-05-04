package sample.Students.Update;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.Classes.StudentProfile;
import sample.SceneManager;
import sample.Utilities.DatabaseTables;
import sample.Utilities.SQLQueries;
import sample.Utilities.UIHandle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class UpdateStudent implements Initializable {

    //region TextFields
    @FXML private TextField UpdateParentNumber;
    @FXML private TextField UpdateName;
    @FXML private TextField UpdateSchool;
    @FXML private TextField UpdateNumber;
    @FXML private ComboBox UpdateGroup;
    @FXML private ComboBox UpdateGradeCombo;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UIHandle.SetTextFieldNumeric(UpdateNumber);
        UIHandle.SetTextFieldNumeric(UpdateParentNumber);
        InitializeComboBox();
    }

    StudentProfile student;
    public void SendData(StudentProfile student) {
        this.student = student;
    }
    public void GoBack() {
        SceneManager.changeScene("Students/student.fxml","admin");
    }
    public void GoToQuizUpdateStudent() {
        QuizUpdateStudent quizUpdateStudent = SceneManager.openNewWindow("Students/Update/QuizUpdateStudent.fxml","admin");
        quizUpdateStudent.SendData(student);
    }
    public void GoToExamUpdateStudent() {
        ExamUpdateStudent examUpdateStudent = SceneManager.openNewWindow("Students/Update/ExamUpdateStudent.fxml","admin");
        examUpdateStudent.SendData(student);
    }
    public void GoToBookUpdateStudent() {
        BookUpdateStudent bookUpdateStudent = SceneManager.openNewWindow("Students/Update/BookUpdateStudent.fxml","admin");
        bookUpdateStudent.SendData(student);
    }

    Map<Object, Object> attendComboMap;
    Map<Object, Object> groupComboMap;
    private void InitializeComboBox()
    {
        attendComboMap = SQLQueries.GetMapString("Grade_Id", "Garade_Name", DatabaseTables.GRADES);

        UpdateGradeCombo.getItems().clear();
        if (attendComboMap != null) {
            for (Map.Entry<Object, Object> object : attendComboMap.entrySet())
            {
                UpdateGradeCombo.getItems().add(object.getValue());
            }
        }

        groupComboMap = SQLQueries.GetMapString("ID", "name", DatabaseTables.GROUP);

        UpdateGroup.getItems().clear();
        if (groupComboMap != null) {
            for (Map.Entry<Object, Object> object : groupComboMap.entrySet())
            {
                UpdateGroup.getItems().add(object.getValue());
            }
        }
    }

    public void UpdateData()
    {
        ArrayList<String> dataChanged = new ArrayList<>();

        boolean isSure = SceneManager.openAlertBox("AlertBox/AlertBox.fxml","تحديث بيانات الطالب", "هل انت تريد تحديث بيانات الطالب", true, false);

        if (!isSure) return;

        //region ComboBox

        if (UpdateGradeCombo.getValue() != null)
        {
            String valueChecked = UpdateGradeCombo.getValue().toString();
            int idLecture = -1;

            for (Map.Entry<Object, Object> object : attendComboMap.entrySet())
            {
                if (object.getValue() == valueChecked)
                {
                    idLecture = Integer.parseInt(object.getKey().toString());
                    break;
                }
            }

            if (idLecture != -1)
            {
                SQLQueries.UpdateSql(DatabaseTables.STUDENT_GENERAL_INFO, "Grade_Id", "Student_Id", student.getStudentID(), idLecture);
                dataChanged.add("الصف الدراسى");
            }
        }


        if (UpdateGroup.getValue() != null)
        {
            String valueCheckedGroup = UpdateGroup.getValue().toString();
            int idGroup = -1;

            for (Map.Entry<Object, Object> object : groupComboMap.entrySet())
            {
                if (object.getValue() == valueCheckedGroup)
                {
                    idGroup = Integer.parseInt(object.getKey().toString());
                    break;
                }
            }

            if (idGroup != -1)
            {
                SQLQueries.UpdateSql(DatabaseTables.STUDENT_GENERAL_INFO, "Group_Student", "Student_Id", student.getStudentID(), idGroup);
                dataChanged.add("مجموعة الطالب");
            }
        }

        //endregion

        if (!UpdateParentNumber.getText().isEmpty()) {
            SQLQueries.UpdateSql(DatabaseTables.PHONES_NUMBER, "Parent_Phone", "Student_Id", student.getStudentID(), UpdateParentNumber.getText());
            dataChanged.add("رقم ولى الامر");
        }
        if (!UpdateName.getText().isEmpty()) {
            SQLQueries.UpdateSql(DatabaseTables.STUDENT_GENERAL_INFO, "Name", "Student_Id", student.getStudentID(), UpdateName.getText());
            dataChanged.add("اسم الطالب");
        }
        if (!UpdateSchool.getText().isEmpty()) {
            SQLQueries.UpdateSql(DatabaseTables.STUDENT_GENERAL_INFO, "School", "Student_Id", student.getStudentID(), UpdateSchool.getText());
            dataChanged.add("مدرسة الطالب");
        }
        if (!UpdateNumber.getText().isEmpty()) {
            SQLQueries.UpdateSql(DatabaseTables.PHONES_NUMBER, "Student_Phone", "Student_Id", student.getStudentID(), UpdateNumber.getText());
            dataChanged.add("رقم الطالب");
        }

        if (dataChanged.size() != 0)
        {
            StringBuilder stringBuilder = new StringBuilder();
            for (String str : dataChanged)
                stringBuilder.append(str).append('\n');
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","تم تحديث بيانات الطالب", stringBuilder.toString(), false, false);
        }
        else
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","تحديث بيانات الطالب", "يرجى ادخال بيانات لكى يتم التحديث", false, true);
        }
    }
}