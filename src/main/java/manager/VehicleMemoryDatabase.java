package manager;

import model.vehicle.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleMemoryDatabase implements Serializable, VehicleDatabase {

    private Map<Long, Vehicle> vehicles = new HashMap<>();

    private Long lastId = (long) vehicles.size();

    @Override
    public Long addVehicle(Vehicle vehicle) {
        lastId++;
        vehicle.setId(lastId);
        vehicles.put(lastId, copyVehicle(vehicle));
        System.out.println(vehicle + "Vehicle added");
        return lastId;
    }

    @Override
    public void removeVehicle(Long id) {
        vehicles.remove(id);
    }

    @Override
    public Vehicle findVehicleById(Long id) {

        return copyVehicle(vehicles.get(id));
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
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

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.addAll(vehicles.values());
        return vehicleList;
    }

    @Override
    public Vehicle findVehicleByVin(String vin) {
        Vehicle vehicle = null;

        List<Vehicle> vehicleList = getAllVehicles();
        for (Vehicle vehicle1 : vehicleList) {
            if (vehicle1.getVin().equalsIgnoreCase(vin)) {
                vehicle = vehicle1;
            }

        }
        return copyVehicle(vehicle);
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void addInsurance(Long id, Insurance insurance) {
        throw new UnsupportedOperationException("Metoda niezaimplementowana");
    }

    @Override
    public void addInspection(Long id, Inspection inspection) {
        throw new UnsupportedOperationException("Metoda niezaimplementowana");
    }

    @Override
    public void addCostService(Long id, Cost cost) {
        throw new UnsupportedOperationException("Metoda niezaimplementowana");
    }


}
