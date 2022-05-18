package app;

import app.database.DBConnection;
import app.utils.ScannerUtils;
import cost.manager.CostManager;
import cost.model.Cost;
import cost.model.CostType;
import inspection.manager.InspectionManager;
import inspection.model.Inspection;
import insurance.manager.InsuranceManager;
import insurance.model.Insurance;
import insurance.model.InsuranceType;
import vehicle.manager.VehicleManager;
import vehicle.model.Vehicle;
import vehicle.model.VehicleType;

import java.time.LocalDate;
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
                    saveToDatabase();
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
                    out.println("Podaj typ pojazdu, który chcesz dodać : 1. Auto  2.Motocykl");
                    int vehicletype = scanner.nextInt();
                    VehicleType vehicleType = VehicleType.mapInToVehicleType(vehicletype);
                    // dodane
                    out.println("2.Podaj markę");
                    scanner.nextLine();
                    String brand = scanner.nextLine();
                    out.println("3.Podaj model");
                    String model = scanner.nextLine();
                    out.println("4.Podaj vin");
                    String vin2 = scanner.nextLine();
                    out.println("5.Podaj pojemność silnika");
                    int enginecapacity2 = scanner.nextInt();
                    addCar(brand, model, vin2, enginecapacity2);
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

    private void chosenVehicleType(Scanner scanner, Vehicle vehicle){
        out.println("Podaj typ pojazdu, który chcesz dodać : 1. Auto  2.Motocykl");
        int vehicletype = scanner.nextInt();
        VehicleType vehicleType = VehicleType.mapInToVehicleType(vehicletype);
    } // dodane menu wyboru typu



    private void printVehicles() {
        List<Vehicle> allVehicles = vehicleManager.getAllVehicles();

        if (allVehicles.isEmpty()) {
            out.println("W bazie nie ma aut");

        } else {
            out.println("Twoja lista aut:");

            for (Vehicle vehicle : allVehicles) {
                out.println(vehicle.getId() + ". " + vehicle.getVin() + ", " + vehicle.getBrand() + ", " + vehicle.getModel() + ", " + vehicle.getEngineCapacity());

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

    private void addCar(String brand, String model, String vin, int engineCapacity) {

        Long id = vehicleManager.addNewCar(brand, model, vin, engineCapacity);
        out.println("Dodano auto i wygenerowano Id pojazdu = " + id);

    }

    private void addMotorcycle(String brand, String model, String vin, int engineCapacity){
        Long id = vehicleManager.addNewCar(brand, model, vin, engineCapacity);
        out.println("Dodano motocykl i wygenerowano Id pojazdu = " + id);
    }                                                                      // dodana metoda

    private void deleteCar(int id) {
        vehicleManager.deleteVehicle((long) id);
    }

    private void saveToDatabase() {
        vehicleManager.saveVehicleBase();
    }

    private void modifyVehicle(Scanner scanner, int id) {
        Vehicle byId = vehicleManager.findById((long) id);
        printVehicleModyficationMenu(id);

        int choosenPosition = scanner.nextInt();
        switch (choosenPosition) {

            case 1:
                addInsuranceToCar(scanner, byId);
                break;
            case 0:
                break;
            case 2:
                addInspectionToCar(scanner, byId);
                break;
            case 3:
                addServiceCostToVehicle(scanner, byId);
                break;
            default:
                break;
        }
    }

    private void printVehicleModyficationMenu(int id) {
        out.println("Modyfikujesz pojazd o id " + id);
        out.println("Co chcesz zrobic? ");
        out.println("1. Dodaj ubezpieczenie");
        out.println("2. Dodaj przegląd pojazdu");
        out.println("3. Dodaj koszty serwisu");
        out.println("0. Powrót ");
    }

    private void addInsuranceToCar(Scanner scanner, Vehicle vehicle) {
        out.println("Podaj datę rozpoczęcia ubezpieczenia ");
        scanner.nextLine();
        LocalDate parsedStartDate = ScannerUtils.readDate(scanner);
        out.println("Podaj datę zakończenia");
        LocalDate parsedEndDate = ScannerUtils.readDate(scanner);
        out.println("Podaj nazwę firmy ubezpieczeniowej ");
        String insuranceCompany = scanner.nextLine();
        out.println("Podaj typ ubezpieczenia : 1. Ac, 2.Oc, 3.AC-OC");
        int typeOfInsurance = scanner.nextInt();
        InsuranceType insuranceType = InsuranceType.mapIntToInsuranceType(typeOfInsurance);
        out.println("Cena ubezpieczenia ");
        int insurancePrice = scanner.nextInt();
        out.println("Dodano ubezpieczenie do pojazdu");
        menuSeparating();
        Insurance insurance = new Insurance(null, parsedStartDate, parsedEndDate, insuranceCompany, insuranceType, insurancePrice);
        insuranceManager.addInsurance(vehicle.getId(), insurance);
    }

    private void addServiceCostToVehicle(Scanner scanner, Vehicle vehicle) {
        out.println("Podaj typ kosztu: 1.Serwis/naprawy 2.Przegląd");
        int serviceType = scanner.nextInt();
        CostType costType = CostType.mapIntToCostType(serviceType);
        out.println("Podaj nazwę kosztu serwisu, który chcesz dodać np.opony : ");
        scanner.nextLine();
        String nameCost = scanner.nextLine();
        out.println("Podaj cenę podanego kosztu");
        double costPrice = scanner.nextDouble();
        out.println("Dodano nowy koszt serwisowania pojazdu");
        menuSeparating();
        Cost cost = new Cost(null, costType, nameCost, costPrice);
        costManager.addServiceCost(vehicle.getId(), cost);
    }

    private void addInspectionToCar(Scanner scanner, Vehicle vehicle) {
        out.println("Podaj datę przeglądu");
        scanner.nextLine();
        LocalDate parseStartDate = ScannerUtils.readDate(scanner);
        out.println("Podaj datę końca obowiązywania przeglądu pojazdu");
        LocalDate parseEndDate = ScannerUtils.readDate(scanner);
        out.println("Podaj przebieg pojazdu");
        int odometerValue = scanner.nextInt();
        out.println("Podaj wynik badania technicznego");
        boolean inspectionResult = scanner.nextBoolean();
        out.println("Inspekcja pojazdu została zaaktualizowana");
        menuSeparating();
        Inspection inspection = new Inspection(null, parseStartDate, parseEndDate, odometerValue, inspectionResult);
        inspectionManager.addInspection(vehicle.getId(), inspection);
    }

    private void menuSeparating(){
        out.println("---------------------------------");
    }

}
