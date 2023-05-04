package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.GeneratePDF.GeneratePDF;
import sample.Utilities.ConnectionUser;
public class Main extends Application {
    public  static Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception {
//        GeneratePDF generatePDF = new GeneratePDF("test.pdf");
//        generatePDF.test();
        ConnectionUser.MakeConnection();
        Parent root = FXMLLoader.load(getClass().getResource("Login/Login.fxml"));
        window=primaryStage;
        window.setTitle("Hello World");
        window.setScene(new Scene(root));
        window.setResizable(true);
        window.setMinHeight(691);
        window.setMinWidth(1074);
        window.setMaxHeight(900);
        window.setMaxWidth(1600);

        window.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}