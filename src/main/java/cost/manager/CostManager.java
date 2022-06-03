package cost.manager;

import app.database.DBConnection;
import cost.model.Cost;

import java.util.List;

public class CostManager {

    private final CostDatabase costDatabase;

    public CostManager(DBConnection dbConnection) {
        this.costDatabase = new CostDatabase(dbConnection);
    }

    public void addServiceCost(Long id, Cost cost){
       costDatabase.addServiceCost(id, cost);
    }

    public void deleteCost(Long id){
        costDatabase.removeCarCost(id);
    }

    public List<Cost> getCostForVehicle(Long id){
        return costDatabase.findCostForVehicle(id);
    }
}
