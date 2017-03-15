package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shrralis on 3/13/17.
 */
public class Race extends Owner {
    public Integer year = null;

    public String country = null;

    public String city = null;

    public String track_name = null;

    public Integer laps = null;

    public Double lap_length = null;

    public Race() {}
    @SuppressWarnings("unused")
    public Race(ResultSet from) {
        parse(from);
    }
    @Override
    public Race parse(ResultSet from) {
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
    @Override
    public String toString() {
        return city + ", " + country + " (" + year + ")";
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTrack_name() {
        return track_name;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public Double getLap_length() {
        return lap_length;
    }

    public void setLap_length(Double lap_length) {
        this.lap_length = lap_length;
    }
}
