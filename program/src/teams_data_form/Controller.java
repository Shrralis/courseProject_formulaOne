package teams_data_form;

import base.AlertsBuilder;
import base.DataFormComboBoxController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Car;
import models.DateWorker;
import models.Team;

import java.lang.reflect.Field;

/**
 * Created by shrralis on 3/13/17.
 */
public class Controller extends DataFormComboBoxController {
    {
        objectToProcess = new Team();
    }

    @FXML public TextField name;
    @FXML public DatePicker birthdate;
    @FXML public DatePicker first_race_date;
    @FXML public TextField first_race_place;
    @FXML public ComboBox<Car> car;
    @FXML public CheckBox bName;
    @FXML public CheckBox bBirthdate;
    @FXML public CheckBox bFirst_race_date;
    @FXML public CheckBox bFirst_race_place;
    @FXML public CheckBox bCar;

    @FXML
    private void initialize() {
        /*car.setCellFactory(new Callback<ListView<Car>, ListCell<Car>>() {
            @Override public ListCell<Car> call(ListView<Car> param) {
                final ListCell<Car> cell = new ListCell<Car>() {
                    {
                        super.setPrefWidth(100);
                    }
                    @Override public void updateItem(Car item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getModel());
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }

        });*/
    }
    @Override
    protected final boolean isAnyTextFieldEmpty() {
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
    protected final boolean areTheFieldsValidForAdding() {
        return !isAnyTextFieldEmpty() && !isAnyComboBoxEmpty();
    }
    @Override
    protected final void objectToProcessBasedOnFields() {
        if (bName.isSelected()) {
            ((Team) objectToProcess).setName(name.getText().trim().isEmpty() ? null : name.getText().trim());
        }

        if (bBirthdate.isSelected()) {
            ((Team) objectToProcess).setBirthdate(birthdate.getValue() == null ? null : DateWorker.convertToDate(birthdate));
        }

        if (bFirst_race_date.isSelected()) {
            ((Team) objectToProcess).setFirst_race_date(first_race_date.getValue() == null ? null :
                    DateWorker.convertToDate(first_race_date));
        }

        if (bFirst_race_place.isSelected()) {
            ((Team) objectToProcess).setFirst_race_place(bFirst_race_place.getText().trim().isEmpty() ? null :
                    bFirst_race_place.getText().trim());
        }

        if (bCar.isSelected()) {
            ((Team) objectToProcess).setCar(car.getValue());
            System.out.println(car.getValue());
        }
    }
    @Override
    protected final String getDatabaseTableName() {
        return "teams";
    }
}
