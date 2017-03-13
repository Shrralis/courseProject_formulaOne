package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shrralis on 3/13/17.
 */
public class Result extends Owner {
    public Race race = null;

    public Team team = null;

    public Pilot pilot = null;

    public String race_time = null;

    public int pilot_points = 0;

    public int team_points = 0;
    @SuppressWarnings("unused")
    public Result() {}

    @Override
    public Owner parse(ResultSet from, Connection connection) {
        super.parse(from);

        try {
            race = ParseUtils.parseViaReflection(new Race(), get("SELECT * FROM `races` WHERE `id` = " +
                    from.getInt("race"), connection));
            team = ParseUtils.parseViaReflection(new Team(), get("SELECT * FROM `teams` WHERE `id` = " +
                    from.getInt("team"), connection));
            pilot = ParseUtils.parseViaReflection(new Pilot(), get("SELECT * FROM `pilots` WHERE `id` = " +
                    from.getInt("pilot"), connection));
            race_time = from.getString("race_time");
            pilot_points = from.getInt("pilot_points");
            team_points = from.getInt("team_points");
        } catch (SQLException ignored) {}
        return this;
    }

    public ResultSet get(String sql, Connection connection) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public String getRace_time() {
        return race_time;
    }

    public void setRace_time(String race_time) {
        this.race_time = race_time;
    }

    public int getPilot_points() {
        return pilot_points;
    }

    public void setPilot_points(int pilot_points) {
        this.pilot_points = pilot_points;
    }

    public int getTeam_points() {
        return team_points;
    }

    public void setTeam_points(int team_points) {
        this.team_points = team_points;
    }
}
