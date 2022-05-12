package vehicle.manager;

import vehicle.model.Vehicle;

import java.util.List;

public interface VehicleDatabase {

    Long addVehicle(Vehicle vehicle);

    void removeVehicle(Long id);

    Vehicle findVehicleById(Long id);

    void updateVehicle(Vehicle vehicle);

    List<Vehicle> getAllVehicles();

    Vehicle findVehicleByVin(String vin);

    boolean isConnected();

}
