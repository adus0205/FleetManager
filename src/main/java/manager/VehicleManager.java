package manager;

import model.vehicle.*;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;

public class VehicleManager {

    private VehicleDatabase vehicleDatabase;

    public VehicleManager() {
        VehicleDatabase vehicleRealDatabase = new VehicleRealDatabase();
        if (vehicleRealDatabase.isConnected()) {
            this.vehicleDatabase = vehicleRealDatabase;
        } else {
            VehicleMemoryDatabase vehicleMemoryDatabase1 = loadVehicleBase();
            if (vehicleMemoryDatabase1 != null) {
                this.vehicleDatabase = vehicleMemoryDatabase1;
            } else {
                this.vehicleDatabase = new VehicleMemoryDatabase();
            }
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

    //metoda zwracajaca sume kosztow dla danego pojazdu , pojad po id
    public Double costSum(Long id) {
        Vehicle vehicle = findById(id);
        double costs = 0.0;
        List<Cost> costList = vehicle.getCosts();
        for (int i = 0; i < costList.size(); i++) {
            costs = costs + costList.get(i).getPrice();
        }
        return costs;

    }

    public void addInsurance(Long id, Insurance insurance) {
        vehicleDatabase.addInsurance(id, insurance);
    }

    public void addInspection(Long id, Inspection inspection) {
        vehicleDatabase.addInspection(id, inspection);
    }

    public void addCost(Long id, Cost cost) {
        vehicleDatabase.addCostService(id, cost);
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
            throw new NoSuchElementException("not found this vehicle " + vin);
        }
        return vehicleByVin;

    }

    public void saveVehicleBase() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("vehiclebase.bin"))) {
            outputStream.writeObject(vehicleDatabase);

        } catch (IOException e) {
            System.out.println("zapis do pliku nie powiódł się ");
        }
    }

    public VehicleMemoryDatabase loadVehicleBase() {
        VehicleMemoryDatabase loadedVehicleMemoryDatabase = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("vehiclebase.bin"))) {
            loadedVehicleMemoryDatabase = (VehicleMemoryDatabase) inputStream.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedVehicleMemoryDatabase;

    }

}
