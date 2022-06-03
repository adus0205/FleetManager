package insurance.manager;

import app.database.DBConnection;
import insurance.model.Insurance;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsuranceDatabase {

    private final DBConnection dbConnection;

    public InsuranceDatabase(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addInsurance(Long id, Insurance insurance){
        PreparedStatement addingInsuranceStatement;
        try {
            addingInsuranceStatement = dbConnection.prepareStatement("INSERT INTO insurance (startDate, expireDate, insuranceCompany, insuranceType, lastPriceOfInsurence, vehicle_id) VALUES (?,?,?,?,?,?)");
            addingInsuranceStatement.setDate(1, Date.valueOf(insurance.getStartDate()));
            addingInsuranceStatement.setDate(2, Date.valueOf(insurance.getExpireDate()));
            addingInsuranceStatement.setString(3, insurance.getInsuranceCompany());
            addingInsuranceStatement.setString(4, insurance.getInsuranceType().name());
            addingInsuranceStatement.setInt(5, insurance.getLastPriceOfInsurence());
            addingInsuranceStatement.setLong(6, id);

            addingInsuranceStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void removeInsurance(Long id) {
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("DELETE FROM insurance WHERE vehicle_id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
