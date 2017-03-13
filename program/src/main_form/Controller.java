package main_form;

import base.DataFormController;
import base.OnMouseClickListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.*;

import java.io.IOException;

public class Controller {
    @FXML private TabPane tabs;
    @FXML private TableView<Result> tableResults;
    @FXML private TableColumn<Result, String> columnRaceTableResults;
    @FXML private TableColumn<Result, String> columnTeamTableResults;
    @FXML private TableColumn<Result, String> columnPilotTableResults;
    @FXML private TableView<Race> tableRaces;
    @FXML private TableView<Team> tableTeams;
    @FXML TableColumn<Team, String> columnBirthDateTableTeams;
    @FXML TableColumn<Team, String> columnFirstRaceDateTableTeams;
    @FXML TableColumn<Team, String> columnCarTableTeams;
    @FXML private TableView<Pilot> tablePilots;
    @FXML TableColumn<Pilot, String> columnBirthDateTablePilots;
    @FXML TableColumn<Pilot, String> columnFirstRaceDateTablePilots;
    @FXML private TableView<Sponsor> tableSponsors;
    @FXML private TableView<Car> tableCars;

    @FXML
    public void initialize() {}
    @FXML
    protected void onButtonAddClick(MouseEvent event) throws IOException {
        openDataForm(DataFormController.Type.Add);
    }
    @FXML
    protected void onButtonEditClick(MouseEvent event) throws IOException {
        openDataForm(DataFormController.Type.Edit);
    }
    @FXML
    protected void onButtonDeleteClick(MouseEvent event) {}
    @FXML
    protected void onButtonSearchClick(MouseEvent event) throws IOException {
        openDataForm(DataFormController.Type.Search);
    }

    private void openDataForm(DataFormController.Type type) throws IOException {
        String sSelectedTab = tabs.getSelectionModel().getSelectedItem().getText();
        FXMLLoader loader;

        if (sSelectedTab.equalsIgnoreCase("результати")) {
            loader = new FXMLLoader(getClass().getResource("/results_data_form/data.fxml"));
        } else if (tabs.getSelectionModel().getSelectedItem().getText().equalsIgnoreCase("гонки")) {
            loader = new FXMLLoader(getClass().getResource("/races_data_form/data.fxml"));
        } else if (tabs.getSelectionModel().getSelectedItem().getText().equalsIgnoreCase("команди")) {
            loader = new FXMLLoader(getClass().getResource("/teams_data_form/data.fxml"));
        } else if (tabs.getSelectionModel().getSelectedItem().getText().equalsIgnoreCase("пілоти")) {
            loader = new FXMLLoader(getClass().getResource("/pilots_data_form/data.fxml"));
        } else if (tabs.getSelectionModel().getSelectedItem().getText().equalsIgnoreCase("спонсори")) {
            loader = new FXMLLoader(getClass().getResource("/sponsors_data_form/data.fxml"));
        } else {
            loader = new FXMLLoader(getClass().getResource("/cars_data_form/data.fxml"));
        }
        loader.load();

        DataFormController controller = loader.getController();

        controller.setType(type);
        setDataFormClickListeners(controller, type);

        Stage dataFormStage = new Stage();

        dataFormStage.setScene(new Scene(loader.getRoot()));
        dataFormStage.show();
    }

    private void setDataFormClickListeners(DataFormController controller, DataFormController.Type type) {
        OnMouseClickListener okListener = null;
        OnMouseClickListener cancelListener = null;

        switch (type) {
            case Add:
                okListener = () -> {
                    // TODO: something;
                };
                cancelListener = () -> {
                    // TODO: something
                };
                break;
            case Edit:
                okListener = () -> {
                    // TODO: something;
                };
                cancelListener = () -> {
                    // TODO: something
                };
                break;
            case Search:
                okListener = () -> {
                    // TODO: something;
                };
                cancelListener = () -> {
                    // TODO: something
                };
                break;
        }
        controller.setOnMouseOkClickListener(okListener);
        controller.setOnMouseCancelClickListener(cancelListener);
    }
}
