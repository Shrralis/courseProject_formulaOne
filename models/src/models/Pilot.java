package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by shrralis on 3/13/17.
 */
public class Pilot extends Owner {
    public String surname = null;

    public Date birthdate = null;

    public String birthplace = null;

    public String marital_status = null;

    public Double height = null;

    public Double weight = null;

    public Date first_race_date = null;

    public String first_race_place = null;
    @SuppressWarnings("unused")
    public Pilot() {}
    @SuppressWarnings("unused")
    public Pilot(ResultSet from) {
        parse(from);
    }
    @Override
    public Pilot parse(ResultSet from) {
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
    @Override
    public String toString() {
        return name + " " + surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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
}
