package cars_data_form;

import base.DataFormController;
import base.OnMouseClickListener;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by shrralis on 3/13/17.
 */
public class Controller implements DataFormController {
    private OnMouseClickListener okClickListener = null;
    private OnMouseClickListener cancelClickListener = null;
    private Stage primaryStage = null;

    @FXML private TextField model;
    @FXML private TextField chassis;
    @FXML private TextField engineBrand;
    @FXML private TextField enginePower;
    @FXML private TextField numberOfCylinders;
    @FXML private CheckBox bModel;
    @FXML private CheckBox bChassis;
    @FXML private CheckBox bEngineBrand;
    @FXML private CheckBox bEnginePower;
    @FXML private CheckBox bNumberOfCylinders;

    @FXML
    private void initialize() {

    }
    @FXML
    private void onMouseOkClick() {
        if (okClickListener != null) {
            okClickListener.onMouseClick();
        }
    }
    @FXML
    private void onMouseCancelClick() {
        if (cancelClickListener != null) {
            cancelClickListener.onMouseClick();
        }
    }

    @Override
    public void setType(Type type) {
        primaryStage = (Stage) model.getScene().getWindow();
        double height = primaryStage.getHeight();
        double width = primaryStage.getWidth();

        switch (type) {
            case Add:
                width -= 25;
                break;
            case Edit:
                width -= 25;
                break;
            case Search:

                break;
        }
        primaryStage.setMinHeight(height);
        primaryStage.setMaxHeight(height);
        primaryStage.setMinWidth(width);
        primaryStage.setMaxWidth(width);
    }
    @Override
    public void setOnMouseOkClickListener(OnMouseClickListener listener) {
        okClickListener = listener;
    }
    @Override
    public void setOnMouseCancelClickListener(OnMouseClickListener listener) {
        cancelClickListener = listener;
    }
}
