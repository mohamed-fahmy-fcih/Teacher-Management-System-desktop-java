package sample.Admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Utilities.DatabaseTables;
import sample.Utilities.SQLQueries;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class DetailExams implements Initializable {

    public class Data {
        private int id;
        private String name;
        private double degree;
        private String grade;
        private String isFinal;

        public Data(int id, String name, double degree, String grade, String isFinal) {
            this.id = id;
            this.name = name;
            this.degree = degree;
            this.grade = grade;
            this.isFinal = isFinal;
        }

        //region Getter And Setter

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

        public double getDegree() {
            return degree;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getIsFinal() {
            return isFinal;
        }

        public void setIsFinal(String isFinal) {
            this.isFinal = isFinal;
        }

        //endregion
    }

    //region Variables
    @FXML private TableView<Data> Table;
    @FXML private TableColumn<Data, Integer> ColumnID;
    @FXML private TableColumn<Data, String> ColumnName;
    @FXML private TableColumn<Data, Integer> ColumnDegree;
    @FXML private TableColumn<Data, String> ColumnGrade;
    @FXML private TableColumn<Data, String> ColumnIsFinal;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnDegree.setCellValueFactory(new PropertyValueFactory<>("degree"));
        ColumnGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        ColumnIsFinal.setCellValueFactory(new PropertyValueFactory<>("isFinal"));

        RefreshTable();
    }

    private void RefreshTable() {
        ObservableList<Data> data = FXCollections.observableArrayList();

        ResultSet rs = SQLQueries.GetResults(DatabaseTables.EXAMS);

        try {
            while (rs.next())
            {
                int gradeId = rs.getInt("Grade_Id");
                String grade = SQLQueries.GetString("Garade_Name", DatabaseTables.GRADES, "Grade_Id", gradeId);

                boolean isFinal = rs.getBoolean("IsFinal");
                String finalStr = (isFinal) ? "نعم" : "لا";

                Data row = new Data(
                        rs.getInt("Exam_ID"),
                        rs.getString("Exam_Name"),
                        rs.getDouble("Exam_Degree"),
                        grade,
                        finalStr
                );
                data.add(row);
            }

            Table.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}