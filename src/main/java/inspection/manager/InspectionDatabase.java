package inspection.manager;

import app.database.DBConnection;
import inspection.model.Inspection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InspectionDatabase {
    private final DBConnection dbConnection ;

    public InspectionDatabase(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addInspection(Long id, Inspection inspection){
        PreparedStatement addingInspectionStatement ;
        try {
            addingInspectionStatement = dbConnection.prepareStatement("INSERT INTO inspection (inspectionDate, endOfInspection, odometer,result, vehicle_id) VALUES (?,?,?,?,?)");
            addingInspectionStatement.setDate(1, Date.valueOf(inspection.getInspectionDate()));
            addingInspectionStatement.setDate(2, Date.valueOf(inspection.getEndOfInspection()));
            addingInspectionStatement.setInt(3, inspection.getOdometer());
            addingInspectionStatement.setBoolean(4, inspection.isResult());
            addingInspectionStatement.setLong(5, id);

            addingInspectionStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }// dodac metody ktore usuna inspekcje, koszty i ubezpieczenie
}
