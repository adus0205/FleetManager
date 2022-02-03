package aplication;

import manager.VehicleManager;
import model.vehicle.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FleetManager {

    private VehicleManager vehicleManager;

    public FleetManager() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Przed startem aplikacji podaj url do bazy danych");
        String baza = scanner.nextLine();
        System.out.println("Podaj login");
        String login = scanner.nextLine();
        System.out.println("Podaj hasło");
        String haslo = scanner.nextLine();
        this.vehicleManager = new VehicleManager(baza, login, haslo);

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

                case 0:
                    isRunning = false;
                    saveToDatabase();
                    System.out.println("***Zamykam aplikację***");
                    break;

                case 2:
                    System.out.println("Podaj nr VIN pojazdu :");
                    scanner.nextLine();
                    String vin = scanner.nextLine();
                    findByVin(vin);
                    printMenu();
                    break;

                case 3:
                    System.out.println("1.Podaj markę");
                    scanner.nextLine();
                    String brand = scanner.nextLine();
                    System.out.println("2.Podaj model");
                    String model = scanner.nextLine();
                    System.out.println("3.Podaj vin");
                    String vin2 = scanner.nextLine();
                    System.out.println("4.Podaj pojemność silnika");
                    int enginecapacity2 = scanner.nextInt();
                    addCar(brand, model, vin2, enginecapacity2);
                    printMenu();
                    break;

                case 4:
                    System.out.println("Podaj id do usuniecia pojazdu :");
                    int i = scanner.nextInt();
                    deleteCar(i);
                    System.out.println("Usunieto pojazd o numerze id :" + i);
                    printMenu();
                    break;

                case 5:
                    System.out.println("Podaj Id do modyfikacji pojazdu");
                    int i1 = scanner.nextInt();
                    modifyVehicle(scanner, i1);
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
        System.out.println("5. Modyfikuj pojazd");
        System.out.println("0. Exit ");
    }

    public void printVehicles() {
        List<Vehicle> allVehicles = vehicleManager.getAllVehicles();

        // allVehicles.sort(Comparator.comparing(Vehicle::getId));
        if (allVehicles.isEmpty()) {
            System.out.println("W bazie nie ma aut");

        } else {
            System.out.println("Twoja lista aut:");

            for (Vehicle vehicle : allVehicles) {
                System.out.println(vehicle.getId() + ". " + vehicle.getVin() + ", " + vehicle.getBrand() + ", " + vehicle.getModel() + ", " + vehicle.getEngineCapacity());

            }
        }
    }

    // sprawdzenie if czy jest pojazd o takim nr vin , jesli nie ma to komunikat "nie ma pojazdu o takim vinie" jesli jest wyswietlamy
    public void findByVin(String vin) {
        System.out.println(vehicleManager.findByVin(vin));

    }


    public void addCar(String brand, String model, String vin, int engineCapacity) {

        Long id = vehicleManager.addNewCar(brand, model, vin, engineCapacity);
        System.out.println("Dodano auto i wygenerowano Id = " + id);

    }

    public void deleteCar(int id) {
        vehicleManager.deleteVehicle((long) id);
    }

    public void saveToDatabase() {
        vehicleManager.saveVehicleBase();
    }

    public void modifyVehicle(Scanner scanner, int id) {
        Vehicle byId = vehicleManager.findById((long) id);
        System.out.println("Modyfikujesz pojazd o id " + id);
        System.out.println("Co chcesz zrobic? ");
        System.out.println("1. Dodaj ubezpieczenie");
        System.out.println("2. Dodaj przegląd pojazdu");
        System.out.println("3. Dodaj koszty serwisu");
        System.out.println("0. Powrót ");


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

    public void addInsuranceToCar(Scanner scanner, Vehicle vehicle) {
        System.out.println("Podaj datę rozpoczęcia ubezpieczenia ");
        scanner.nextLine();
        String startDate = scanner.nextLine();
        LocalDate parsedStartDate = LocalDate.parse(startDate);
        System.out.println("Podaj datę zakończenia");
        String endDate = scanner.nextLine();
        LocalDate parsedEndDate = LocalDate.parse(endDate);
        System.out.println("Podaj nazwę firmy ubezpieczeniowej ");
        String insuranceCompany = scanner.nextLine();
        System.out.println("Podaj typ ubezpieczenia : 1. Ac, 2.Oc, 3.AC-OC");
        int typeOfInsurance = scanner.nextInt();
        InsuranceType insuranceType = InsuranceType.mapIntToInsuranceType(typeOfInsurance);
        System.out.println("Cena ubezpieczenia ");
        int insurancePrice = scanner.nextInt();
        System.out.println("Dodano ubezpieczenie do pojazdu");
        System.out.println("-----------------------------------------");
        Insurance insurance = new Insurance(null, parsedStartDate, parsedEndDate, insuranceCompany, insuranceType, insurancePrice);
        vehicleManager.addInsurance(vehicle.getId(), insurance);
    }


    public void addServiceCostToVehicle(Scanner scanner, Vehicle vehicle) {
        System.out.println("Podaj typ kosztu: 1.Serwis/naprawy 2.Przegląd");
        int serviceType = scanner.nextInt();
        CostType costType = CostType.mapIntToCostType(serviceType);
        System.out.println("Podaj nazwę kosztu serwisu, który chcesz dodać np.opony : ");
        String pominiety = scanner.nextLine();
        String nameCost = scanner.nextLine();
        System.out.println("Podaj cenę podanego kosztu");
        double costPrice = scanner.nextDouble();
        System.out.println("Dodano nowy koszt serwisowania pojazdu");
        System.out.println("-----------------------------------------");
        Cost cost = new Cost(null, costType, nameCost, costPrice);
        vehicleManager.addCost(vehicle.getId(), cost);
    }

    public void addInspectionToCar(Scanner scanner, Vehicle vehicle) {
        System.out.println("Podaj datę przeglądu");
        scanner.nextLine();
        String startDate = scanner.nextLine();
        LocalDate parseStartDate = LocalDate.parse(startDate);
        System.out.println("Podaj datę końca obowiązywania przeglądu");
        String endDate = scanner.nextLine();
        LocalDate parseEndDate = LocalDate.parse(endDate);
        System.out.println("Podaj przebieg pojazdu");
        int odometerValue = scanner.nextInt();
        System.out.println("Podaj wynik badania technicznego");
        boolean inspectionResult = scanner.nextBoolean();
        System.out.println("Inspekcja auta została zaaktualizowana");
        System.out.println("----------------------------------------");
        Inspection inspection = new Inspection(null, parseStartDate, parseEndDate, odometerValue, inspectionResult);
        vehicleManager.addInspection(vehicle.getId(), inspection);
    }

}
