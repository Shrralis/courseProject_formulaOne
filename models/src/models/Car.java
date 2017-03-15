package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shrralis on 3/13/17.
 */
public class Car extends Owner {
    public String model = null;

    public String chassis = null;

    public String engine_brand = null;

    public String engine_power = null;

    public Integer number_of_cylinders = 0;

    public Car() {}
    @SuppressWarnings("unused")
    public Car(ResultSet from) {
        parse(from);
    }
    @Override
    public Car parse(ResultSet from) {
        super.parse(from);

        try {
            model = from.getString("model");
            chassis = from.getString("chassis");
            engine_brand = from.getString("engine_brand");
            engine_power = from.getString("engine_power");
            number_of_cylinders = from.getInt("number_of_cylinders");
        } catch (SQLException ignored) {}
        return this;
    }
    @Override
    public String toString() {
        return model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getEngine_brand() {
        return engine_brand;
    }

    public void setEngine_brand(String engine_brand) {
        this.engine_brand = engine_brand;
    }

    public String getEngine_power() {
        return engine_power;
    }

    public void setEngine_power(String engine_power) {
        this.engine_power = engine_power;
    }

    public Integer getNumber_of_cylinders() {
        return number_of_cylinders;
    }

    public void setNumber_of_cylinders(Integer number_of_cylinders) {
        this.number_of_cylinders = number_of_cylinders;
    }
}
