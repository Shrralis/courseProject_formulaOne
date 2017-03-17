package sponsors_data_form;

import base.DataFormController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import models.Sponsor;

/**
 * Created by shrralis on 3/13/17.
 */
public class Controller extends DataFormController {
    {
        objectToProcess = new Sponsor();
    }

    @FXML public TextField name;
    @FXML public TextField country;
    @FXML public CheckBox bName;
    @FXML public CheckBox bCountry;

    @Override
    protected final boolean areTheFieldsValidForAdding() {
        return !isAnyTextFieldEmpty();
    }
    @Override
    protected final void objectToProcessBasedOnFields() {
        if (bName.isSelected()) {
            ((Sponsor) objectToProcess).setName(name.getText().trim().isEmpty() ? null : name.getText().trim());
        }

        if (bCountry.isSelected()) {
            ((Sponsor) objectToProcess).setCountry(country.getText().trim().isEmpty() ? null : country.getText().trim());
        }
    }
    @Override
    protected final String getDatabaseTableName() {
        return "sponsors";
    }
}
