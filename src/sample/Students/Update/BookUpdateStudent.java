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
import sample.Classes.OwnedBook;
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

public class BookUpdateStudent implements Initializable {
    //region Table
    @FXML private TableView<OwnedBook> BooksTable;
    @FXML private TableColumn<OwnedBook, String> BookNameCol;
    @FXML private TableColumn<OwnedBook, Integer> BookCurrCol;
    @FXML private TableColumn<OwnedBook, Integer> BookMaxCol;
    @FXML private TableColumn<OwnedBook, Date> BookDateCol;
    //endregion

    //region Text Fields
    @FXML private ComboBox BookComboBox;
    @FXML private TextField BookPaid;
    //endregion

    StudentProfile student;
    public void SendData(StudentProfile student) {
        this.student = student;
        refreshTimelineTable();
        SetComboGrade();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BookNameCol.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        BookCurrCol.setCellValueFactory(new PropertyValueFactory<>("paid"));
        BookMaxCol.setCellValueFactory(new PropertyValueFactory<>("fullPrice"));
        BookDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    Map<Object, Object> ExamComboMap = new HashMap<>();
    private void SetComboGrade() {
        ExamComboMap = SQLQueries.GetMapString("Book_Id", "Name", DatabaseTables.BOOKS, "Grade", student.getStudentGradeID());

        ComboBoxHandler.SetUpComboBox(ExamComboMap, BookComboBox);
    }

    private void refreshTimelineTable() {
        ObservableList<OwnedBook> data = FXCollections.observableArrayList();

        data.addAll(GetBigData.GetOwnedBooks(student.getStudentID(), student.getStudentGradeID()));

        BooksTable.setItems(data);
    }

    public void SaveBookData() {
        int resultComboBox = (Integer)ComboBoxHandler.CheckValueChecked(ExamComboMap, BookComboBox);

        if (resultComboBox == -1) {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","اضافة كتاب للطالب", "يرجى اختيار الكتاب", false, true);
            return;
        }

        if (BookPaid.getText().isEmpty())
        {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","اضافة كتاب للطالب", "يرجى ادخال المبلغ المدفوع", false, true);
            return;
        }

        int paid = Integer.parseInt(BookPaid.getText());

        try {

            if (!SceneManager.openAlertBox("AlertBox/AlertBox.fxml","اضافة كتاب للطالب", "هل انت تريد اضافة كتاب؟", true, false))
                return;

            ArrayList<Object> dataToInsert = new ArrayList<>();
            dataToInsert.add(student.getStudentID());
            dataToInsert.add(resultComboBox);
            dataToInsert.add(paid);
            dataToInsert.add(Date.valueOf(LocalDate.now()).toString());
            SQLQueries.Insert(DatabaseTables.BOOKS_SALES, dataToInsert);

            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add(student.getStudentID() + " Payed Book. ");
            arrayList.add(paid);
            arrayList.add(Date.valueOf(LocalDate.now()).toString());
            SQLQueries.Insert(DatabaseTables.MONEY, arrayList);

            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","اضافة كتاب للطالب", "تم اضافة كتاب للطالب", false, false);
        } catch (Exception e) {
            e.printStackTrace();
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml","اضافة كتاب للطالب", "حدث خطأ فى اضافة الكتاب فى قاعدة البيانات", false, true);
        }

        refreshTimelineTable();
    }
}