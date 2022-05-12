package cost.manager;

import app.database.DBConnection;
import cost.model.Cost;

public class CostManager {

    private final CostDatabase costDatabase;

    public CostManager(DBConnection dbConnection) {
        this.costDatabase = new CostDatabase(dbConnection);
    }

    public void addServiceCost(Long id, Cost cost){
       costDatabase.addServiceCost(id, cost);
    }
}
