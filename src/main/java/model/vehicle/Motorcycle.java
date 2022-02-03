package model.vehicle;


import java.util.List;

public class Motorcycle extends Vehicle {

    public Motorcycle(String brand, String model, String vin, int engineCapacity) {
        super(brand, model, vin, engineCapacity);
    }

    public Motorcycle(Long id, String brand, String model, String vin, int engineCapacity, int power, int productionYear, int carMilage, List<Insurance> insurances, List<Inspection> inspections, List<Cost> costs) {
        super(id, brand, model, vin, engineCapacity, power, productionYear, carMilage, insurances, inspections, costs);
    }
}
