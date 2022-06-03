package vehicle.manager;

import app.database.DBConnection;
import cost.manager.CostManager;
import cost.model.Cost;
import cost.model.CostType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleManagerTest {

    @Test
    void sprawdzenieZeNowoDodanyPojazdNiePosiadaKosztow() {
        DBConnection dbConnection = new DBConnection("jdbc:mysql://localhost:3306/vehiclemanager","adrian","asd123");

        VehicleManager vehicleManager = new VehicleManager(dbConnection);
        Long idDodanegoPojazdu = vehicleManager.addNewMotorcycle("bmw", "m2", "wb4666", 3000);

        Double sumaKosztow = vehicleManager.costSum(idDodanegoPojazdu);

        Assertions.assertEquals(0,sumaKosztow);
    }

    @Test
    void test() {
        DBConnection dbConnection = new DBConnection("jdbc:mysql://localhost:3306/vehiclemanager","adrian","asd123");

        VehicleManager vehicleManager = new VehicleManager(dbConnection);
        CostManager costManager = new CostManager(dbConnection);

        Long idDodanegoPojazdu = vehicleManager.addNewMotorcycle("bmw", "m2", "wb4666", 3000);
        costManager.addServiceCost(idDodanegoPojazdu,new Cost(null, CostType.SERVICE,"opony",2000));
        costManager.addServiceCost(idDodanegoPojazdu,new Cost(null, CostType.SERVICE,"olej",100));
        Double sumaKosztow = vehicleManager.costSum(idDodanegoPojazdu);

        Assertions.assertEquals(2100,sumaKosztow);
    }
}