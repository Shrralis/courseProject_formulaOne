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
    public Team() {}
    @SuppressWarnings("unused")
    public Team(ResultSet from) { parse(from); }

    @Override
    public Team parse(ResultSet from, Connection connection) {
        super.parse(from);

        try {
            birthdate = from.getDate("birthdate");
            first_race_date = from.getDate("first_race_date");
            first_race_place = from.getString("first_race_place");
            car = ParseUtils.parseViaReflection(new Car(), get("SELECT * FROM `cars` WHERE `id` = " +
                    from.getInt("car") + ";", connection));
        } catch (SQLException ignored) {}
        return this;
    }

    public ResultSet get(String sql, Connection connection) throws SQLException {
        return connection.createStatement().executeQuery(sql);
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getFirst_race_date() {
        return first_race_date;
    }

    public void setFirst_race_date(Date first_race_date) {
        this.first_race_date = first_race_date;
    }

    public String getFirst_race_place() {
        return first_race_place;
    }

    public void setFirst_race_place(String first_race_place) {
        this.first_race_place = first_race_place;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
