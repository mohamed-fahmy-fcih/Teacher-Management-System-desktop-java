package sample.Utilities;

import javafx.scene.control.ComboBox;

import java.util.Map;

public class ComboBoxHandler {
    public static void SetUpComboBox(Map<Object, Object> map, ComboBox comboBox) {
        comboBox.getItems().clear();
        if (map != null) {
            for (Map.Entry<Object, Object> object : map.entrySet())
            {
                comboBox.getItems().add(object.getValue());
            }
        }
    }
    public static Object CheckValueChecked(Map<Object, Object> map, ComboBox comboBox) {
        Object result = comboBox.getValue();
        Object data = -1;

        for (Map.Entry<Object, Object> object : map.entrySet()) {
            if (object.getValue() == result)
            {
                data = Integer.parseInt(object.getKey().toString());
                break;
            }
        }

        return data;
    }
}
