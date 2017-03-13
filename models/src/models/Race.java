package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shrralis on 3/13/17.
 */
public class Race extends Owner {
    public int year = 0;

    public String country = null;

    public String city = null;

    public String track_name = null;

    public int laps = 0;

    public double lap_length = 0;
    @SuppressWarnings("unused")
    Race() {}
    @SuppressWarnings("unused")
    public Race(ResultSet from) {
        parse(from);
    }
    @Override
    public Owner parse(ResultSet from) {
        super.parse(from);

        try {
            year = from.getInt("year");
            country = from.getString("country");
            city = from.getString("city");
            track_name = from.getString("track_name");
            laps = from.getInt("laps");
            lap_length = from.getDouble("lap_length");
        } catch (SQLException ignored) {}
        return this;
    }
}
