package model.vehicle;


import java.util.List;

public class Car extends Vehicle {


    public Car(String brand, String model, String vin) {
        super(brand, model, vin);
    }


    public Car(Long id, String brand, String model, String vin, int engineCapacity, int power, int productionYear, int carMilage, List<Insurance> insurances, List<Inspection> inspections, List<Cost> costs) {
        super(id, brand, model, vin, engineCapacity, power, productionYear, carMilage, insurances, inspections, costs);
    }



}