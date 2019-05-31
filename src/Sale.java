/**
 * Created by De Jong Yeong on 24/11/2016.
 * @author De Jong Yeong
 * @version 1.0
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class Sale implements Serializable {

    private double cost;
    private Date date;

    public Sale() {
        this(0);
    }

    public Sale(double cost) {
        date = new Date();
        this.cost = cost;
    }

    /**
     * Mutator method to set sale's cost
     * @param cost The total sales price
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Accessor method to get the sales price
     * @return The total sales price
     */
    public double getCost() {
        return cost;
    }

    /**
     * Returns the details of the order
     * @return The details of the order
     */
    @Override
    public String toString() {
        //formatting and parsing dates
        //inherits DateFormat class from java.text package to use the instance format()
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        //format() is an instance method that formats a Date into a date/time String
        return "Date: " + simpleDateFormat.format(date) + "\nCost: " + String.format("%.2f",cost) + "\n";
    }
}
