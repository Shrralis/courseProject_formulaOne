package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by shrralis on 3/13/17.
 */
public class Team extends Owner {
    public Date birthdate = null;

    public Date first_race_date = null;

    public String first_race_place = null;

    public Car car = null;
    @SuppressWarnings("unused")
    Team() {}
    @SuppressWarnings("unused")
    public Team(ResultSet from) { parse(from); }

    @Override
    public Owner parse(ResultSet from, Connection connection) {
        super.parse(from);

        try {
            birthdate = from.getDate("birthdate");
            first_race_date = from.getDate("first_race_date");
            first_race_place = from.getString("first_race_place");
            car = ParseUtils.parseViaReflection(new Car(), get("SELECT * FROM `cars` WHERE `id` = " +
                    from.getInt("car"), connection));
        } catch (SQLException ignored) {}
        return this;
    }

    public ResultSet get(String sql, Connection connection) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }
}
