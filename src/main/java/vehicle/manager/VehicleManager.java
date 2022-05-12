package vehicle.manager;

import app.database.DBConnection;
import cost.model.Cost;
import inspection.model.Inspection;
import insurance.model.Insurance;
import vehicle.model.Car;
import vehicle.model.Vehicle;

import java.io.*;
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
        long id = vehicleDatabase.addVehicle(vehicle);
        return id;
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
        for (int i = 0; i < costList.size(); i++) {
            costs = costs + costList.get(i).getPrice();
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

        Vehicle vehicleByVin = vehicleDatabase.findVehicleByVin(vin);
        if (vehicleByVin == null) {
            throw new NoSuchElementException("Nie znaleziono pojazdu o podanym numerze vin " + " Podany nr vin to: " + vin);
        }
        return vehicleByVin;
    }

    public void saveVehicleBase() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("vehiclebase.bin"))) {
            outputStream.writeObject(vehicleDatabase);

        } catch (IOException e) {
            System.out.println("Zapis do pliku nie powiódł się ");
        }
    }
}
