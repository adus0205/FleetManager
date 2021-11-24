package aplication;

import manager.VehicleBase;
import manager.VehicleManager;
import model.vehicle.Car;
import model.vehicle.Vehicle;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FleetManager {

    private VehicleManager vehicleManager;

    public FleetManager() {
        this.vehicleManager = new VehicleManager();
    }

    public void start() {
        System.out.println("***Start aplikacji***");
        Scanner scanner = new Scanner(System.in);
        printMenu();

        boolean isRunning = true;
        while (isRunning) {
            int chosenPosition = scanner.nextInt();
            switch (chosenPosition) {
                case 1:
                    printVehicles();
                    break;
                case 5:
                    isRunning = false;
                    saveToDatabase();
                    System.out.println("***Zamykam aplikację***");
                    break;
               case 2:
                   scanner.nextLine();
                   String vin = scanner.nextLine();
                   findByVin(vin);
                   printMenu();

                break;
                case 3:
                    System.out.println("1.Podaj markę" );
                    scanner.nextLine();
                    String brand = scanner.nextLine();

                    System.out.println("2.Podaj model");
                    String model =  scanner.nextLine();
                    System.out.println("3.Podaj vin");
                    String vin2 = scanner.nextLine();
                    addCar(brand,model,vin2);
                    printMenu();
                    break;
                case 4:
                    System.out.println("Podaj id do usuniecia pojazdu :");
                    int i = scanner.nextInt();
                    deleteCar(i);
                    System.out.println("Usunieto pojazd o numerze id :" + i);
                    printMenu();
                    break;

                default:
                    break;
            }
        }

    }

    private void printMenu() {
        System.out.println("Witamy w programie Fleet Manager");
        System.out.println("Co chcesz zrobic ? ");
        System.out.println("1.Wypisz zasoby floty");
        System.out.println("2.Znajdź auto po nr vin");
        System.out.println("3.Dodaj nowe auto");
        System.out.println("4. Usuń auto ");
        System.out.println("5. Exit ");
    }

    public void printVehicles() {
        List<Vehicle> allVehicles = vehicleManager.getAllVehicles();

       // allVehicles.sort(Comparator.comparing(Vehicle::getId));
        if (allVehicles.isEmpty()) {
            System.out.println("W bazie nie ma aut");

        } else {
            System.out.println("Twoja lista aut:");

            for (Vehicle vehicle : allVehicles) {
                System.out.println(vehicle.getId() + ". " + vehicle.getVin() + ", " + vehicle.getBrand() + ", " + vehicle.getModel());

            }
        }
    }

    // sprawdzenie if czy jest pojazd o takim nr vin , jesli nie ma to komunikat "nie ma pojazdu o takim vinie" jesli jest wyswietlamy
    public void findByVin(String vin){

        System.out.println(vehicleManager.findByVin(vin));

    }
    //nazwa metody addVehicle czy addCar ? Menu jest dla car nie vehicle , dla motorcycle bedzie nowe menu ?
    public void addCar(String brand, String model, String vin){

        Long id = vehicleManager.addNewCar(brand, model, vin);
        System.out.println("Dodano auto i wygenerowano Id = " + id );

    }

    public void deleteCar(int id){
        vehicleManager.deleteVehicle((long)id);
    }

    public void saveToDatabase(){
        vehicleManager.saveVehicleBase();
    }
}
