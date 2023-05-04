package sample.AlertBox;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AlertBoxController {

    public static final String PATH = "AlertBox/AlertBox.fxml";

    @FXML
    private Label headerAlert;

    @FXML
    private Label logAlert;

    public boolean result;

    @FXML private HBox AlertTitleBackground;
    @FXML private HBox ConditionalHBox;
    @FXML private HBox AgreeHBox;

    public void isChecker(boolean value) {
        ConditionalHBox.setVisible(value);
        AgreeHBox.setVisible(!value);
    }

    public void isError(boolean value)
    {
        if (value)
        {
            AlertTitleBackground.getStyleClass().add("error-color");
        }
        else
        {
            AlertTitleBackground.getStyleClass().add("success-color");
        }
    }

    public void setTitleAndMessage(String title, String message, Boolean valueCheck, boolean isError) {
        headerAlert.setText(title);
        logAlert.setText(message);
        isChecker(valueCheck);
        isError(isError);
    }

    public void CheckTrue()
    {
        result = true;
        CloseWindow();
    }

    public void CheckFalse()
    {
        result = false;
        CloseWindow();
    }

    public void CloseWindow()
    {
        Stage stage = (Stage) headerAlert.getScene().getWindow();
        stage.close();
    }
}
