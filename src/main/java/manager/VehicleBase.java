package manager;

import model.vehicle.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleBase implements Serializable {

    private Map<Long, Vehicle> vehicles = new HashMap<>();
    {
        Insurance insurance = new Insurance(LocalDate.of(2021, 9, 9), LocalDate.of(2022, 9, 9), "Hestia", InsuranceType.AC_OC, 12000);
        List<Insurance> insuranceList = new ArrayList<>();
        insuranceList.add(insurance);

        Inspection inspection = new Inspection(LocalDate.of(2021, 7, 7), LocalDate.of(2022, 7, 7), 5000, true);
        List<Inspection> inspectionList = new ArrayList<>();
        inspectionList.add(inspection);
        Cost cost = new Cost("Insurance",800.0,CostType.INSPECTION);
        Cost cost1 = new Cost("Servis",1250.0, CostType.SERVICE);
        List<Cost> costList = new ArrayList<>();
        costList.add(cost);
        costList.add(cost1);

        Car car = new Car(1L, "BMW", "M2", "WBS2U710107G91966", 2976, 411, 2020, 3000, insuranceList, inspectionList,costList);
        Car car1 = new Car(2L, "Audi", "S3", "WBS2U710107G5555", 1999, 300, 2018, 15000, insuranceList, inspectionList,costList);
        Car car2 = new Car(3L, "Mercedes", "C63", "WBS2U710107G5555", 1999, 300, 2018, 15000, insuranceList, inspectionList,costList);
        vehicles.put(1L,car);
        vehicles.put(2L,car1);
        vehicles.put(3L,car2);
    }

    //private Set<Vehicle> vehicles = new HashSet<>();

    private Long lastId = (long)vehicles.size();

     Long addVehicle(Vehicle vehicle) {
        lastId++;
        vehicle.setId(lastId);
        vehicles.put(lastId, copyVehicle(vehicle));
        System.out.println(vehicle + "Vehicle added");
        return lastId;
    }

     void removeVehicle(Long id) {
        vehicles.remove(id);

    }

     Vehicle findVehicleById(Long id) {

        return copyVehicle(vehicles.get(id));
    }

     void updateVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getId(), copyVehicle(vehicle));

    }

     Vehicle copyVehicle(Vehicle vehicle) {

        Vehicle vehicle1 = null;
        if (vehicle instanceof Car) {
            vehicle1 = new Car(vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getVin(), vehicle.getEngineCapacity(), vehicle.getPower(), vehicle.getProductionYear(), vehicle.getCarMilage(), vehicle.getInsurances(), vehicle.getInspections(), vehicle.getCosts());
        }

        if (vehicle instanceof Motorcycle) {
            vehicle1 = new Motorcycle(vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getVin(), vehicle.getEngineCapacity(), vehicle.getPower(), vehicle.getProductionYear(), vehicle.getCarMilage(), vehicle.getInsurances(), vehicle.getInspections(), vehicle.getCosts());

        }
        return vehicle1;
    }

     List<Vehicle> getAllVehicles(){
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.addAll(vehicles.values());
        return vehicleList;
    }

    Vehicle findVehicleByVin(String vin) {
         Vehicle vehicle = null;

        List<Vehicle> vehicleList = getAllVehicles();
        for (Vehicle vehicle1 : vehicleList){
            if (vehicle1.getVin().equalsIgnoreCase(vin)){
                vehicle = vehicle1;
            }

        }
        return copyVehicle(vehicle);
    }






}
