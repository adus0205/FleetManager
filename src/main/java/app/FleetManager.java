package app;

import app.database.DBConnection;

import cost.manager.CostManager;
import inspection.manager.InspectionManager;
import insurance.manager.InsuranceManager;

import vehicle.manager.VehicleManager;
import vehicle.model.Vehicle;
import vehicle.model.VehicleType;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class FleetManager {

    private VehicleManager vehicleManager;
    private InspectionManager inspectionManager;
    private InsuranceManager insuranceManager;
    private CostManager costManager;


    public FleetManager() {
        Scanner scanner = new Scanner(System.in);
        out.println("Przed startem aplikacji podaj ścieżkę url do bazy danych");
        String baza = scanner.nextLine();
        out.println("Podaj login");
        String login = scanner.nextLine();
        out.println("Podaj hasło");
        String haslo = scanner.nextLine();
        DBConnection dbConnection = new DBConnection(baza, login, haslo);
        this.vehicleManager = new VehicleManager(dbConnection);
        this.inspectionManager = new InspectionManager(dbConnection);
        this.insuranceManager = new InsuranceManager(dbConnection);
        this.costManager = new CostManager(dbConnection);
    }

    private void printMenu() {
        out.println("Witamy w programie Fleet Manager");
        out.println("Co chcesz zrobic ? ");
        out.println("1.Wypisz zasoby floty");
        out.println("2.Znajdź pojazd po nr vin");
        out.println("3.Dodaj nowy pojazd");
        out.println("4. Usuń pojazd ");
        out.println("5. Modyfikuj pojazd");
        out.println("0. Exit ");
    }

    public void start() {
        out.println("***Start aplikacji***");
        Scanner scanner = new Scanner(System.in);
        printMenu();

        boolean isRunning = true;
        while (isRunning) {
            int chosenPosition = scanner.nextInt();
            switch (chosenPosition) {
                case 1:
                    printVehicles();
                    break;
                case 0:
                    isRunning = false;
                    out.println("***Zamykam aplikację***");
                    break;
                case 2:
                    out.println("Podaj nr VIN pojazdu :");
                    scanner.nextLine();
                    String vin = scanner.nextLine();
                    findByVin(vin);
                    printMenu();
                    break;
                case 3:
                    VehicleType vehicleType = chosenVehicleType(scanner);

                    out.println("2.Podaj markę");
                    scanner.nextLine();
                    String brand = scanner.nextLine();
                    out.println("3.Podaj model");
                    String model = scanner.nextLine();
                    out.println("4.Podaj vin");
                    String vin2 = scanner.nextLine();
                    out.println("5.Podaj pojemność silnika");
                    int enginecapacity2 = scanner.nextInt();
                    addVehicle(vehicleType,brand, model, vin2, enginecapacity2);
                    printMenu();
                    break;
                case 4:
                    out.println("Podaj id pojazdu do usuniecia  :");
                    int i = scanner.nextInt();
                    deleteCar(i);
                    out.println("Usunieto pojazd o numerze id :" + i);
                    printMenu();
                    break;
                case 5:
                    out.println("Podaj Id do modyfikacji pojazdu");
                    int i1 = scanner.nextInt();
                    modifyVehicle(scanner, i1);
                    printMenu();
                    break;
                default:
                    break;
            }
        }

    }

    private VehicleType chosenVehicleType(Scanner scanner){
        out.println("Podaj typ pojazdu, który chcesz dodać : 1. Auto  2.Motocykl");
        int vehicletype = scanner.nextInt();
        return VehicleType.mapInToVehicleType(vehicletype);
    }

    private void printVehicles() {
        List<Vehicle> allVehicles = vehicleManager.getAllVehicles();

        if (allVehicles.isEmpty()) {
            out.println("W bazie nie ma aut");

        } else {
            out.println("Twoja lista aut:");

            for (Vehicle vehicle : allVehicles) {
                out.println(vehicle.getId() + ". "+ vehicle.getType()+ " " + vehicle.getVin() + ", " + vehicle.getBrand() + ", " + vehicle.getModel() + ", " + vehicle.getEngineCapacity());
            }
        }
    }

    private void findByVin(String vin) {
        if(vehicleManager.findByVin(vin) == null){
            out.println("Nie ma pojazdu o podanym nr vin." + " " + "Podany nr vin to : " + vin);
        } else {
            out.println(vehicleManager.findByVin(vin));
        }
    }

    private void addVehicle(VehicleType type,String brand, String model, String vin, int engineCapacity) {
        switch(type){
            case CAR:
                addCar(brand, model, vin, engineCapacity);
            break;
            case MOTORCYCLE:
                addMotorcycle(brand, model, vin, engineCapacity);
            default:
                break;
        }

    }

    private void addCar(String brand, String model, String vin, int engineCapacity){
        Long id = vehicleManager.addNewCar(brand, model, vin, engineCapacity);
        out.println("Dodano auto i wygenerowano Id pojazdu = " + id);
    }

    private void addMotorcycle(String brand, String model, String vin, int engineCapacity){
        Long id = vehicleManager.addNewMotorcycle(brand, model, vin, engineCapacity);
        out.println("Dodano motocykl i wygenerowano Id pojazdu = " + id);
    }

    private void deleteCar(int id) {
        vehicleManager.deleteVehicle((long) id);
    }

    private void modifyVehicle(Scanner scanner, int id) {
        Vehicle byId = vehicleManager.findById((long) id);
        printVehicleModyficationMenu(id);

        int choosenPosition = scanner.nextInt();
        switch (choosenPosition) {

            case 1:
                InsuranceMenu insuranceMenu = new InsuranceMenu(byId,scanner,insuranceManager);
                insuranceMenu.handleInsurance();
                break;
            case 0:
                break;
            case 2:
                InspectionMenu inspectionMenu = new InspectionMenu(byId,scanner,inspectionManager);
                inspectionMenu.handleInspection();
                break;
            case 3:
                CostMenu costMenu = new CostMenu(byId,scanner,costManager);
                costMenu.handle();
                break;
            default:
                break;
        }
    }

    private void printVehicleModyficationMenu(int id) {
        out.println("Modyfikujesz pojazd o id " + id);
        out.println("Co chcesz zrobic? ");
        out.println("1. Ubezpieczenie pojazdu");
        out.println("2. Przegląd pojazdu");
        out.println("3  Serwis pojazdu");
        out.println("0. Powrót ");
    }

    private void menuSeparating(){
        out.println("---------------------------------");
    }

}
