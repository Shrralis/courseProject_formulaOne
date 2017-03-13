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

    public int number_of_cylinders = 0;
    @SuppressWarnings("unused")
    Car() {}
    @SuppressWarnings("unused")
    public Car(ResultSet from) { parse(from); }
    @Override
    public Owner parse(ResultSet from) {
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
}
