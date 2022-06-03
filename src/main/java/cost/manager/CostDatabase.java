package cost.manager;

import app.database.DBConnection;
import cost.model.Cost;
import cost.model.CostType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void removeCarCost(Long id) {
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("DELETE FROM cost WHERE vehicle_id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Cost> findCostForVehicle(Long id){
        PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT * FROM cost WHERE vehicle_id = ?");
        try {
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Cost> costList = new ArrayList<>();
            while (resultSet.next()){

                long costId = resultSet.getLong("id");
                CostType costType = CostType.valueOf(resultSet.getString("type"));
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Cost cost = new Cost(costId,costType,name,price);
                costList.add(cost);

            }
            return costList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }



}
