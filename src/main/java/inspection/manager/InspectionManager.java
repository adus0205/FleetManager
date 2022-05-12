package inspection.manager;

import app.database.DBConnection;
import inspection.model.Inspection;

public class InspectionManager {

    private final InspectionDatabase inspectionDatabase;

    public InspectionManager(DBConnection dbConnection) {
        this.inspectionDatabase = new InspectionDatabase(dbConnection);
    }

    public void addInspection(Long id, Inspection inspection) {
        inspectionDatabase.addInspection(id, inspection);
    }
}
