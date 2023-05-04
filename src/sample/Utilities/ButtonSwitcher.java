package sample.Utilities;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ButtonSwitcher
{
    private AnchorPane anchorPane;
    private HBox hBox;
    private Rectangle rectangle;

    public ButtonSwitcher(AnchorPane anchorPane, HBox hBox, Rectangle rectangle) {
        this.anchorPane = anchorPane;
        this.hBox = hBox;
        this.rectangle = rectangle;
    }

    public void SetOnAction(ArrayList<ButtonSwitcher> buttonSwitchers)
    {
        hBox.setOnMouseClicked(e->{
            Utilities.ActivePage(this, buttonSwitchers);
        });
    }

    public void SetActiveButton(boolean value)
    {
        anchorPane.setVisible(value);
        rectangle.setVisible(value);
    }
}
