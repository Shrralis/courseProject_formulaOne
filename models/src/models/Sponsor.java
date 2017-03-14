package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shrralis on 3/13/17.
 */
public class Sponsor extends Owner {
    public String country = null;
    @SuppressWarnings("unused")
    public Sponsor() {}
    @SuppressWarnings("unused")
    public Sponsor(ResultSet from) {
        parse(from);
    }
    @Override
    public Sponsor parse(ResultSet from) {
        super.parse(from);

        try {
            country = from.getString("country");
        } catch (SQLException ignored) {}
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
