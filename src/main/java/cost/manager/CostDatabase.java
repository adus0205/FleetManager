package cost.manager;

import app.database.DBConnection;
import cost.model.Cost;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CostDatabase {

    private final DBConnection dbConnection;

    public CostDatabase(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addServiceCost(Long id, Cost cost){
        PreparedStatement addingCostStatement ;
        try {
            addingCostStatement = dbConnection.prepareStatement("INSERT INTO cost (type,name,price ,vehicle_id) VALUES (?,?,?,?)");
            addingCostStatement.setString(1, cost.getType().name());
            addingCostStatement.setString(2, cost.getName());
            addingCostStatement.setDouble(3, cost.getPrice());
            addingCostStatement.setLong(4, id);

            addingCostStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
