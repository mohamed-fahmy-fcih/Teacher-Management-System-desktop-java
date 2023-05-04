package sample.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import sample.Classes.Assistance;
import sample.Classes.DailyLog;
import sample.Classes.StudentProfile;
import sample.GeneratePDF.GeneratePDF;
import sample.SceneManager;
import sample.Utilities.*;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    //region Page Anchors
    @FXML private AnchorPane addStudent;
    @FXML private AnchorPane SearchStudent;
    @FXML private AnchorPane UpdateStudent;
    @FXML private AnchorPane DeleteStudent;
    @FXML private AnchorPane DailyInformation;
    @FXML private AnchorPane editGarde;
    @FXML private AnchorPane editGroup;
    @FXML private AnchorPane addLec;
    @FXML private AnchorPane editExam;
    @FXML private AnchorPane editBook;
    @FXML private AnchorPane AllInformation;
    @FXML private AnchorPane listAllStudentAnchor;
    @FXML private AnchorPane listAttendenceSecrtary;
    @FXML private AnchorPane listAllSecrtary;
    //endregion
    //region Add Section
    @FXML private TextField AddNameTextField;
    @FXML private TextField AddSalaryTextField;
    @FXML private TextField AddPasswordTextField;
    @FXML private TextField AddAddressTextField;
    @FXML private DatePicker AddDate;
    @FXML private TextField AddPhoneTextField;
    @FXML private TextField AddSSNTextField;
    //endregion
    //region Search Section
    @FXML private TextField SearchTextField;
    @FXML private Label AttendNameLabel;
    @FXML private Label AttendNumberLabel;
    @FXML private Label AttendSalaryLabel;
    @FXML private Label AttendBirthDateLabel;
    @FXML private Label AttendJoinDateLabel;
    @FXML private Label AttendSSNLabel;
    //endregion
    //region Update Section
    @FXML private TextField UpdateSearchTextField;
    @FXML private TextField UpdateNameTextField;
    @FXML private TextField UpdateAddressTextField;
    @FXML private TextField UpdateSalaryTextField;
    @FXML private TextField UpdateSNNTextField;
    @FXML private DatePicker UpdateBirthDate;
    @FXML private TextField UpdatePhoneTextField;
    @FXML private GridPane UpdateGridLayout;
    //endregion
    //region Delete Section
    @FXML private TextField DeleteTextField;
    //endregion
    //region Daily Section
    @FXML private TableView<DailyLog> DailyTable;
    @FXML private TableColumn<DailyLog, String> DailyDescription;
    @FXML private TableColumn<DailyLog, Integer> DailyPaid;
    @FXML private TableColumn<DailyLog, String> DailyDate;
    @FXML private Label totalMoney;
    //endregion
    //region All Paid Section
    @FXML private TableView<AllPaidLog> AllPaidTable;
    @FXML private TableColumn<AllPaidLog, String> AllPaidDescription;
    @FXML private TableColumn<AllPaidLog, Integer> AllPaidPaid;
    @FXML private TableColumn<AllPaidLog, String> AllPaidDate;

    public class AllPaidLog {
        private String DailyDescription;
        private int DailyPaid;
        private String DailyDate;

        public AllPaidLog(String dailyDescription, int dailyPaid, String dailyDate) {
            DailyDescription = dailyDescription;
            DailyPaid = dailyPaid;
            DailyDate = dailyDate;
        }

        public String getDailyDescription() {
            return DailyDescription;
        }

        public void setDailyDescription(String dailyDescription) {
            DailyDescription = dailyDescription;
        }

        public int getDailyPaid() {
            return DailyPaid;
        }

        public void setDailyPaid(int dailyPaid) {
            DailyPaid = dailyPaid;
        }

        public String getDailyDate() {
            return DailyDate;
        }

        public void setDailyDate(String dailyDate) {
            DailyDate = dailyDate;
        }
    }
    @FXML private Label totalAllPaidMoney;
    //endregion
    //region All Assistance Section
    @FXML private TableView<Assistance> AssTable;
    @FXML private TableColumn<Assistance, Integer> AssID;
    @FXML private TableColumn<Assistance, String> AssName;
    @FXML private TableColumn<Assistance, String> AssAddress;
    @FXML private TableColumn<Assistance, String> AssDate;
    @FXML private TableColumn<Assistance, String> AssSSN;
    @FXML private TableColumn<Assistance, Integer> AssSalary;

    //endregion
    //region Side Buttons
    @FXML private HBox addButton;
    @FXML private Rectangle rectanAdd;
    @FXML private HBox searchButton;
    @FXML private Rectangle rectanSearch;
    @FXML private HBox updateButton;
    @FXML private Rectangle rectanupdate;
    @FXML private HBox DeleteButton;
    @FXML private Rectangle rectanDelete;
    @FXML private HBox DailyButton;
    @FXML private Rectangle rectanDaily;

    @FXML private HBox addLecButton;
    @FXML private Rectangle rectanEditlGrade;

    @FXML private HBox editGradeButton;
    @FXML private Rectangle rectanAddLec;

    @FXML private HBox editGroupButton;
    @FXML private Rectangle rectanEditlGroup;

    @FXML private HBox editExamButton1;
    @FXML private Rectangle rectanEditExam;

    @FXML private HBox editBookButton1;
    @FXML private Rectangle rectanEditBook;

    @FXML private HBox AllInformationButton;
    @FXML private Rectangle rectanAllInformation;

    @FXML private HBox listStudentButton;
    @FXML private Rectangle rectanListStudent;

    @FXML private HBox AttendenceScretaryButton;
    @FXML private Rectangle rectanAttendenceScretary;

    @FXML private HBox listSecrtaryButton;
    @FXML private Rectangle recanListSecrtary;

    @FXML private RadioButton toggleAdmin;


    @FXML private VBox EditAdmin;
    @FXML private VBox EditScretary;

    public  void Swapping() {
        if(toggleAdmin.isSelected())
        {
            EditAdmin.setVisible(true);
            EditScretary.setVisible(false);

        }else
        {
            EditAdmin.setVisible(false);
            EditScretary.setVisible(true);
        }
    }
    //endregion

    //region Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SetupNumericTextFields();
        InitializeAddSection();
        SetupButtonsAndAnchorsForSwitching();
        InitializeDailyTable();
        InitializeAllPaidTable();
        InitializeAllAssistanceTable();
        InitializeAllAssistanceAttendanceTable();
        SetUpLectureComboBox();
        SetUpGroupComboBox();
        SetUpExamComboBox();
        SetUpBookComboBox();
        SetUpStudentsSearchComboBox();
    }
    ArrayList<TextField> arrayOfAddTextFields = new ArrayList<>();
    Map<Object, Object> AddComboMap = new HashMap<>();

    private void InitializeAddSection() {
        arrayOfAddTextFields.add(AddNameTextField);
        arrayOfAddTextFields.add(AddSalaryTextField);
        arrayOfAddTextFields.add(AddPasswordTextField);
        arrayOfAddTextFields.add(AddAddressTextField);
        arrayOfAddTextFields.add(AddPhoneTextField);
        arrayOfAddTextFields.add(AddSSNTextField);
    }
    private void SetupNumericTextFields() {
        UIHandle.SetTextFieldNumeric(AddSalaryTextField);
        UIHandle.SetTextFieldNumeric(AddPhoneTextField);
        UIHandle.SetTextFieldNumeric(AddSSNTextField);

        UIHandle.SetTextFieldNumeric(GradePrefixTextField);

        UIHandle.SetTextFieldNumeric(GroupSizeTextField);
        UIHandle.SetTextFieldNumeric(GroupDeleteTextField);

        UIHandle.SetTextFieldNumeric(SearchTextField);
        UIHandle.SetTextFieldNumeric(LectureDegreeTextField);

        UIHandle.SetTextFieldNumeric(UpdateSearchTextField);
        UIHandle.SetTextFieldNumeric(UpdateSalaryTextField);
        UIHandle.SetTextFieldNumeric(UpdateSNNTextField);
        UIHandle.SetTextFieldNumeric(UpdatePhoneTextField);

        UIHandle.SetTextFieldNumeric(DeleteTextField);

        UIHandle.SetTextFieldNumeric(ExamDegreeTextField);
    }

    ArrayList<ButtonSwitcher> buttonSwitchers = new ArrayList<>();
    private void SetupButtonsAndAnchorsForSwitching() {
        buttonSwitchers.add(new ButtonSwitcher(addStudent, addButton, rectanAdd));
        buttonSwitchers.add(new ButtonSwitcher(SearchStudent, searchButton, rectanSearch));
        buttonSwitchers.add(new ButtonSwitcher(UpdateStudent, updateButton, rectanupdate));
        buttonSwitchers.add(new ButtonSwitcher(DeleteStudent, DeleteButton, rectanDelete));
        buttonSwitchers.add(new ButtonSwitcher(DailyInformation, DailyButton, rectanDaily));
        buttonSwitchers.add(new ButtonSwitcher(addLec, addLecButton, rectanAddLec));
        buttonSwitchers.add(new ButtonSwitcher(editGarde, editGradeButton, rectanEditlGrade));
        buttonSwitchers.add(new ButtonSwitcher(editGroup, editGroupButton, rectanEditlGroup));
        buttonSwitchers.add(new ButtonSwitcher(editExam, editExamButton1, rectanEditExam));
        buttonSwitchers.add(new ButtonSwitcher(editBook, editBookButton1, rectanEditBook));
        buttonSwitchers.add(new ButtonSwitcher(AllInformation, AllInformationButton, rectanAllInformation));
        buttonSwitchers.add(new ButtonSwitcher(listAllStudentAnchor, listStudentButton, rectanListStudent));
        buttonSwitchers.add(new ButtonSwitcher(listAttendenceSecrtary, AttendenceScretaryButton, rectanAttendenceScretary));
        buttonSwitchers.add(new ButtonSwitcher(listAllSecrtary, listSecrtaryButton, recanListSecrtary));

        int i = 0;
        for (ButtonSwitcher b : buttonSwitchers)
        {
            b.SetOnAction(buttonSwitchers);
        }

        Utilities.ActivePage(buttonSwitchers.get(4), buttonSwitchers);
    }
    //endregion

    //region Actions
    @FXML
    void GoToProfilePage(ActionEvent event) {
        SearchAssistant();
    }

    /** Details for Admin **/
    public void GoToAllLectures() {
        SceneManager.openNewWindow("Admin/DetailLectures.fxml","admin");
    }
    public void GoToAllExams() {
        SceneManager.openNewWindow("Admin/DetailExams.fxml","admin");
    }
    public void GoToAllGrades() {
        SceneManager.openNewWindow("Admin/DetailGrades.fxml","admin");
    }
    public void GoToAllGroups() {
        SceneManager.openNewWindow("Admin/DetailGroups.fxml","admin");
    }
    public void GoToAllBooks() {
        SceneManager.openNewWindow("Admin/DetailBooks.fxml","admin");
    }
    public void GoToAssistantPage() {
        SceneManager.changeScene("Students/student.fxml", "Assistant");
    }

    /***********/
    public void BackToLogin1()
    {
        SceneManager.changeScene("Login/Login.fxml", "Admin");
    }
    @FXML
    void GoToUpdatePage(ActionEvent event) {
        UpdateAssistant();
    }
    @FXML
    void AddAssistant(ActionEvent event) {
        AddAssistant();
    }
    //endregion

    //region Add Section
    public void AddAssistant() {
        for (TextField text : arrayOfAddTextFields) {
            if (text.getText().isEmpty()) {
                System.out.println("Empty");
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "اضافة سكرتير", "يرجى ادخال جميع البيانات", false, true);
                return;
            }
        }
        if (AddDate.getValue() == null) { return; }

        ArrayList<Object> arrayOfStudentDetials = new ArrayList<>();
        arrayOfStudentDetials.add(Integer.parseInt(AddPasswordTextField.getText()));
        arrayOfStudentDetials.add(AddNameTextField.getText());
        arrayOfStudentDetials.add(AddAddressTextField.getText());
        arrayOfStudentDetials.add(AddSSNTextField.getText());
        arrayOfStudentDetials.add(Date.valueOf(AddDate.getValue()).toString());
        arrayOfStudentDetials.add(Date.valueOf(LocalDate.now()).toString());
        arrayOfStudentDetials.add(Integer.parseInt(AddSalaryTextField.getText()));

        try {
            boolean isInserted = SQLQueries.Insert(DatabaseTables.SECRETARY_DATA, arrayOfStudentDetials);
            if (!isInserted) return;

            int secretaryId = SQLQueries.GetInt("Secretary_Id", DatabaseTables.SECRETARY_DATA, "SSN", AddSSNTextField.getText());

            ArrayList<Object> arrayOfPhone = new ArrayList<>();
            arrayOfPhone.add(secretaryId);
            arrayOfPhone.add(AddPhoneTextField.getText());
            SQLQueries.Insert(DatabaseTables.SECRETARY_PHONES, arrayOfPhone);
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "اضافة سكرتير", "تم اضافة سكرتير", false, false);
        } catch (Exception e) {
            e.printStackTrace();
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "اضافة سكرتير", "لم يتم اضافة سكرتير", false, true);
        }

    }
    //endregion

    //region Search Section
    public void SearchAssistant() {
        int idAssistant = Integer.parseInt(SearchTextField.getText());
        String phoneNumber = SQLQueries.GetString("Secretary_Phone", DatabaseTables.SECRETARY_PHONES, "Secretary_Id", idAssistant);

        try {
            ResultSet rsDetial = SQLQueries.GetResults(DatabaseTables.SECRETARY_DATA, "Secretary_Id", idAssistant);
            rsDetial.next();

            AttendNameLabel.setText(rsDetial.getString("Secretary_Name"));
            AttendNumberLabel.setText(phoneNumber);
            AttendSalaryLabel.setText(String.valueOf(rsDetial.getInt("salary")));
            AttendBirthDateLabel.setText(rsDetial.getDate("BirthDay").toString());
            AttendJoinDateLabel.setText(rsDetial.getDate("Joined_Date").toString());
            AttendSSNLabel.setText(rsDetial.getString("SSN"));
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "بحث عن سكرتير", "تم البحث بنجاح", false, false);
        } catch (Exception e) {
            e.printStackTrace();

            AttendNameLabel.setText("-");
            AttendNumberLabel.setText("-");
            AttendSalaryLabel.setText("-");
            AttendBirthDateLabel.setText("-");
            AttendJoinDateLabel.setText("-");
            AttendSSNLabel.setText("-");
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "بحث عن سكرتير", "لم يتم البحث, تاكد من رقم السكرتير", false, true);
        }

    }
    //endregion

    //region Update Section
    public void CheckAssistant() {
        boolean isValid = Utilities.CheckDataToDisable("Secretary_Id", DatabaseTables.SECRETARY_DATA, "Secretary_Id", UpdateSearchTextField.getText());
        UpdateGridLayout.setDisable(isValid);
        if (!isValid)
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "بحث عن سكرتير", "تم البحث بنجاح", false, false);
        else
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "بحث عن سكرتير", "لم يتم البحث, اتاكد من رقم السكرتير", false, true);
    }

    public void UpdateAssistant() {

        int idAssistant = Integer.parseInt(UpdateSearchTextField.getText());

        Map<String, Object> mapOfTextFields = new HashMap<>();
        mapOfTextFields.put("Secretary_Name", UpdateNameTextField.getText());
        mapOfTextFields.put("Address", UpdateAddressTextField.getText());
        String salary = UpdateSalaryTextField.getText();
        mapOfTextFields.put("salary", (!salary.isEmpty()) ? Integer.parseInt(UpdateSalaryTextField.getText()) : "");
        mapOfTextFields.put("SSN", UpdateSNNTextField.getText());

        boolean isUpdated = false;
        for (Map.Entry<String, Object> item : mapOfTextFields.entrySet())
        {
            if (item.getValue() instanceof String)
            {
                if (item.getValue().toString().isEmpty()) continue;
            }

            isUpdated = SQLQueries.UpdateSql(DatabaseTables.SECRETARY_DATA, item.getKey(), "Secretary_Id", idAssistant, item.getValue());
        }
        if (!UpdatePhoneTextField.getText().isEmpty())
        {
            isUpdated = SQLQueries.UpdateSql(DatabaseTables.SECRETARY_PHONES, "Secretary_Phone", "Secretary_Id", idAssistant, UpdatePhoneTextField.getText());
        }

        if (UpdateBirthDate.getValue() != null)
        {
            Date date = Date.valueOf(UpdateBirthDate.getValue().toString());

            isUpdated = SQLQueries.UpdateSql(DatabaseTables.SECRETARY_DATA, "BirthDay", "Secretary_Id", idAssistant, date.toString());
        }

        if (isUpdated)
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تحديث بيانات سكرتير", "تم تحديث البيانات بنجاح", false, false);
    }
    //endregion

    //region Delete Section
    public void DeleteAssistant() {
        if (DeleteTextField.getText().isEmpty()) return;

        int id = Integer.parseInt(DeleteTextField.getText());
        Map<String, Object> mp = new HashMap<>();
        mp.put("Secretary_Id", id);
        SQLQueries.Delete(DatabaseTables.SECRETARY_DATA, mp);
        SQLQueries.Delete(DatabaseTables.SECRETARY_ATTENDANCE, mp);
        SQLQueries.Delete(DatabaseTables.SECRETARY_PHONES, mp);
        SQLQueries.Delete(DatabaseTables.SECRETARY_WORK_DAYS, mp);

        SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "حذف بيانات سكرتير", "تم حذف البيانات بنجاح", false, false);
    }
    //endregion

    //region Daily And All Paid Logs
    //region Daily Log
    private void InitializeDailyTable() {
        DailyDescription.setCellValueFactory(new PropertyValueFactory<>("DailyDescription"));
        DailyPaid.setCellValueFactory(new PropertyValueFactory<>("DailyPaid"));
        DailyDate.setCellValueFactory(new PropertyValueFactory<>("DailyDate"));

        RefreshDailyTable();
        DailyPaidMoney();
    }
    public void RefreshDailyTable() {
        ObservableList<DailyLog> data = FXCollections.observableArrayList();

        data.addAll(GetBigData.GetDailyRecords());

        DailyTable.setItems(data);
        DailyPaidMoney();
    }
    private void DailyPaidMoney(){
        String dailyDate = Date.valueOf(LocalDate.now()).toString();
        int res = SQLQueries.SumOfColumn(DatabaseTables.MONEY, "money", "dateMoney", dailyDate);
        System.out.println(res);
        totalMoney.setText(String.valueOf(res));
    }
    public void makePdfDailyPaidMoney(){
        GeneratePDF generatePDF = new GeneratePDF();
        generatePDF.dailyPaidPDF();
    }
    //endregion

    //region All Paid Log
    private void InitializeAllPaidTable() {
        AllPaidDescription.setCellValueFactory(new PropertyValueFactory<>("DailyDescription"));
        AllPaidPaid.setCellValueFactory(new PropertyValueFactory<>("DailyPaid"));
        AllPaidDate.setCellValueFactory(new PropertyValueFactory<>("DailyDate"));

        RefreshAllPaidTable();
        AllPaidMoney();
    }

    public void RefreshAllPaidTable() {
        ObservableList<AllPaidLog> data = FXCollections.observableArrayList();

        ArrayList<AllPaidLog> allPaidLogs = new ArrayList<>();
        try {
            ResultSet rs = SQLQueries.GetResults(DatabaseTables.MONEY);

            while (rs.next())
            {
                String description = rs.getString("descriptionMoney");
                int paid = rs.getInt("money");
                Date date = rs.getDate("dateMoney");

                allPaidLogs.add(new AllPaidLog(
                        description,
                        paid,
                        date.toString()
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        data.addAll(allPaidLogs);

        AllPaidTable.setItems(data);
        AllPaidMoney();
    }

    private void AllPaidMoney(){
        int res = SQLQueries.SumOfColumn(DatabaseTables.MONEY, "money");
        totalAllPaidMoney.setText(String.valueOf(res));
    }

    public void makePdfAllPaidMoney(){
        GeneratePDF generatePDF = new GeneratePDF();
        generatePDF.totalPaidPDF();
    }
    //endregion
    //endregion

    //region assistance attendance log

    @FXML private TableView<AssistanceAttendance> AllAttendanceTable;
    @FXML private TableColumn<AssistanceAttendance, String> AllAttendanceID;
    @FXML private TableColumn<AssistanceAttendance, Integer> AllAttendanceName;
    @FXML private TableColumn<AssistanceAttendance, String> AllAttendanceDate;

    public class AssistanceAttendance {
        private String date;
        private int id;
        private String name;

        public AssistanceAttendance(String date, int id, String name) {
            this.date = date;
            this.id = id;
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
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
    }

    private void InitializeAllAssistanceAttendanceTable() {
        AllAttendanceID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AllAttendanceName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AllAttendanceDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        RefreshAllAssistanceAttendanceTable();
    }
    public void RefreshAllAssistanceAttendanceTable() {
        ObservableList<AssistanceAttendance> data = FXCollections.observableArrayList();

        ArrayList<AssistanceAttendance> assAttendance = new ArrayList<>();
        try {
            ResultSet rs = SQLQueries.GetResults(DatabaseTables.SECRETARY_ATTENDANCE);

            while (rs.next())
            {
                int idAssistance = rs.getInt("Secretary_Id");
                String date = rs.getDate("Attendance_Date").toString();

                String nameAssistance = SQLQueries.GetString("Secretary_Name", DatabaseTables.SECRETARY_DATA, "Secretary_Id", idAssistance);



                assAttendance.add(new AssistanceAttendance(
                        date,
                        idAssistance,
                        nameAssistance
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        data.addAll(assAttendance);

        AllAttendanceTable.setItems(data);
    }

    //endregion

    //region Students Search
    //region Search Students Table
    @FXML private ComboBox searchGradeId;
    @FXML private ComboBox searchGroupId;
    //endregion
    Map<Object, Object> GradeMap;
    Map<Object, Object> GroupMap;
    private void SetUpStudentsSearchComboBox() {
        GradeMap = SQLQueries.GetMapString("Grade_Id", "Garade_Name", DatabaseTables.GRADES);
        Utilities.SetComboBox(searchGradeId, GradeMap);

        GroupMap = SQLQueries.GetMapString("ID", "name", DatabaseTables.GROUP);
        Utilities.SetComboBox(searchGroupId, GroupMap);
    }
    public void GoToListOfStudent() {
        if (searchGradeId.getValue() == null || searchGroupId.getValue() == null)
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "قائمة الطلاب", "يرجى ادخال جميع البيانات", false, true);
            return;
        }

        int gradeId = (Integer)Utilities.CheckDataComboBox(searchGradeId, GradeMap);
        int groupId = (Integer)Utilities.CheckDataComboBox(searchGroupId, GroupMap);

        Map<String, Object> checkers = new HashMap<>();
        checkers.put("Grade_Id", gradeId);
        checkers.put("Group_Student", groupId);
        ResultSet rs = SQLQueries.GetResults(DatabaseTables.STUDENT_GENERAL_INFO, checkers);

        ArrayList<StudentProfile> students = new ArrayList<>();

        try {
            while (rs.next())
            {
                StudentProfile student = new StudentProfile();
                student.setStudentID(rs.getInt("Student_Id"));
                student.setStudentName(rs.getString("Name"));
                student.setStudentGrade(searchGradeId.getValue().toString());
                student.setStudentGroup(searchGroupId.getValue().toString());
                student.setStudentName(rs.getString("Name"));
                students.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ListOfStudentController controller = SceneManager.openNewWindow("Admin/ListOfStudent.fxml", "Admin");
        controller.SendData(students);
    }
    //endregion

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
    //endregion

    //region Add Grades
        //region Grade Variables Section
        @FXML private TextField GradeNameTextField;
        @FXML private TextField GradePrefixTextField;
        @FXML private TextField GradeDeleteTextField;
        //endregion
        public void AddGrade() {
            if (GradeNameTextField.getText().isEmpty() || GradePrefixTextField.getText().isEmpty())
            {
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل صف دراسى", "يرجى ادخال جميع البيانات", false, true);
                return;
            }

            String gradeName = GradeNameTextField.getText();
            int prefix = Integer.parseInt(GradePrefixTextField.getText());

            int countPrefixChecker = SQLQueries.GetCount("Grade_Id", DatabaseTables.GRADES, "Garade_Prefix", prefix);

            if (countPrefixChecker > 0)
            {
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل صف دراسى", "يرجى اختيار ID مختلف", false, true);
                return;
            }

            ArrayList<Object> columns = new ArrayList<>();
            columns.add("Garade_Name");
            columns.add("Garade_Prefix");

            ArrayList<Object> values = new ArrayList<>();
            values.add(gradeName);
            values.add(prefix);

            boolean isInserted = SQLQueries.Insert(DatabaseTables.GRADES, columns, values);
            if (isInserted)
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل صف دراسى", "تم حفظ الصف الدراسى", false, false);
            else
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل صف دراسى", "حدث خطأ فى تسجيل الصف الدراسى", false, true);
        }
        public void DeleteGrade() {
            if (GradeDeleteTextField.getText().isEmpty())
            {
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح صف دراسى", "يرجى ادخال الID", false, true);
                return;
            }

            Map<String, Object> mapCondition = new HashMap<>();
            mapCondition.put("Grade_Id", Integer.parseInt(GradeDeleteTextField.getText()));
            boolean isDeleted = SQLQueries.Delete(DatabaseTables.GRADES, mapCondition);
            if (isDeleted)
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح صف دراسى", "تم مسح الصف الدراسى", false, false);
            else
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح صف دراسى", "حدث خطأ فى مسح الصف الدراسى", false, true);


        }
    //endregion

    //region Groups
        //region variables
        @FXML private TextField GroupNameTextField;
        @FXML private TextField GroupSizeTextField;
        @FXML private TextField GroupDeleteTextField;
        @FXML private ComboBox GradeGroupComboBox;
        Map<Object, Object> GradeGroupMap;
        //endregion
        private void SetUpGroupComboBox() {
            GradeGroupMap = SQLQueries.GetMapString("Grade_Id", "Garade_Name", DatabaseTables.GRADES);
            Utilities.SetComboBox(GradeGroupComboBox, GradeGroupMap);
        }
        public void AddGroup() {
            if (GroupNameTextField.getText().isEmpty() || GroupSizeTextField.getText().isEmpty() || GradeGroupComboBox.getValue() == null)
            {
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل المجموعة دراسى", "يرجى ادخال جميع البيانات", false, true);
                return;
            }

            String gradeName = GroupNameTextField.getText();
            int sizeStudents = Integer.parseInt(GroupSizeTextField.getText());
            int groupId = (Integer)Utilities.CheckDataComboBox(GradeGroupComboBox, GradeGroupMap);

            ArrayList<Object> columns = new ArrayList<>();
            columns.add("name");
            columns.add("size");
            columns.add("Grade_Id");

            ArrayList<Object> values = new ArrayList<>();
            values.add(gradeName);
            values.add(sizeStudents);
            values.add(groupId);

            boolean isInserted = SQLQueries.Insert(DatabaseTables.GROUP, columns, values);
            if (isInserted)
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل المجموعة دراسى", "تم حفظ المجموعة الدراسى", false, false);
            else
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل المجموعة دراسى", "حدث خطأ فى تسجيل المجموعة الدراسى", false, true);
        }

        public void DeleteGroup() {
        if (GroupDeleteTextField.getText().isEmpty())
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح المجموعة دراسى", "يرجى ادخال الID", false, true);
            return;
        }

        Map<String, Object> mapCondition = new HashMap<>();
        mapCondition.put("ID", Integer.parseInt(GroupDeleteTextField.getText()));
        boolean isDeleted = SQLQueries.Delete(DatabaseTables.GROUP, mapCondition);
        if (isDeleted)
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح المجموعة دراسى", "تم مسح المجموعة الدراسى", false, false);
        else
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح المجموعة دراسى", "حدث خطأ فى مسح المجموعة الدراسى", false, true);


    }
    //endregion

    //region Add Exam
        //region Exam Variables Section
        @FXML private TextField ExamNameTextField;
        @FXML private TextField ExamDegreeTextField;
        @FXML private ComboBox ExamComboBox;
        @FXML private CheckBox ExamCheckBox;
        @FXML private TextField ExamDeleteTextField;
        Map<Object, Object> ExamMap;
        //endregion
        private void SetUpExamComboBox() {
            ExamMap = SQLQueries.GetMapString("Grade_Id", "Garade_Name", DatabaseTables.GRADES);
            Utilities.SetComboBox(ExamComboBox, ExamMap);
        }
        public void AddExam() {
            if (ExamNameTextField.getText().isEmpty() || ExamDegreeTextField.getText().isEmpty() || ExamComboBox.getValue() == null)
            {
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل امتحان", "يرجى ادخال جميع البيانات", false, true);
                return;
            }

            String examName = ExamNameTextField.getText();
            int examDegree = Integer.parseInt(ExamDegreeTextField.getText());
            int comboBoxResult = (Integer) Utilities.CheckDataComboBox(ExamComboBox, ExamMap);
            boolean isChecked = ExamCheckBox.isSelected();

            ArrayList<Object> columns = new ArrayList<>();
            columns.add("Exam_Name");
            columns.add("Exam_Degree");
            columns.add("IsFinal");
            columns.add("Grade_Id");

            ArrayList<Object> values = new ArrayList<>();
            values.add(examName);
            values.add(examDegree);
            values.add(isChecked);
            values.add(comboBoxResult);

            boolean isInserted = SQLQueries.Insert(DatabaseTables.EXAMS, columns, values);
            if (isInserted)
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل امتحان", "تم حفظ الامتحان", false, false);
            else
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل امتحان", "حدث خطأ فى تسجيل الامتحان", false, true);
        }
        public void DeleteExam() {
            if (ExamDeleteTextField.getText().isEmpty())
            {
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح تسجيل امتحان", "يرجى ادخال الID", false, true);
                return;
            }

            Map<String, Object> mapCondition = new HashMap<>();
            mapCondition.put("Exam_ID", Integer.parseInt(ExamDeleteTextField.getText()));
            boolean isDeleted = SQLQueries.Delete(DatabaseTables.EXAMS, mapCondition);
            if (isDeleted)
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح تسجيل امتحان", "تم مسح تسجيل امتحان", false, false);
            else
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح تسجيل امتحان", "حدث خطأ فى مسح تسجيل امتحان", false, true);


        }
    //endregion

    //region Add Books
        //region Book Variables Section
        @FXML private TextField BookNameTextField;
        @FXML private TextField BookPriceTextField;
        @FXML private ComboBox BookComboBox;
        @FXML private TextField BookDeleteTextField;
        Map<Object, Object> BookMap;
        //endregion
        private void SetUpBookComboBox() {
            BookMap = SQLQueries.GetMapString("Grade_Id", "Garade_Name", DatabaseTables.GRADES);
            Utilities.SetComboBox(BookComboBox, BookMap);
        }
        public void AddBook() {
            if (BookNameTextField.getText().isEmpty() || BookPriceTextField.getText().isEmpty() || BookComboBox.getValue() == null)
            {
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل كتاب", "يرجى ادخال جميع البيانات", false, true);
                return;
            }

            String bookName = BookNameTextField.getText();
            int bookPrice = Integer.parseInt(BookPriceTextField.getText());
            int comboBoxResult = (Integer) Utilities.CheckDataComboBox(BookComboBox, BookMap);

            ArrayList<Object> columns = new ArrayList<>();
            columns.add("Name");
            columns.add("Book_Price");
            columns.add("Grade");

            ArrayList<Object> values = new ArrayList<>();
            values.add(bookName);
            values.add(bookPrice);
            values.add(comboBoxResult);

            boolean isInserted = SQLQueries.Insert(DatabaseTables.BOOKS, columns, values);
            if (isInserted)
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل كتاب", "تم حفظ تسجيل الكتاب", false, false);
            else
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل كتاب", "حدث خطأ فى تسجيل تسجيل الكتاب", false, true);
        }
        public void DeleteBook() {
            if (BookDeleteTextField.getText().isEmpty())
            {
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح كتاب", "يرجى ادخال الID", false, true);
                return;
            }

            Map<String, Object> mapCondition = new HashMap<>();
            mapCondition.put("Book_Id", Integer.parseInt(BookDeleteTextField.getText()));
            boolean isDeleted = SQLQueries.Delete(DatabaseTables.BOOKS, mapCondition);
            if (isDeleted)
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح كتاب", "تم مسح الكتاب", false, false);
            else
                SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "مسح كتاب", "حدث خطأ فى مسح الكتاب", false, true);
        }
    //endregion

    //region Assistance Table
    /*
        @FXML private TableView<AllPaidLog> AssTable;
        @FXML private TableColumn<AllPaidLog, Integer> AssID;
        @FXML private TableColumn<AllPaidLog, String> AssName;
        @FXML private TableColumn<AllPaidLog, String> AssAddress;
        @FXML private TableColumn<AllPaidLog, String> AssDate;
        @FXML private TableColumn<AllPaidLog, Integer> AssSSN;
        @FXML private TableColumn<AllPaidLog, Integer> AssSalary;
     */
    private void InitializeAllAssistanceTable() {
        AssID.setCellValueFactory(new PropertyValueFactory<>("AssID"));
        AssName.setCellValueFactory(new PropertyValueFactory<>("AssName"));
        AssAddress.setCellValueFactory(new PropertyValueFactory<>("AssAddress"));
        AssDate.setCellValueFactory(new PropertyValueFactory<>("AssDate"));
        AssSSN.setCellValueFactory(new PropertyValueFactory<>("AssSSN"));
        AssSalary.setCellValueFactory(new PropertyValueFactory<>("AssSalary"));

        RefreshAllAssistanceTable();
        AllPaidMoney();
    }

    public void RefreshAllAssistanceTable() {
        ObservableList<Assistance> data = FXCollections.observableArrayList();

        ArrayList<Assistance> allPaidLogs = GetBigData.GetAsssistanceRecords();

        data.addAll(allPaidLogs);

        AssTable.setItems(data);
    }
    //endregion
}
