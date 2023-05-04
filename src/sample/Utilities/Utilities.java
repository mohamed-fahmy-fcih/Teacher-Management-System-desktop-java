package sample.Utilities;

import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Map;

public class Utilities {
    public static void ActivePage(ButtonSwitcher activeButton, ArrayList<ButtonSwitcher> allButtons)
    {
        for (ButtonSwitcher button : allButtons)
        {
            if (button != activeButton)
                button.SetActiveButton(false);
            else
                button.SetActiveButton(true);
        }
    }

    public static boolean CheckDataToDisable(String columnName, String databaseTable, String where, String valueToCheck) {

        if (valueToCheck.isEmpty()) return true;

        int idAssistant = Integer.parseInt(valueToCheck);

        int result = SQLQueries.GetInt(columnName, databaseTable, where, idAssistant);

        return (result == -1);
    }

    public static void SetComboBox(ComboBox comboBox, Map<Object, Object> mapWillEnter) {

        comboBox.getItems().clear();
        if (mapWillEnter != null) {
            for (Map.Entry<Object, Object> object : mapWillEnter.entrySet())
            {
                comboBox.getItems().add(object.getValue());
            }
        }
    }

    public static Object CheckDataComboBox(ComboBox comboBox, Map<Object, Object> map)
    {
        String valueChecked = comboBox.getValue().toString();
        int idGrade = -1;

        for (Map.Entry<Object, Object> object : map.entrySet()) {
            if (object.getValue() == valueChecked)
            {
                idGrade = Integer.parseInt(object.getKey().toString());
                break;
            }
        }
        return idGrade;
    }
}