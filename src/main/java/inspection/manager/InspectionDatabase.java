package inspection.manager;

import app.database.DBConnection;
import inspection.model.Inspection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InspectionDatabase {

    private final DBConnection dbConnection;

    public InspectionDatabase(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addInspection(Long id, Inspection inspection) {
        PreparedStatement addingInspectionStatement;
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
    }

    public void removeInspection(long id) {
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("DELETE FROM inspection WHERE vehicle_id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Inspection> findInspectionForVehicle(Long id) {

        PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT * from inspection where vehicle_id = ?");
        try {
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Inspection> inspectionList = new ArrayList<>();
            while(resultSet.next()){
                long inspectionId = resultSet.getLong("id");
                LocalDate inspectionDate = resultSet.getDate("inspectionDate").toLocalDate();
                LocalDate endOfInspection = resultSet.getDate("endOfInspection").toLocalDate();
                int odometer = resultSet.getInt("odometer");
                boolean inspectionResult = resultSet.getBoolean("result");
                Inspection inspection = new Inspection(inspectionId,inspectionDate,endOfInspection,odometer,inspectionResult);
                inspectionList.add(inspection);
            }
            return inspectionList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

}
