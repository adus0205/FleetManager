package model.vehicle;

import java.io.Serializable;

public class Cost implements Serializable {

    private String name;
    private double price;
    private CostType type;

    public Cost(String name, double price, CostType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public CostType getType() {
        return type;
    }
}
