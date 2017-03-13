package models;

import java.sql.ResultSet;

/**
 * Created by shrralis on 3/13/17.
 */
public class Sponsor extends Owner {
    public String country = null;
    @SuppressWarnings("unused")
    Sponsor() {}
    @SuppressWarnings("unused")
    public Sponsor(ResultSet from) {
        parse(from);
    }
    @Override
    public Sponsor parse(ResultSet from) {
        super.parse(from);
        return this;
    }
}
