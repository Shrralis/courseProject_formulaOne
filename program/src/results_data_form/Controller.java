package results_data_form;

import base.AlertsBuilder;
import base.DataFormComboBoxController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.*;

/**
 * Created by shrralis on 3/13/17.
 */
public class Controller extends DataFormComboBoxController {
    {
        objectToProcess = new Result();
    }

    @FXML public ComboBox<Race> race;
    @FXML public ComboBox<Pilot> pilot;
    @FXML public ComboBox<Team> team;
    @FXML public TextField race_time;
    @FXML public TextField pilot_points;
    @FXML public TextField team_points;
    @FXML public CheckBox bRace;
    @FXML public CheckBox bPilot;
    @FXML public CheckBox bTeam;
    @FXML public CheckBox bRace_time;
    @FXML public CheckBox bPilot_points;
    @FXML public CheckBox bTeam_points;

    @Override
    protected final boolean areTheFieldsValidForAdding() {
        if (isAnyTextFieldEmpty() || isAnyComboBoxEmpty()) {
            return false;
        }



        if (!race_time.getText().trim().matches("^\\d{2}:\\d{2}:\\d{2}$")) {
            AlertsBuilder.start()
                    .setAlertType(Alert.AlertType.WARNING)
                    .setTitle("Помилка")
                    .setHeaderText("Помилкка вводу")
                    .setContentText("Невірний формат поля час гонки!\n" +
                            "Приклад: 00:12:47 (гг:хв:сс)")
                    .buildOnFront(primaryStage)
                    .showAndWait();
            return false;
        } else if (DateWorker.convertToTime(race_time) == null) {
            AlertsBuilder.start()
                    .setAlertType(Alert.AlertType.WARNING)
                    .setTitle("Помилка")
                    .setHeaderText("Помилкка вводу")
                    .setContentText("Невірний час гонки!\n" +
                            "Максимум годин — 24, максимум хвилин — 60, максимум секунд — 60\n" +
                            "Приклад: 00:12:47 (гг:хв:сс)")
                    .buildOnFront(primaryStage)
                    .showAndWait();
            return false;
        }

        if (!pilot_points.getText().trim().matches("^\\d+$")) {
            AlertsBuilder.start()
                    .setAlertType(Alert.AlertType.WARNING)
                    .setTitle("Помилка")
                    .setHeaderText("Помилка вводу")
                    .setContentText("Поле " + pilot_points.getPromptText() + " повинно містити ціле число!")
                    .build()
                    .showAndWait();
        }

        if (!team_points.getText().trim().matches("^\\d+$")) {
            AlertsBuilder.start()
                    .setAlertType(Alert.AlertType.WARNING)
                    .setTitle("Помилка")
                    .setHeaderText("Помилка вводу")
                    .setContentText("Поле " + team_points.getPromptText() + " повинно містити ціле число!")
                    .build()
                    .showAndWait();
        }
        return true;
    }
    @Override
    protected final void objectToProcessBasedOnFields() {
        if (bRace.isSelected()) {
            ((Result) objectToProcess).setRace(race.getValue());
        }

        if (bPilot.isSelected()) {
            ((Result) objectToProcess).setPilot(pilot.getValue());
        }

        if (bTeam.isSelected()) {
            ((Result) objectToProcess).setTeam(team.getValue());
        }

        if (bRace_time.isSelected()) {
            ((Result) objectToProcess).setRace_time(race_time.getText().trim().isEmpty() ? null :
                    DateWorker.convertToTime(race_time));
        }

        if (bPilot_points.isSelected()) {
            ((Result) objectToProcess).setPilot_points(pilot_points.getText().trim().isEmpty() ? null :
                    new Integer(pilot_points.getText().trim()));
        }

        if (bTeam_points.isSelected()) {
            ((Result) objectToProcess).setTeam_points(team_points.getText().trim().isEmpty() ? null :
                    new Integer(team_points.getText().trim()));
        }
    }
    @Override
    protected final String getDatabaseTableName() {
        return "results";
    }
}
