package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shrralis on 3/13/17.
 */
public class SponsorToTeam extends Owner {
    public Team team = null;
    public int sponsor = 0;

    public SponsorToTeam() {}

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setSponsor(int sponsor) {
        this.sponsor = sponsor;
    }
    @Override
    public SponsorToTeam parse(ResultSet from, Connection connection) {
        super.parse(from);

        try {
            team = ParseUtils.parseViaReflection(new Team(), get("SELECT * FROM `teams` WHERE `id` = " +
                    from.getInt("store"), connection));
            sponsor = from.getInt("sponsor");
        } catch (SQLException ignored) {}
        return this;
    }

    public ResultSet get(String sql, Connection connection) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }
}
