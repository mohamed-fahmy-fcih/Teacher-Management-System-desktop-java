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

public class DetailLectures implements Initializable {

    public class Data {
        private int id;
        private String name;
        private String grade;
        private String date;

        public Data(int id, String name, String grade, String date) {
            this.id = id;
            this.name = name;
            this.grade = grade;
            this.date = date;
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

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
        //endregion
    }

    //region Variables
    @FXML private TableView<Data> Table;
    @FXML private TableColumn<Data, Integer> ColumnID;
    @FXML private TableColumn<Data, String> ColumnNumber;
    @FXML private TableColumn<Data, String> ColumnGrade;
    @FXML private TableColumn<Data, String> ColumnDate;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnNumber.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        ColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        RefreshTable();
    }

    private void RefreshTable() {
        ObservableList<Data> data = FXCollections.observableArrayList();

        ResultSet rs = SQLQueries.GetResults(DatabaseTables.LECTURES);

        try {
            while (rs.next())
            {
                int gradeId = rs.getInt("Grade_Id");
                String grade = SQLQueries.GetString("Garade_Name", DatabaseTables.GRADES, "Grade_Id", gradeId);
                Data row = new Data(
                        rs.getInt("Lec_Id"),
                        rs.getString("Lec_Name"),
                        grade,
                        rs.getDate("Date").toString()
                );
                data.add(row);
            }

            Table.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
