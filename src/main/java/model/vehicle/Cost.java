package model.vehicle;

import java.io.Serializable;

public class Cost implements Serializable {

    private Long id;
    private CostType type;
    private String name;
    private double price;


    public Cost(Long id, CostType type,String name, double price) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;

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
