package app;

import app.utils.ScannerUtils;
import inspection.manager.InspectionManager;
import inspection.model.Inspection;
import vehicle.model.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class InspectionMenu {

    private Vehicle vehicle;
    private Scanner scanner;
    private InspectionManager inspectionManager;

    public InspectionMenu(Vehicle vehicle, Scanner scanner, InspectionManager inspectionManager) {
        this.vehicle = vehicle;
        this.scanner = scanner;
        this.inspectionManager = inspectionManager;
    }

    public void printInspectionMenu() {
        out.println();
        out.println("Modyfikujesz informacje o przeglądzie pojazdu o id: " + vehicle.getId());
        out.println("1. Dodaj nowy przegląd okresowy pojazdu");
        out.println("2. Usuń przegląd pojazdu");
        out.println("3. Wyświetl historię badań okresowych pojazdu ");
        out.println("0. Powrót ");
        out.println();
    }

    public void handleInspection() {
        while (true) {
            printInspectionMenu();
            switch (scanner.nextInt()) {
                case 1:
                    addInspectionToCar();
                    break;
                case 2:
                    deleteCarInspection();
                    break;
                case 3:
                    showInspection();
                    break;
                case 0:
                    return;
                default:
                    throw new IllegalArgumentException("Wybierz poprawną opcję");
            }
        }
    }

    private void showInspection() {
        List<Inspection> inspectionForVehicle = inspectionManager.getInspectionForVehicle(vehicle.getId());
        if (inspectionForVehicle.isEmpty()){
            out.println("Brak badań technicznych dla podanego pojazdu");
        }
        for (Inspection inspection : inspectionForVehicle) {
            out.printf("Badanie techniczne nr %d, data badania technicznego: %s" +
                    " , data zakończenia badania technicznego: %s , przebieg pojazdu: %d, rezultat badania: %s",
                    inspection.getId(), inspection.getInspectionDate().toString(), inspection.getEndOfInspection().toString(),
                    inspection.getOdometer(), inspection.isResult() ? "Pozytywny" : "Negatywny");
        }
    }

    private void addInspectionToCar() {
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
        Inspection inspection = new Inspection(null, parseStartDate, parseEndDate, odometerValue, inspectionResult);
        inspectionManager.addInspection(vehicle.getId(), inspection);
    }

    private void deleteCarInspection() {
        out.println("Podaj id pojazdu do usuniecia badania okresowego");
        long id = scanner.nextInt();
        out.println("Usunięto badanie techniczne pojazdu o id: " + id);
        inspectionManager.deleteInspection(id);
    }
}
