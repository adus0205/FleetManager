package manager;

import model.vehicle.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehicleRealDatabase implements VehicleDatabase {

    private final static String DB_USER = "adrian";
    private final static String DB_PASSWORD = "asd123";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/vehiclemanager";
    private Connection connection;

    @Override
    public Long addVehicle(Vehicle vehicle) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO vehicle (brand,model,vin,engineCapacity,power,productionYear,carMilage) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setString(3, vehicle.getVin());
            preparedStatement.setInt(4, vehicle.getEngineCapacity());
            preparedStatement.setInt(5, vehicle.getPower());
            preparedStatement.setInt(6, vehicle.getProductionYear());
            preparedStatement.setInt(7, vehicle.getCarMilage());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            long id = generatedKeys.getLong(1);

            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeVehicle(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vehicle WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Vehicle findVehicleById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from vehicle where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new RuntimeException();
            }
            Vehicle car = new Car();
            car.setId(resultSet.getLong("id"));
            car.setBrand(resultSet.getString("brand"));
            car.setModel(resultSet.getString("model"));
            car.setVin(resultSet.getString("vin"));
            car.setEngineCapacity(resultSet.getInt("engineCapacity"));
            car.setPower(resultSet.getInt("power"));
            car.setProductionYear(resultSet.getInt("productionYear"));
            car.setCarMilage(resultSet.getInt("carMilage"));

            return car;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE vehicle SET brand = ?, model = ?, vin = ?, engineCapacity = ?, power = ?, productionYear = ?, carMilage = ?\n" +
                    "WHERE id = ?");
            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setString(3, vehicle.getVin());
            preparedStatement.setInt(4, vehicle.getEngineCapacity());
            preparedStatement.setInt(5, vehicle.getPower());
            preparedStatement.setInt(6, vehicle.getProductionYear());
            preparedStatement.setInt(7, vehicle.getCarMilage());
            preparedStatement.setLong(8, vehicle.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Vehicle> getAllVehicles() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from vehicle");

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Vehicle> vehicles = new ArrayList<>();
            while (resultSet.next()) {
                Vehicle car = new Car();
                car.setId(resultSet.getLong("id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setVin(resultSet.getString("vin"));
                car.setEngineCapacity(resultSet.getInt("engineCapacity"));
                car.setPower(resultSet.getInt("power"));
                car.setProductionYear(resultSet.getInt("productionYear"));
                car.setCarMilage(resultSet.getInt("carMilage"));
                vehicles.add(car);
            }
            return vehicles;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public Vehicle findVehicleByVin(String vin) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from vehicle where vin = ?");
            preparedStatement.setString(1, vin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new RuntimeException();
            }
            Vehicle car = new Car();
            car.setId(resultSet.getLong("id"));
            car.setBrand(resultSet.getString("brand"));
            car.setModel(resultSet.getString("model"));
            car.setVin(resultSet.getString("vin"));
            car.setEngineCapacity(resultSet.getInt("engineCapacity"));
            car.setPower(resultSet.getInt("power"));
            car.setProductionYear(resultSet.getInt("productionYear"));
            car.setCarMilage(resultSet.getInt("carMilage"));


            return car;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isConnected() {
        try {
            return connection.isValid(1000);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void addInsurance(Long id, Insurance insurance) {
        PreparedStatement preparedStatement1 = null;
        try {
            preparedStatement1 = connection.prepareStatement("INSERT INTO insurance (startDate, expireDate, insuranceCompany, insuranceType, lastPriceOfInsurence, vehicle_id) VALUES (?,?,?,?,?,?)");
            preparedStatement1.setDate(1, Date.valueOf(insurance.getStartDate()));
            preparedStatement1.setDate(2, Date.valueOf(insurance.getExpireDate()));
            preparedStatement1.setString(3, insurance.getInsuranceCompany());
            preparedStatement1.setString(4, insurance.getInsuranceType().name());
            preparedStatement1.setInt(5, insurance.getLastPriceOfInsurence());
            preparedStatement1.setLong(6, id);

            preparedStatement1.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addInspection(Long id, Inspection inspection) {
        PreparedStatement preparedStatement2 = null;
        try {
            preparedStatement2 = connection.prepareStatement("INSERT INTO inspection (inspectionDate, endOfInspection, odometer,result, vehicle_id) VALUES (?,?,?,?,?)");
            preparedStatement2.setDate(1, Date.valueOf(inspection.getInspectionDate()));
            preparedStatement2.setDate(2, Date.valueOf(inspection.getEndOfInspection()));
            preparedStatement2.setInt(3, inspection.getOdometer());
            preparedStatement2.setBoolean(4, inspection.isResult());
            preparedStatement2.setLong(5, id);

            preparedStatement2.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addCostService(Long id, Cost cost) {
        PreparedStatement preparedStatement3 = null;
        try {
            preparedStatement3 = connection.prepareStatement("INSERT INTO cost (type,name,price ,vehicle_id) VALUES (?,?,?,?)");
            preparedStatement3.setString(1, cost.getType().name());
            preparedStatement3.setString(2, cost.getName());
            preparedStatement3.setDouble(3, cost.getPrice());
            preparedStatement3.setLong(4, id);

            preparedStatement3.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public VehicleRealDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    public void closeDatabase() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
