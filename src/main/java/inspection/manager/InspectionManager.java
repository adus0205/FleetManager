package inspection.manager;

import app.database.DBConnection;
import inspection.model.Inspection;

import java.util.List;

public class InspectionManager {

    private final InspectionDatabase inspectionDatabase;

    public InspectionManager(DBConnection dbConnection) {
        this.inspectionDatabase = new InspectionDatabase(dbConnection);
    }

    public void addInspection(Long id, Inspection inspection) {
        inspectionDatabase.addInspection(id, inspection);
    }

    public void deleteInspection(Long id){
        inspectionDatabase.removeInspection(id);
    }

    public List<Inspection> getInspectionForVehicle(Long id){
        return inspectionDatabase.findInspectionForVehicle(id);

    }
}
