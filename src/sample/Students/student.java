package sample.Students;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import sample.Classes.DailyLog;
import sample.Classes.StudentProfile;
import sample.GeneratePDF.GeneratePDF;
import sample.SceneManager;
import sample.Students.Update.UpdateStudent;
import sample.Utilities.*;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
public class student implements Initializable {

    //region Variables
    //region SwitchButtons
    @FXML private HBox attendButton;
    @FXML private HBox addButton;
    @FXML private HBox searchButton;
    @FXML private HBox updateButton;
    @FXML private HBox DeleteButton;
    @FXML private HBox DailyButton;
    @FXML private HBox addStudentButton1;
    // endregion
    //region Anchors And Rectangles
    @FXML private AnchorPane attendStudent;
    @FXML private AnchorPane addStudent;
    @FXML private AnchorPane SearchStudent;
    @FXML private AnchorPane UpdateStudent;
    @FXML private AnchorPane DeleteStudent;
    @FXML private AnchorPane DailyInformation;
    @FXML private AnchorPane addLecStudent;
    //rectangcle
    @FXML private Rectangle rectanAttend;
    @FXML private Rectangle rectanAdd;
    @FXML private Rectangle rectanSearch;
    @FXML private Rectangle rectanupdate;
    @FXML private Rectangle rectanDelete;
    @FXML private Rectangle rectanDaily;
    @FXML private Rectangle rectanAddStudent1;
    //endregion
    //region Attend Section
    @FXML private TextField AttendSearchTextField;
    @FXML private Button AttendSearchBtn;
    @FXML private GridPane AttendGrid;
    @FXML private Label AttendNameLabel;
    @FXML private Label AttendGroupLabel;
    @FXML private Label AttendGradeLabel;
    @FXML private ComboBox AttendLectureCombo;
    @FXML private TextField AttendPriceTextField;
    //endregion
    //region Add Section
    @FXML private TextField AddNameTextField;
    @FXML private ComboBox GroupTextField;
    @FXML private ComboBox AddGradeCombo;
    @FXML private TextField AddNumberTextField;
    @FXML private TextField AddSchoolTextField;
    @FXML private TextField AddParentNumberTextField;
    @FXML private DatePicker AddDate;
    //endregion
    //region Search Section
    @FXML private ToggleGroup SearchGroup;
    @FXML private RadioButton SearchRadioID;
    @FXML private RadioButton SearchRadioName;
    @FXML private HBox SearchIDHBox;
    @FXML private HBox SearchNameHBox;
    @FXML private TextField SearchIDTextField;
    @FXML private TextField SearchNameTextField;
    //endregion
    //region Update Section
    @FXML private TextField UpdateSearch;
    //endregion
    //region Daily Section
    @FXML private TableView<DailyLog> DailyTable;
    @FXML private TableColumn<DailyLog, String> DailyDescription;
    @FXML private TableColumn<DailyLog, Integer> DailyPaid;
    @FXML private TableColumn<DailyLog, String> DailyDate;
    @FXML private Label totalMoney;
    //endregion
    //region Delete
    @FXML private TextField deleteId;
    //endregion
    ArrayList<ButtonSwitcher> buttonSwitchers = new ArrayList<>();
    //endregion

    //region Admin
    @FXML private HBox AdminButton;
    //endregion

