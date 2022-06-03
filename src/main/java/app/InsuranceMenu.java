package app;

import app.utils.ScannerUtils;
import insurance.manager.InsuranceManager;
import insurance.model.Insurance;
import insurance.model.InsuranceType;
import vehicle.model.Vehicle;

import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.System.out;

public class InsuranceMenu {

    private Vehicle vehicle;
    private Scanner scanner;
    private InsuranceManager insuranceManager;

    public InsuranceMenu(Vehicle vehicle, Scanner scanner, InsuranceManager insuranceManager) {
        this.vehicle = vehicle;
        this.scanner = scanner;
        this.insuranceManager = insuranceManager;
    }

    public void insurancePrintMenu(){
        System.out.println("1. Dodaj ubezpieczenie");
        System.out.println("2. Usuń ubezpieczenie");
        System.out.println("3. Wyświetl historię polis ubezpieczeniowych");
        System.out.println("0. Powrót");
    }

    public void handleInsurance(){
        while(true){
            insurancePrintMenu();
            switch (scanner.nextInt()){
                case 1:
                    addInsuranceToCar();
                    break;
                case 2:
                    deleteCarInsurance();
                    break;
                case 3:
                    break;
                case 0:
                    return;
                default:
                    throw new IllegalArgumentException("Wybierz opcję od 0 do 3");
            }
        }
    }

    private void addInsuranceToCar() {
        out.println("Podaj datę rozpoczęcia ubezpieczenia ");
        scanner.nextLine();
        LocalDate parsedStartDate = ScannerUtils.readDate(scanner);
        out.println("Podaj datę zakończenia");
        LocalDate parsedEndDate = ScannerUtils.readDate(scanner);
        out.println("Podaj nazwę firmy ubezpieczeniowej ");
        String insuranceCompany = scanner.nextLine();
        out.println("Podaj typ ubezpieczenia : 1. AC, 2.OC, 3.AC-OC");
        int typeOfInsurance = scanner.nextInt();
        InsuranceType insuranceType = InsuranceType.mapIntToInsuranceType(typeOfInsurance);
        out.println("Cena ubezpieczenia ");
        int insurancePrice = scanner.nextInt();
        out.println("Dodano ubezpieczenie do pojazdu");
        Insurance insurance = new Insurance(null, parsedStartDate, parsedEndDate, insuranceCompany, insuranceType, insurancePrice);
        insuranceManager.addInsurance(vehicle.getId(), insurance);
    }

    private void deleteCarInsurance(){
        out.println("Podaj id pojazdu do usunięcia ubezpieczenia");
        long id = scanner.nextLong();
        out.println("Usunięto ubezpieczenie pojazdu o id: " + id);
        insuranceManager.deleteInsurance(id);

    }
}
