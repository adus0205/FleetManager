package insurance.manager;

import app.database.DBConnection;
import insurance.model.Insurance;

public class InsuranceManager {

    private final InsuranceDatabase insuranceDatabase;

    public InsuranceManager(DBConnection dbConnection) {
        this.insuranceDatabase = new InsuranceDatabase(dbConnection);
    }

    public void addInsurance(Long id, Insurance insurance){
        insuranceDatabase.addInsurance(id, insurance);
    }

    public void deleteInsurance(Long id){
        insuranceDatabase.removeInsurance(id);
    }
}
