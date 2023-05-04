package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.AlertBox.AlertBoxController;

import java.io.IOException;

public class SceneManager {

    public static <T> T changeScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SceneManager.class.getResource(fxmlFile));
            Parent root = loader.load();

            Main.window.setTitle(title);
            Main.window.setScene(new Scene(root));
            Main.window.show();

            return loader.getController();
        } catch (IOException e) {
            openAlertBox(AlertBoxController.PATH, "Error", "Error happened, Please Try again", false, true);
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T openNewWindow(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SceneManager.class.getResource(fxmlFile));
            Parent root = loader.load();

            Stage newWindow = new Stage();
            newWindow.setTitle(title);
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.setResizable(false);

            newWindow.setScene(new Scene(root));
            newWindow.show();
            return loader.getController();
        } catch (IOException e) {
            openAlertBox(AlertBoxController.PATH, "Error", "Error happened, Please Try again", false, true);
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean openAlertBox(String fxmlFile, String title, String message, boolean valueIsToCheck, boolean isError) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SceneManager.class.getResource(fxmlFile));
            Parent root = loader.load();

            AlertBoxController alertBox = loader.getController();
            alertBox.setTitleAndMessage(title, message, valueIsToCheck, isError);

            Stage newWindow = new Stage();
            newWindow.setTitle(title);
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.setResizable(false);

            newWindow.setScene(new Scene(root));
            newWindow.showAndWait();

            AlertBoxController alert = loader.getController();
            return alert.result;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
