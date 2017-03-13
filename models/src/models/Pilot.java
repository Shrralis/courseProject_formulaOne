package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shrralis on 3/13/17.
 */
public class Pilot extends Owner {
    public String surname = null;

    public Date birthdate = null;

    public String birthplace = null;

    public String marital_status = null;

    public double height = 0;

    public double weight = 0;

    public Date first_race_date = null;

    public String first_race_place = null;
    @SuppressWarnings("unused")
    Pilot() {}
    @SuppressWarnings("unused")
    public Pilot(ResultSet from) {
        parse(from);
    }

    @Override
    public Owner parse(ResultSet from) {
        super.parse(from);

        try {
            surname = from.getString("surname");
            birthdate = from.getDate("birthdate");
            birthplace = from.getString("birthplace");
            marital_status = from.getString("marital_status");
            height = from.getDouble("height");
            weight = from.getDouble("weight");
            first_race_date = from.getDate("first_race_date");
            first_race_place = from.getString("first_race_place");
        } catch (SQLException ignored) {}
        return this;
    }
}
