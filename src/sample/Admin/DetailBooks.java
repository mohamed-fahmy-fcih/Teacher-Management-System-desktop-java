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

public class DetailBooks implements Initializable {

    public class Data {
        private int id;
        private String grade;
        private String name;
        private double price;

        public Data(int id, String grade, String name, double price) {
            this.id = id;
            this.grade = grade;
            this.name = name;
            this.price = price;
        }

        //region Getter And Setter
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
//endregion
    }

    //region Variables
    @FXML private TableView<Data> Table;
    @FXML private TableColumn<Data, Integer> ColumnID;
    @FXML private TableColumn<Data, String> ColumnGrade;
    @FXML private TableColumn<Data, String> ColumnName;
    @FXML private TableColumn<Data, Integer> ColumnPrice;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        ColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        RefreshTable();
    }

    private void RefreshTable() {
        ObservableList<Data> data = FXCollections.observableArrayList();

        ResultSet rs = SQLQueries.GetResults(DatabaseTables.BOOKS);

        try {
            while (rs.next())
            {
                int gradeId = rs.getInt("Grade");
                String grade = SQLQueries.GetString("Garade_Name", DatabaseTables.GRADES, "Grade_Id", gradeId);

                Data row = new Data(
                        rs.getInt("Book_Id"),
                        grade,
                        rs.getString("Name"),
                        rs.getDouble("Book_Price")
                );
                data.add(row);
            }

            Table.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
