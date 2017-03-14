package pilots_data_form;

import base.AlertsBuilder;
import base.DataFormController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.DateWorker;
import models.Pilot;

import java.lang.reflect.Field;

/**
 * Created by shrralis on 3/13/17.
 */
public class Controller extends DataFormController {
    {
        objectToProcess = new Pilot();
    }

    @FXML public TextField name;
    @FXML public TextField surname;
    @FXML public DatePicker birthdate;
    @FXML public TextField birthplace;
    @FXML public TextField marital_status;
    @FXML public TextField height;
    @FXML public TextField weight;
    @FXML public DatePicker first_race_date;
    @FXML public TextField first_race_place;
    @FXML public CheckBox bName;
    @FXML public CheckBox bSurname;
    @FXML public CheckBox bBirthdate;
    @FXML public CheckBox bBirthplace;
    @FXML public CheckBox bMarital_status;
    @FXML public CheckBox bHeight;
    @FXML public CheckBox bWeight;
    @FXML public CheckBox bFirst_race_date;
    @FXML public CheckBox bFirst_race_place;

    @Override
    protected boolean isAnyTextFieldEmpty() {
        for (Field field : getClass().getDeclaredFields()) {
            try {
                String fieldDaoName = "";
                boolean empty = false;

                if (!field.getName().equalsIgnoreCase("first_race_date")
                        && !field.getName().equalsIgnoreCase("first_race_place")) {
                    if (field.getType() == TextField.class) {
                        fieldDaoName = ((TextField) field.get(this)).getPromptText();
                        empty = ((TextField) field.get(this)).getText().trim().equalsIgnoreCase("");
                    } else if (field.getType() == DatePicker.class) {
                        fieldDaoName = ((DatePicker) field.get(this)).getPromptText();
                        empty = ((DatePicker) field.get(this)).getValue() == null;
                    }
                }

                if (empty) {
                    AlertsBuilder.start()
                            .setAlertType(Alert.AlertType.WARNING)
                            .setTitle("Помилка")
                            .setHeaderText("Помилка вводу")
                            .setContentText("Ви залишили поле " + fieldDaoName + " порожнім!")
                            .build()
                            .showAndWait();
                    return true;
                }
            } catch (IllegalAccessException ignored) {}
        }
        return false;
    }
    @Override
    protected boolean areTheFieldsValidForAdding() {
        if (isAnyTextFieldEmpty()) {
            return false;
        }

        if (!height.getText().trim().matches("^\\d+(\\.\\d+)?$")) {
            AlertsBuilder.start()
                    .setAlertType(Alert.AlertType.WARNING)
                    .setTitle("Помилка")
                    .setHeaderText("Помилка вводу")
                    .setContentText("Поле " + height.getPromptText() + " повинно містити ціле або дробове число!\n" +
                            "Приклад: 1.09")
                    .build()
                    .showAndWait();
            return false;
        }

        if (!weight.getText().trim().matches("^\\d+(\\.\\d+)?$")) {
            AlertsBuilder.start()
                    .setAlertType(Alert.AlertType.WARNING)
                    .setTitle("Помилка")
                    .setHeaderText("Помилка вводу")
                    .setContentText("Поле " + weight.getPromptText() + " повинно містити ціле або дробове число!\n" +
                            "Приклад: 1.09")
                    .buildOnFront(primaryStage)
                    .showAndWait();
            return false;
        }
        return true;
    }
    @Override
    protected void objectToProcessBasedOnFields() {
        if (bName.isSelected()) {
            objectToProcess.setName(name.getText().trim().isEmpty() ? null : name.getText().trim());
        }

        if (bSurname.isSelected()) {
            ((Pilot) objectToProcess).setSurname(surname.getText().trim().isEmpty() ? null : surname.getText().trim());
        }

        if (bBirthdate.isSelected()) {
            ((Pilot) objectToProcess).setBirthdate(birthdate.getValue() == null ? null : DateWorker.convertToDate(birthdate));
        }

        if (bBirthplace.isSelected()) {
            ((Pilot) objectToProcess).setBirthplace(birthplace.getText().trim().isEmpty() ? null : birthplace.getText().trim());
        }

        if (bMarital_status.isSelected()) {
            ((Pilot) objectToProcess).setMarital_status(marital_status.getText().trim().isEmpty() ? null :
                    marital_status.getText().trim());
        }

        if (bHeight.isSelected()) {
            ((Pilot) objectToProcess).setHeight(height.getText().trim().isEmpty() ? null :
                    new Double(height.getText().trim()));
        }

        if (bWeight.isSelected()) {
            ((Pilot) objectToProcess).setWeight(weight.getText().trim().isEmpty() ? null :
                    new Double(weight.getText().trim()));
        }

        if (bFirst_race_date.isSelected()) {
            ((Pilot) objectToProcess).setFirst_race_date(first_race_date.getValue() == null ? null :
                    DateWorker.convertToDate(first_race_date));
        }

        if (bFirst_race_place.isSelected()) {
            ((Pilot) objectToProcess).setFirst_race_place(first_race_place.getText().trim().isEmpty() ? null :
                    first_race_place.getText().trim());
        }
    }
    @Override
    protected String getDatabaseTableName() {
        return "pilots";
    }
}
