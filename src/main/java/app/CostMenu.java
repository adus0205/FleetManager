package app;

import cost.manager.CostManager;
import cost.model.Cost;
import cost.model.CostType;
import vehicle.model.Vehicle;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class CostMenu {


    private  Vehicle vehicle;
    private Scanner scanner ;
    private CostManager costManager;

    public CostMenu(Vehicle vehicle, Scanner scanner, CostManager costManager) {
        this.vehicle = vehicle;
        this.scanner = scanner;
        this.costManager = costManager;
    }

    public void printMenu(){

        out.println("Modyfikujesz koszty pojazdu o id " + vehicle.getId());
        out.println("Co chcesz zrobic? ");
        out.println("1. Dodaj koszt");
        out.println("2. Usuń koszty serwisu");
        out.println("3. Wyświetl wszystkie koszty pojazdu");
        out.println("0. Powrót ");
    }

    public void handle(){
        while (true){
            printMenu();
            switch (scanner.nextInt()){
                case 1:
                    addServiceCostToVehicle();
                    break;
                case 2:
                    removeServiceCost();
                    break;
                case 3:
                    showCosts();
                    break;
                case 0:
                    return;
                default:
                    throw new IllegalArgumentException("Wybierz poprawną opcję");
            }
        }

    }

    private void showCosts() {
        List<Cost> costForVehicle = costManager.getCostForVehicle(vehicle.getId());
        for (Cost cost : costForVehicle) {
            out.printf("Koszt nr %d, typ: %s, nazwa kosztu: %s, cena podanego kosztu: %.2f%n", cost.getId(), cost.getType().toString(),cost.getName(), cost.getPrice());
        }

    }

    private void addServiceCostToVehicle() {
        out.println("Podaj typ kosztu: 1.Serwis/naprawy 2.Przegląd");
        int serviceType = scanner.nextInt();
        CostType costType = CostType.mapIntToCostType(serviceType);
        out.println("Podaj nazwę kosztu serwisu, który chcesz dodać np.opony : ");
        scanner.nextLine();
        String nameCost = scanner.nextLine();
        out.println("Podaj cenę podanego kosztu");
        double costPrice = scanner.nextDouble();
        out.println("Dodano nowy koszt serwisowania pojazdu");

        Cost cost = new Cost(null, costType, nameCost, costPrice);
        costManager.addServiceCost(vehicle.getId(), cost);
    }

    private void removeServiceCost(){
        out.println("Podaj id kosztu do usunięcia");
        long idCost = scanner.nextLong();
        costManager.deleteCost(idCost);
    }


}
