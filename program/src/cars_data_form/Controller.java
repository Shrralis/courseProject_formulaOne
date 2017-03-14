package cars_data_form;

import base.AlertsBuilder;
import base.DataFormController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import models.Car;

/**
 * Created by shrralis on 3/13/17.
 */
@SuppressWarnings("unused")
public class Controller extends DataFormController {
    {
        objectToProcess = new Car();
    }

    @FXML public TextField model;
    @FXML public TextField chassis;
    @FXML public TextField engine_brand;
    @FXML public TextField engine_power;
    @FXML public TextField number_of_cylinders;
    @FXML public CheckBox bModel;
    @FXML public CheckBox bChassis;
    @FXML public CheckBox bEngine_brand;
    @FXML public CheckBox bEngine_power;
    @FXML public CheckBox bNumber_of_cylinders;

    @Override
    protected boolean areTheFieldsValidForAdding() {
        if (isAnyTextFieldEmpty()) {
            return false;
        }

        if (!number_of_cylinders.getText().trim().matches("^\\d+$")) {
            AlertsBuilder.start()
                    .setAlertType(Alert.AlertType.WARNING)
                    .setTitle("Помилка")
                    .setHeaderText("Помилка вводу")
                    .setContentText("Поле " + number_of_cylinders.getPromptText() + " повинно містити ціле число!")
                    .build()
                    .showAndWait();
            return false;
        }
        return true;
    }
    @Override
    protected void objectToProcessBasedOnFields() {
        if (bModel.isSelected()) {
            ((Car) objectToProcess).setModel(model.getText().trim().isEmpty() ? null : model.getText().trim());
        }

        if (bChassis.isSelected()) {
            ((Car) objectToProcess).setChassis(chassis.getText().trim().isEmpty() ? null : chassis.getText().trim());
        }

        if (bEngine_brand.isSelected()) {
            ((Car) objectToProcess).setEngine_brand(engine_brand.getText().trim().isEmpty() ? null : engine_brand.getText().trim());
        }

        if (bEngine_power.isSelected()) {
            ((Car) objectToProcess).setEngine_power(engine_power.getText().trim().isEmpty() ? null : engine_power.getText().trim());
        }

        if (bNumber_of_cylinders.isSelected()) {
            ((Car) objectToProcess).setNumber_of_cylinders(number_of_cylinders.getText().trim().isEmpty() ? null :
                    new Integer(number_of_cylinders.getText().trim()));
        }
    }
    @Override
    protected String getDatabaseTableName() {
        return "cars";
    }
}
