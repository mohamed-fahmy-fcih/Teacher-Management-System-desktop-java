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

public class DetailGroups implements Initializable {

    public class Data {
        private int id;
        private String name;
        private int currSize;
        private int maxSize;
        private String grade;

        public Data(int id, String name, int currSize, int maxSize, String grade) {
            this.id = id;
            this.name = name;
            this.currSize = currSize;
            this.maxSize = maxSize;
            this.grade = grade;
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

        public int getCurrSize() {
            return currSize;
        }

        public void setCurrSize(int currSize) {
            this.currSize = currSize;
        }

        public int getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }

    //region Variables
    @FXML private TableView<Data> Table;
    @FXML private TableColumn<Data, Integer> ColumnID;
    @FXML private TableColumn<Data, String> ColumnName;
    @FXML private TableColumn<Data, String> ColumnCurrSize;
    @FXML private TableColumn<Data, String> ColumnMaxSize;
    @FXML private TableColumn<Data, String> ColumnGrade;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnCurrSize.setCellValueFactory(new PropertyValueFactory<>("currSize"));
        ColumnMaxSize.setCellValueFactory(new PropertyValueFactory<>("maxSize"));
        ColumnGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        RefreshTable();
    }

    private void RefreshTable() {
        ObservableList<Data> data = FXCollections.observableArrayList();

        ResultSet rs = SQLQueries.GetResults(DatabaseTables.GROUP);

        try {
            while (rs.next())
            {
                int gradeId = rs.getInt("Grade_Id");
                String grade = SQLQueries.GetString("Garade_Name", DatabaseTables.GRADES, "Grade_Id", gradeId);
                int numberOfStudents = SQLQueries.GetCount("Group_Student", DatabaseTables.STUDENT_GENERAL_INFO, "Group_Student", rs.getInt("ID"));
                Data row = new Data(
                        rs.getInt("ID"),
                        rs.getString("name"),
                        numberOfStudents,
                        rs.getInt("size"),
                        grade
                );
                data.add(row);
            }

            Table.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
