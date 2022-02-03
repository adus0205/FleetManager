package manager;

import model.vehicle.Cost;
import model.vehicle.Inspection;
import model.vehicle.Insurance;
import model.vehicle.Vehicle;

import java.util.List;

public interface VehicleDatabase {

    Long addVehicle(Vehicle vehicle);

    void removeVehicle(Long id);

    Vehicle findVehicleById(Long id);

    void updateVehicle(Vehicle vehicle);

    List<Vehicle> getAllVehicles();

    Vehicle findVehicleByVin(String vin);

    boolean isConnected();

    void addInsurance(Long id, Insurance insurance);

    void addInspection(Long id , Inspection inspection);

    void addCostService(Long id, Cost cost);

}
