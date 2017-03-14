package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shrralis on 3/13/17.
 */
public class SponsorToTeam extends Owner {
    public Team team = null;
    public Sponsor sponsor = null;

    public SponsorToTeam() {}
    @Override
    public SponsorToTeam parse(ResultSet from, Connection connection) {
        super.parse(from);

        try {
            team = ParseUtils.parseViaReflection(new Team(), get("SELECT * FROM `teams` WHERE `id` = " +
                    from.getInt("store"), connection));
            sponsor = ParseUtils.parseViaReflection(new Sponsor(), get("SELECT * FROM `sponsors` WHERE `id` = " +
                    from.getInt("sponsor"), connection));
        } catch (SQLException ignored) {}
        return this;
    }

    public ResultSet get(String sql, Connection connection) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }
}
