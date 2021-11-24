package manager;

import model.vehicle.Car;
import model.vehicle.Cost;
import model.vehicle.Motorcycle;
import model.vehicle.Vehicle;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;

public class VehicleManager {

    private VehicleBase vehicleBase;

    public VehicleManager() {
        VehicleBase vehicleBase1 = loadVehicleBase();
        if (vehicleBase1 != null){
            this.vehicleBase = vehicleBase1;
        }else{
        this.vehicleBase = new VehicleBase();
        }
    }

    public Long addNewCar(String brand, String model, String vin) {

        Vehicle vehicle = new Car(brand, model, vin);

        Long id = vehicleBase.addVehicle(vehicle);

        return id;
    }

    public Long addNewMotorcycle(String brand, String model, String vin) {

        Vehicle vehicle = new Motorcycle(brand, model, vin);
        Long id = vehicleBase.addVehicle(vehicle);
        return id;
    }

    //metoda znajdujaca pojazd po id w vehicleBase

    public Vehicle findById(Long id) {

        Vehicle vehicleById = vehicleBase.findVehicleById(id);
        if (vehicleById == null) {
            throw new NoSuchElementException("not found this vehicle " + id);
        }
        return vehicleById;

    }

    //metoda addCostToVehicle bedzie dodawala nowy koszt , dwa argumenty id pojazdu i koszt
    public void addCostToVehicle(Long id, Cost cost) {
        Vehicle vehicle = findById(id);
        vehicle.getCosts().add(cost);

    }

    //metoda zwracajaca sume kosztow dla danego pojazdu , pojad po id
    public Double costSum(Long id) {
        Vehicle vehicle = findById(id);
        Double costs = 0.0;
        List<Cost> costList = vehicle.getCosts();
        for (int i = 0; i < costList.size(); i++) {
            costs = costs + costList.get(i).getPrice();
        }
        return costs;

    }

    public void addVehicle(Vehicle vehicle) {
        vehicleBase.addVehicle(vehicle);
    }

    public void updateVehicleCarMilage(Long id, int carMilage){
        Vehicle vehicleById = vehicleBase.findVehicleById(id);
        vehicleById.setCarMilage(carMilage);
        vehicleBase.updateVehicle(vehicleById);
    }

    public void updateVehicle(Vehicle vehicle){
        vehicleBase.updateVehicle(vehicle);
    }

    public void deleteVehicle(Long id){
        vehicleBase.removeVehicle(id);
    }

    public List<Vehicle> getAllVehicles(){

        return vehicleBase.getAllVehicles();
    }

    //find by vin
    public Vehicle findByVin(String vin) {

        Vehicle vehicleByVin = vehicleBase.findVehicleByVin(vin);
        if (vehicleByVin == null) {
            throw new NoSuchElementException("not found this vehicle " + vin);
        }
        return vehicleByVin;

    }

    public void saveVehicleBase(){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("vehiclebase.bin"))) {
            outputStream.writeObject(vehicleBase);

        } catch (IOException e) {
            System.out.println("zapis do bazy nie powiódł się ");
        }
    }

    public VehicleBase loadVehicleBase(){
        VehicleBase loadedVehicleBase = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("vehiclebase.bin"))) {
            loadedVehicleBase = (VehicleBase) inputStream.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedVehicleBase;

    }

}
