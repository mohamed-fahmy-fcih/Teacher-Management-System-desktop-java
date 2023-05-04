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

public class DetailGrades implements Initializable {

    public class Data {
        private int id;
        private String name;
        private int prefix;

        public Data(int id, String name, int prefix) {
            this.id = id;
            this.name = name;
            this.prefix = prefix;
        }

        //region Getter And Setter
        public int getPrefix() {
            return prefix;
        }

        public void setPrefix(int prefix) {
            this.prefix = prefix;
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
        //endregion
    }

    //region Variables
    @FXML private TableView<Data> Table;
    @FXML private TableColumn<Data, Integer> ColumnID;
    @FXML private TableColumn<Data, Integer> ColumnName;
    @FXML private TableColumn<Data, Integer> ColumnPrefix;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColumnPrefix.setCellValueFactory(new PropertyValueFactory<>("prefix"));

        RefreshTable();
    }

    private void RefreshTable() {
        ObservableList<Data> data = FXCollections.observableArrayList();

        ResultSet rs = SQLQueries.GetResults(DatabaseTables.GRADES);

        try {
            while (rs.next())
            {
                Data row = new Data(
                        rs.getInt("Grade_Id"),
                        rs.getString("Garade_Name"),
                        rs.getInt("Garade_Prefix")
                );
                data.add(row);
            }

            Table.setItems(data);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
