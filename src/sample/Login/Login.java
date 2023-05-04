package sample.Login;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.SceneManager;
import sample.Utilities.DatabaseTables;
import sample.Utilities.SQLQueries;
import sample.Utilities.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login {

    //region Variables
    @FXML private TextField UsernameTextField;
    @FXML private PasswordField PasswordTextField;
    //endregion

    public void CheckUser()
    {
//        String adminUser = "UdL+/F5BA";
//        String passwordUser = "ctCSZ5^Cb";
        String adminUser = "a";
        String passwordUser = "a";

        String user = UsernameTextField.getText();
        String pass = PasswordTextField.getText();

        if (CheckIsEmpty(user, pass)) return;

        if (CheckIsAdmin(adminUser, passwordUser, user, pass)) return;


        try {
            if (CheckIsSecretary(user, pass)) return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل الدخول", "ادخل البيانات صحيحة", false, true);
    }

    private boolean CheckIsSecretary(String user, String pass) throws Exception {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("Secretary_Name", user);

        conditions.put("Secretary_Id", Integer.parseInt(pass));
        int result = SQLQueries.GetInt("Secretary_Id", DatabaseTables.SECRETARY_DATA, conditions);

        if (result != -1)
        {
            User.setAdmin(false);
            Date date = Date.valueOf(LocalDate.now().toString());

            Map<String, Object> mapChecker = new HashMap<>();
            mapChecker.put("Secretary_Id", result);
            mapChecker.put("Attendance_Date", date.toString());
            int count = SQLQueries.GetCount( "Secretary_Id",DatabaseTables.SECRETARY_ATTENDANCE, mapChecker);

            if (count <= 0)
            {
                int dayIndex = LocalDate.now().getDayOfWeek().getValue();

                ArrayList<Object> columns = new ArrayList<>();
                columns.add("Secretary_Id");
                columns.add("Day_Id");
                columns.add("Attendance_Date");
                columns.add("Report");

                ArrayList<Object> values = new ArrayList<>();
                values.add(result);
                values.add(dayIndex + 1);
                values.add(date.toString());
                values.add("");

                SQLQueries.Insert(DatabaseTables.SECRETARY_ATTENDANCE, columns, values);
            }

            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل الدخول", "تم تسجيل الدخول بنجاح", false, false);
            SceneManager.changeScene("Students/student.fxml", "Secretary");
            return true;
        }

        return false;
    }

    private boolean CheckIsAdmin(String adminUser, String passwordUser, String user, String pass) {
        if (user.equals(adminUser) && pass.equals(passwordUser)) {
            User.setAdmin(true);
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل الدخول", "تم تسجيل الدخول بنجاح", false, false);
            SceneManager.changeScene("Admin/Admin.fxml", "Admin");
            return true;
        }
        return false;
    }

    private boolean CheckIsEmpty(String user, String pass) {
        if (user.isEmpty() || pass.isEmpty()) {
            SceneManager.openAlertBox("AlertBox/AlertBox.fxml", "تسجيل الدخول", "يرجى ادخال البيانات", false, true);
            return true;
        }
        return false;
    }
}

enum Days
{
    الاحد,
    الاثنين,
    الثلاثاء,
    الاربعاء,
    الخميس,
    الجمعة,
    السبت
}