package sponsors_data_form;

import base.DataFormController;
import base.OnMouseClickListener;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by shrralis on 3/13/17.
 */
public class Controller implements DataFormController {
    private OnMouseClickListener okClickListener = null;
    private OnMouseClickListener cancelClickListener = null;

    @FXML private TextField name;
    @FXML private TextField country;

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
