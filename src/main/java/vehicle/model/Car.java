package vehicle.model;


import cost.model.Cost;
import inspection.model.Inspection;
import insurance.model.Insurance;

import java.util.List;

public class Car extends Vehicle {


    public Car(String brand, String model, String vin, int engineCapacity) {
        super(VehicleType.CAR,brand, model, vin, engineCapacity);
    }


    public Car(Long id, String brand, String model, String vin, int engineCapacity, int power, int productionYear, int carMilage, List<Insurance> insurances, List<Inspection> inspections, List<Cost> costs) {
        super(id,VehicleType.CAR, brand, model, vin, engineCapacity, power, productionYear, carMilage, insurances, inspections, costs);
    }

    public Car() {
        super(VehicleType.CAR);
    }
}
