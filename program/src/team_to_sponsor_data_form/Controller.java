package team_to_sponsor_data_form;

import base.DataFormComboBoxController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import models.Sponsor;
import models.SponsorToTeam;
import models.Team;

/**
 * Created by shrralis on 3/14/17.
 */
public class Controller extends DataFormComboBoxController {
    {
        objectToProcess = new SponsorToTeam();
    }

    @FXML public ComboBox<Sponsor> sponsor;

    @Override
    protected boolean areTheFieldsValidForAdding() {
        return !isAnyComboBoxEmpty();
    }
    @Override
    protected void objectToProcessBasedOnFields() {
        ((SponsorToTeam) objectToProcess).setSponsor(sponsor.getValue());
    }
    @Override
    protected String getDatabaseTableName() {
        return "teams_has_sponsors";
    }

    public final void setSponsor(Sponsor sponsor) {
        ((SponsorToTeam) objectToProcess).setSponsor(sponsor);
    }

    public final void setTeam(Team team) {
        ((SponsorToTeam) objectToProcess).setTeam(team);
    }
}
