package vehicle.manager;

import app.database.DBConnection;
import cost.model.Cost;
import vehicle.model.Car;
import vehicle.model.Motorcycle;
import vehicle.model.Vehicle;

import java.util.List;
import java.util.NoSuchElementException;

public class VehicleManager {

    private VehicleDatabase vehicleDatabase;

    public VehicleManager(DBConnection dbConnection) {
        VehicleDatabase vehicleRealDatabase = new VehicleRealDatabase(dbConnection);
        if (vehicleRealDatabase.isConnected()) {
            this.vehicleDatabase = vehicleRealDatabase;
        }
    }

    public Long addNewCar(String brand, String model, String vin, int engineCapacity) {
        Vehicle vehicle = new Car(brand, model, vin, engineCapacity);
        return vehicleDatabase.addVehicle(vehicle);
    }

    public Vehicle findById(Long id) {

        Vehicle vehicleById = vehicleDatabase.findVehicleById(id);
        if (vehicleById == null) {
            throw new NoSuchElementException("not found this vehicle " + id);
        }
        return vehicleById;
    }

    public Double costSum(Long id) {
        Vehicle vehicle = findById(id);
        double costs = 0.0;
        List<Cost> costList = vehicle.getCosts();
        for (Cost cost : costList) {
            costs = costs + cost.getPrice();
        }
        return costs;
    }

    public void deleteVehicle(Long id) {
        vehicleDatabase.removeVehicle(id);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleDatabase.getAllVehicles();
    }

    public Vehicle findByVin(String vin) {
        return vehicleDatabase.findVehicleByVin(vin);
    }

    public Long addNewMotorcycle(String brand, String model, String vin, int engineCapacity) {
        Vehicle vehicle = new Motorcycle(brand, model, vin, engineCapacity);
        return vehicleDatabase.addVehicle(vehicle);
    }
}

