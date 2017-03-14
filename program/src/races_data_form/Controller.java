package races_data_form;

import base.AlertsBuilder;
import base.DataFormController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import models.Race;

import java.util.Calendar;

/**
 * Created by shrralis on 3/13/17.
 */
public class Controller extends DataFormController {
    {
        objectToProcess = new Race();
    }

    @FXML public TextField year;
    @FXML public TextField country;
    @FXML public TextField city;
    @FXML public TextField track_name;
    @FXML public TextField laps;
    @FXML public TextField lap_length;
    @FXML public CheckBox bYear;
    @FXML public CheckBox bCountry;
    @FXML public CheckBox bCity;
    @FXML public CheckBox bTrack_name;
    @FXML public CheckBox bLaps;
    @FXML public CheckBox bLap_length;

    @Override
    protected boolean areTheFieldsValidForAdding() {
        if (isAnyTextFieldEmpty()) {
            return false;
        }

        if (!year.getText().trim().matches("^\\d+$")) {
            AlertsBuilder.start()
                    .setAlertType(Alert.AlertType.WARNING)
                    .setTitle("Помилка")
                    .setHeaderText("Помилка вводу")
                    .setContentText("Поле " + year.getPromptText() + " повинно містити ціле число!")
                    .build()
                    .showAndWait();
            return false;
        } else {
            int iYear = Integer.parseInt(year.getText().trim());

            if (iYear < 1970 || iYear > Calendar.getInstance().get(Calendar.YEAR)) {
                AlertsBuilder.start()
                        .setAlertType(Alert.AlertType.WARNING)
                        .setTitle("Помилка")
                        .setHeaderText("Помилка вводу")
                        .setContentText("Поле " + year.getPromptText() + " повинно містити рік в межах від 1970–" +
                                Calendar.getInstance().get(Calendar.YEAR) + "!")
                        .build()
                        .showAndWait();
                return false;
            }
        }

        if (!laps.getText().trim().matches("^\\d+$")) {
            AlertsBuilder.start()
                    .setAlertType(Alert.AlertType.WARNING)
                    .setTitle("Помилка")
                    .setHeaderText("Помилка вводу")
                    .setContentText("Поле " + laps.getPromptText() + " повинно містити ціле число!")
                    .build()
                    .showAndWait();
            return false;
        }

        if (!lap_length.getText().trim().matches("^\\d+(\\.\\d+)?$")) {
            AlertsBuilder.start()
                    .setAlertType(Alert.AlertType.WARNING)
                    .setTitle("Помилка")
                    .setHeaderText("Помилка вводу")
                    .setContentText("Поле " + lap_length.getPromptText() + " повинно містити ціле або дробове число!\n" +
                            "Приклад: 1.09")
                    .build()
                    .showAndWait();
            return false;
        }
        return true;
    }
    @Override
    protected void objectToProcessBasedOnFields() {
        if (bYear.isSelected()) {
            ((Race) objectToProcess).setYear(year.getText().trim().isEmpty() ? null : new Integer(year.getText().trim()));
        }

        if (bCountry.isSelected()) {
            ((Race) objectToProcess).setCountry(country.getText().trim().isEmpty() ? null : country.getText().trim());
        }

        if (bCity.isSelected()) {
            ((Race) objectToProcess).setCity(city.getText().trim().isEmpty() ? null : city.getText().trim());
        }

        if (bTrack_name.isSelected()) {
            ((Race) objectToProcess).setTrack_name(track_name.getText().trim().isEmpty() ? null : track_name.getText().trim());
        }

        if (bLaps.isSelected()) {
            ((Race) objectToProcess).setLaps(laps.getText().trim().isEmpty() ? null :
                    new Integer(laps.getText().trim()));
        }

        if (bLap_length.isSelected()) {
            ((Race) objectToProcess).setLap_length(lap_length.getText().trim().isEmpty() ? null :
                    new Double(lap_length.getText().trim()));
        }
    }
    @Override
    protected String getDatabaseTableName() {
        return "races";
    }
}