    //region Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SetupNumericTextFields();
        InitializeAddSection();
        SetupButtonsAndAnchorsForSwitching();
        InitializeDailyTable();
        AdminButton.setVisible(User.isAdmin());
        SetupSearchRadioButtons();
        SetUpLectureComboBox();
    }
    private void SetupNumericTextFields() {
        //TODO Change Numeric
        UIHandle.SetTextFieldNumeric(AttendSearchTextField);
        UIHandle.SetTextFieldNumeric(AttendPriceTextField);
        UIHandle.SetTextFieldNumeric(AddNumberTextField);
        UIHandle.SetTextFieldNumeric(AddParentNumberTextField);
        UIHandle.SetTextFieldNumeric(SearchIDTextField);
    }
    private void SetupButtonsAndAnchorsForSwitching() {
        buttonSwitchers.add(new ButtonSwitcher(attendStudent, attendButton, rectanAttend));
        buttonSwitchers.add(new ButtonSwitcher(addStudent, addButton, rectanAdd));
        buttonSwitchers.add(new ButtonSwitcher(SearchStudent, searchButton, rectanSearch));
        buttonSwitchers.add(new ButtonSwitcher(UpdateStudent, updateButton, rectanupdate));
        buttonSwitchers.add(new ButtonSwitcher(DeleteStudent, DeleteButton, rectanDelete));
        buttonSwitchers.add(new ButtonSwitcher(DailyInformation, DailyButton, rectanDaily));
        buttonSwitchers.add(new ButtonSwitcher(addLecStudent, addStudentButton1, rectanAddStudent1));

        int i = 0;
        for (ButtonSwitcher b : buttonSwitchers)
        {
            b.SetOnAction(buttonSwitchers);
        }

        Utilities.ActivePage(buttonSwitchers.get(5), buttonSwitchers);
    }
    private void SetupSearchRadioButtons() {

        SearchGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle old_radio, Toggle new_radio) {
                if (new_radio == SearchRadioID) {
                    SearchIDHBox.setVisible(true);
                    SearchNameHBox.setVisible(false);
                }
                else if (new_radio == SearchRadioName) {
                    SearchIDHBox.setVisible(false);
                    SearchNameHBox.setVisible(true);
                }
            }
        });

        SearchGroup.selectToggle(SearchRadioID);
    }
    //endregion

    //region Action Buttons
    StudentProfile student = new StudentProfile();
    public void GoToAdminPage() {
        SceneManager.changeScene("Admin/Admin.fxml", "Admin");
    }
    public void BackToLogin() {
        SceneManager.changeScene("Login/Login.fxml", "Admin");
    }
    public void GoToProfilePage() {
        if (SearchIDTextField.getText().isEmpty())
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى البحث", "يرجى ادخال الID", false, true);
            return;
        }
        try {
        StudentProfile student = GetBigData.GetStudentByID(Integer.parseInt(SearchIDTextField.getText()));

        if (student == null)
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى البحث", "يرجى ادخال الID بشكل صحيح", false, true);
            return;
        }

        ProfileStudent profileStudent = SceneManager.openNewWindow("Students/ProfileStudent.fxml","admin");

        profileStudent.SetProfileStudent(student);
        }
        catch (Exception e) {
            e.printStackTrace();
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى البحث", "يرجى ادخال الID بشكل صحيح", false, true);
        }
    }
    public void GoToUpdatePage() {
        if (UpdateSearch.getText().isEmpty())
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى البحث", "يرجى ادخال الID بشكل صحيح", false, true);
            return;
        }

        try {
            StudentProfile student = GetBigData.GetStudentByID(Integer.parseInt(UpdateSearch.getText()));

            if (student == null) {
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى البحث", "يرجى ادخال الID", false, true);
            }

            UpdateStudent updateStudent = SceneManager.openNewWindow("Students/Update/UpdateStudent.fxml","admin");

            updateStudent.SendData(student);
        } catch (Exception e) {
            e.printStackTrace();
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى البحث", "يرجى ادخال الID بشكل صحيح", false, true);
        }
    }
    //region Attend Section
    Map<Object, Object> attendComboMap;
    public void OnSearchID() {
        boolean isChecked = SearchStudentByID(AttendSearchTextField);
        AttendGrid.setDisable(isChecked);
        if (isChecked)
        {
            AttendNameLabel.setText("-");
            AttendGroupLabel.setText("-");
            AttendGradeLabel.setText("-");
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","خطأ فى البحث", "الID غير صحيح", false, true);
            return;
        }

        if (AttendSearchTextField.getText().isEmpty())
        {
            System.out.println("Empty");
            return;
        }

        try {
            int StudentId = Integer.parseInt(AttendSearchTextField.getText());

            student = GetBigData.GetStudentByID(StudentId);
            System.out.println(student.getStudentID());
            if (student == null) {
                System.out.println("ERROR: In Finding Student");
                return;
            }
            else {
                AttendNameLabel.setText(student.getStudentName());
                AttendGroupLabel.setText(student.getStudentGroup());
                AttendGradeLabel.setText(student.getStudentGrade());
            }

            student.setStudentGradeID(SQLQueries.GetInt("Grade_Id", DatabaseTables.STUDENT_GENERAL_INFO, "Student_Id", StudentId));

            attendComboMap = SQLQueries.GetMapString("Lec_Id", "Lec_Name", DatabaseTables.LECTURES, "Grade_Id", student.getStudentGradeID());

            AttendLectureCombo.getItems().clear();
            if (attendComboMap != null) {
                for (Map.Entry<Object, Object> object : attendComboMap.entrySet())
                {
                    AttendLectureCombo.getItems().add(object.getValue());
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            AttendNameLabel.setText("-");
            AttendGroupLabel.setText("-");
            AttendGradeLabel.setText("-");
        }
    }

    public void OnSearchName() {
        if (SearchNameTextField.getText().isEmpty())
        {
            System.out.println("Empty");
            return;
        }
        ArrayList<StudentProfile> studentData = new ArrayList<>();

        try {
            String StudentName = SearchNameTextField.getText();

            studentData = GetBigData.GetStudentByName("%"+StudentName+"%");


            for (int i = 0; i < studentData.size() ; i++){



                StudentProfile studentProfile = studentData.get(i);

                if (studentProfile == null) {
                    System.out.println("ERROR: In Finding Student");
                    return;
                }

            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            AttendNameLabel.setText("-");
            AttendGroupLabel.setText("-");
            AttendGradeLabel.setText("-");
        }

        SearchingTableNameController searchingTableNameController = SceneManager.openNewWindow("Students/SearchingTable.fxml", "Result Of Searching");
        searchingTableNameController.SendData(studentData);
    }

    public void OnAttend() {

        if (AttendLectureCombo.getValue() == null)
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","خطأ فى التسجيل الطالب", "ادخل الحصة", false, true);
            return;
        }

        String valueChecked = AttendLectureCombo.getValue().toString();
        int valuePaid = -1;
        int idLecture = -1;

        for (Map.Entry<Object, Object> object : attendComboMap.entrySet())
        {
            if (object.getValue() == valueChecked)
            {
                idLecture = Integer.parseInt(object.getKey().toString());
                break;
            }
        }

        if (AttendPriceTextField.getText().isEmpty())
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","خطأ فى التسجيل الطالب", "ادخل المبلغ المدفوع", false, true);
            return;
        }

        if (idLecture == -1)
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","خطأ فى التسجيل الطالب", "اختر الحصة", false, true);
            return;
        }

        valuePaid = Integer.parseInt(AttendPriceTextField.getText());

        ArrayList<Object> arrayOfValues = new ArrayList<>();
        arrayOfValues.add(student.getStudentID());
        arrayOfValues.add(idLecture);
        arrayOfValues.add(valuePaid);
        arrayOfValues.add(Date.valueOf(LocalDate.now()).toString());
        try {
            boolean isSucc = SQLQueries.Insert(DatabaseTables.ATTENDANCE, arrayOfValues);

            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add(student.getStudentID() + " Payed Lecture. ");
            arrayList.add(valuePaid);
            arrayList.add(Date.valueOf(LocalDate.now()).toString());
            SQLQueries.Insert(DatabaseTables.MONEY, arrayList);

            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","نجح فى تسجيل الطالب", "تم اضافة الطالب فى قاعدة البيانات", false, false);
        } catch (Exception e) {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","خطأ فى تسجيل الطالب", "فشل فى اضافة الطالب فى قاعدة البيانات", false, true);
            e.printStackTrace();
        }

        AttendPriceTextField.setText("");
    }
    //endregion
    //region Add Section
    ArrayList<TextField> arrayOfTextFields = new ArrayList<>();
    Map<Object, Object> AddComboMap = new HashMap<>();
    Map<Object, Object> AddGroupComboMap = new HashMap<>();

    private void InitializeAddSection() {
        SetComboGrade();
        SetComboGroup();

        arrayOfTextFields.add(AddNameTextField);
        arrayOfTextFields.add(AddNumberTextField);
        arrayOfTextFields.add(AddSchoolTextField);
        arrayOfTextFields.add(AddParentNumberTextField);
    }

    private void SetComboGrade() {
        AddComboMap = SQLQueries.GetMapString("Grade_Id", "Garade_Name", DatabaseTables.GRADES);

        AddGradeCombo.getItems().clear();
        if (AddComboMap != null) {
            for (Map.Entry<Object, Object> object : AddComboMap.entrySet())
            {
                AddGradeCombo.getItems().add(object.getValue());
            }
        }
    }

    private void SetComboGroup() {
        AddGroupComboMap = SQLQueries.GetMapString("ID", "name", DatabaseTables.GROUP);

        GroupTextField.getItems().clear();
        if (AddComboMap != null) {
            for (Map.Entry<Object, Object> object : AddGroupComboMap.entrySet())
            {
                GroupTextField.getItems().add(object.getValue());
            }
        }
    }

    public void OnAddStudent() {
        for (TextField text : arrayOfTextFields) {
            if (text.getText().isEmpty()) {
                System.out.println("Empty");
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى اضافة الطالب", "يرجى ادخال جميع البيانات", false, true);
                return;
            }
        }

        if (AddDate.getValue() == null) {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى اضافة الطالب", "يرجى ادخال جميع البيانات", false, true);
            return;
        }

        String valueChecked = AddGradeCombo.getValue().toString();
        int idGrade = -1;

        for (Map.Entry<Object, Object> object : AddComboMap.entrySet()) {
            if (object.getValue() == valueChecked)
            {
                idGrade = Integer.parseInt(object.getKey().toString());
                break;
            }
        }

        if (idGrade == -1) {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى اضافة الطالب", "يرجى ادخال جميع البيانات", false, true);
            return;
        }

        valueChecked = GroupTextField.getValue().toString();
        int idGroup = -1;

        for (Map.Entry<Object, Object> object : AddGroupComboMap.entrySet()) {
            if (object.getValue() == valueChecked)
            {
                idGroup = Integer.parseInt(object.getKey().toString());
                break;
            }
        }

        if (idGroup == -1) {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى اضافة الطالب", "يرجى ادخال جميع البيانات", false, true);
            return;
        }

        int prefix = (SQLQueries.GetInt("Garade_Prefix", DatabaseTables.GRADES, "Grade_ID", idGrade) * 100000) + 1;

        int countStudentsInGrade = SQLQueries.GetCount("Student_Id", DatabaseTables.STUDENT_GENERAL_INFO, "Grade_Id", idGrade);
        int idStudent = prefix + countStudentsInGrade + 1;
        System.out.println(prefix + " -- " + idStudent);

        for(int i = prefix + 1 ; i <= idStudent ; i++){
            int res = SQLQueries.GetInt("Student_Id", "student_general_info", "Student_Id", i);
            System.out.println(res);
            if(res == -1) {
                 idStudent = i;
                 break;
            }
        }

        ArrayList<Object> arrayOfStudentDetials = new ArrayList<>();
        arrayOfStudentDetials.add(idStudent);
        arrayOfStudentDetials.add(AddNameTextField.getText());
        arrayOfStudentDetials.add(AddSchoolTextField.getText());
        arrayOfStudentDetials.add(Date.valueOf(AddDate.getValue()).toString());
        arrayOfStudentDetials.add(idGrade);
        arrayOfStudentDetials.add(idGroup);

        ArrayList<Object> arrayOfPhone = new ArrayList<>();
        arrayOfPhone.add(idStudent);
        arrayOfPhone.add(AddNumberTextField.getText());
        arrayOfPhone.add(AddParentNumberTextField.getText());

        try {
            SQLQueries.Insert(DatabaseTables.STUDENT_GENERAL_INFO, arrayOfStudentDetials);
            SQLQueries.Insert(DatabaseTables.PHONES_NUMBER, arrayOfPhone);
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","نجح فى اضافة الطالب", "تم اضافة الطالب فى قاعدة البيانات", false, false);
        } catch (Exception e) {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى اضافة الطالب", "فشل فى اضافة الطالب فى قاعدة البيانات", false, true);
            e.printStackTrace();
        }

        for (TextField text : arrayOfTextFields) {
            text.setText("");
        }

    }
    //endregion
    //endregion

    //region Daily Log
    private void InitializeDailyTable() {
        DailyDescription.setCellValueFactory(new PropertyValueFactory<>("DailyDescription"));
        DailyPaid.setCellValueFactory(new PropertyValueFactory<>("DailyPaid"));
        DailyDate.setCellValueFactory(new PropertyValueFactory<>("DailyDate"));

        RefreshDailyTable();
        AllPaidMoney();
    }

    public void RefreshDailyTable() {
        ObservableList<DailyLog> data = FXCollections.observableArrayList();

        data.addAll(GetBigData.GetDailyRecords());

        DailyTable.setItems(data);

        AllPaidMoney();
    }

    private void AllPaidMoney(){
        String dailyDate = Date.valueOf(LocalDate.now()).toString();
        int res = SQLQueries.SumOfColumn(DatabaseTables.MONEY, "money", "dateMoney", dailyDate);
        totalMoney.setText(String.valueOf(res));
    }
    //endregion

    //region delete
    public  void DeleteStudent(){
        if (deleteId.getText().isEmpty())
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى البحث", "يرجى ادخال الID", false, true);
            return;
        }

        int id = Integer.parseInt(deleteId.getText());
        Map<String, Object> mp = new HashMap<>();
        mp.put("Student_Id", id);

        int checkUser = SQLQueries.GetInt("Student_Id", DatabaseTables.STUDENT_GENERAL_INFO, "Student_Id", id);
        if (checkUser == -1) {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى البحث", "يرجى ادخال الID بشكل صحيح", false, true);
            return;
        }

        try {
            boolean isSure = SceneManager.openAlertBox("AlertBox/AlertBox.fxml","نجح فى مسح الطالب", "هل تريد مسح هذا الطالب " + id, true, false);
            if (isSure)
            {
                SQLQueries.Delete("attendance", mp);
                SQLQueries.Delete("books_sales", mp);
                SQLQueries.Delete("lecture_quiz", mp);
                SQLQueries.Delete("phones_number", mp);
                SQLQueries.Delete("students_exams", mp);
                SQLQueries.Delete("student_general_info", mp);

                SceneManager.openAlertBox("AlertBox/AlertBox.fxml","نجح فى مسح الطالب", "تم مسح الطالب من قاعدة البيانات", false, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","فشل فى مسح الطالب", "حدث خطأ فى مسح الطالب", false, true);
        }

        deleteId.setText("");
    }
    //endregion

    public boolean SearchStudentByID(TextField textField){
        boolean isDataFound = Utilities.CheckDataToDisable("Student_Id", DatabaseTables.STUDENT_GENERAL_INFO, "Student_Id", textField.getText());
        return isDataFound;
    }


    //region Add lectures
    //region Lecture Variables Section
    @FXML private TextField LectureNumberTextField;
    @FXML private TextField LectureDegreeTextField;
    @FXML private DatePicker LectureDate;
    @FXML private ComboBox LectureComboBox;
    @FXML private TextField LectureDeleteTextField;
    Map<Object, Object> LectureMap;
    //endregion
    private void SetUpLectureComboBox() {
        LectureMap = SQLQueries.GetMapString("Grade_Id", "Garade_Name", DatabaseTables.GRADES);
        Utilities.SetComboBox(LectureComboBox, LectureMap);
    }
    public void AddLecture() {
        if (LectureNumberTextField.getText().isEmpty() || LectureDate.getValue() == null || LectureComboBox.getValue() == null || LectureDegreeTextField.getText().isEmpty())
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل محاضرة", "يرجى ادخال جميع البيانات", false, true);
            return;
        }

        String lecName = LectureNumberTextField.getText();
        int gradeId = (Integer)Utilities.CheckDataComboBox(LectureComboBox, LectureMap);
        Date date = Date.valueOf(LectureDate.getValue().toString());

        ArrayList<Object> columns = new ArrayList<>();
        columns.add("Grade_Id");
        columns.add("Lec_Name");
        columns.add("Date");
        columns.add("Max_Degree");

        ArrayList<Object> values = new ArrayList<>();
        values.add(gradeId);
        values.add(lecName);
        values.add(date.toString());
        values.add(Integer.parseInt(LectureDegreeTextField.getText()));

        boolean isInserted = SQLQueries.Insert(DatabaseTables.LECTURES, columns, values);
        if (isInserted)
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل محاضرة", "تم حفظ المحاضرة", false, false);
        else
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل محاضرة", "حدث خطأ فى تسجيل المحاضرة", false, true);
    }
    public void DeleteLecture() {
        if (LectureDeleteTextField.getText().isEmpty())
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح محاضرة", "يرجى ادخال الID", false, true);
            return;
        }

        Map<String, Object> mapCondition = new HashMap<>();
        mapCondition.put("Lec_Id", Integer.parseInt(LectureDeleteTextField.getText()));
        boolean isDeleted = SQLQueries.Delete(DatabaseTables.LECTURES, mapCondition);
        if (isDeleted)
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح محاضرة", "تم مسح المحاضرة", false, false);
        else
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح محاضرة", "حدث خطأ فى مسح المحاضرة", false, true);


    }
    public void GoToAllLectures() {
        SceneManager.openNewWindow("Admin/DetailLectures.fxml","admin");
    }

    public void makePdfDailyPaidMoney(){
        GeneratePDF generatePDF = new GeneratePDF();
        generatePDF.dailyPaidPDF();
    }
    //endregion
}