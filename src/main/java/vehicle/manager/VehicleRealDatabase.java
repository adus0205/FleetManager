package vehicle.manager;

import app.database.DBConnection;
import vehicle.model.Car;
import vehicle.model.Motorcycle;
import vehicle.model.Vehicle;
import vehicle.model.VehicleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleRealDatabase implements VehicleDatabase {

    private final DBConnection connection;

    @Override
    public Long addVehicle(Vehicle vehicle) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatementWithKeys("INSERT INTO vehicle (type,brand,model,vin,engineCapacity,power,productionYear,carMilage) VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,vehicle.getType().name());
            preparedStatement.setString(2, vehicle.getBrand());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setString(4, vehicle.getVin());
            preparedStatement.setInt(5, vehicle.getEngineCapacity());
            preparedStatement.setInt(6, vehicle.getPower());
            preparedStatement.setInt(7, vehicle.getProductionYear());
            preparedStatement.setInt(8, vehicle.getCarMilage());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();

            return generatedKeys.getLong(1);
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
                throw new RuntimeException("Wystapil blad przy pobieraniu z bazy");
            }
            VehicleType type = VehicleType.valueOf(resultSet.getString("type"));
            Vehicle vehicle ;
            if (type == VehicleType.CAR){
                vehicle = new Car();
            }else {
                vehicle = new Motorcycle();
            }

            vehicle.setId(resultSet.getLong("id"));
            vehicle.setBrand(resultSet.getString("brand"));
            vehicle.setModel(resultSet.getString("model"));
            vehicle.setVin(resultSet.getString("vin"));
            vehicle.setEngineCapacity(resultSet.getInt("engineCapacity"));
            vehicle.setPower(resultSet.getInt("power"));
            vehicle.setProductionYear(resultSet.getInt("productionYear"));
            vehicle.setCarMilage(resultSet.getInt("carMilage"));

            return vehicle;

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
                String type = resultSet.getString("type");

                Vehicle vehicle = createVehicleByType( type);

                vehicle.setId(resultSet.getLong("id"));
                vehicle.setBrand(resultSet.getString("brand"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setVin(resultSet.getString("vin"));
                vehicle.setEngineCapacity(resultSet.getInt("engineCapacity"));
                vehicle.setPower(resultSet.getInt("power"));
                vehicle.setProductionYear(resultSet.getInt("productionYear"));
                vehicle.setCarMilage(resultSet.getInt("carMilage"));
                vehicles.add(vehicle);
            }
            return vehicles;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    private Vehicle createVehicleByType( String type) {

        if (type==null){
            throw new IllegalArgumentException(" Wartość null w polu type " );
        }
        VehicleType vehicleType = VehicleType.valueOf(type);
        Vehicle vehicle;

        if (vehicleType == VehicleType.CAR){
            vehicle = new Car();
        }else if (vehicleType == VehicleType.MOTORCYCLE){
            vehicle = new Motorcycle();
        }else{
            throw new IllegalArgumentException( " Pojazd powinien być jednym z tych typów: "+ Arrays.stream(VehicleType.values()).map(VehicleType::name).collect(Collectors.toList()));
        }
        return vehicle;
    }

    @Override
    public Vehicle findVehicleByVin(String vin) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from vehicle where vin = ?");
            preparedStatement.setString(1, vin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            Vehicle vehicle = createVehicleByType(resultSet.getString("type"));
            vehicle.setId(resultSet.getLong("id"));
            vehicle.setBrand(resultSet.getString("brand"));
            vehicle.setModel(resultSet.getString("model"));
            vehicle.setVin(resultSet.getString("vin"));
            vehicle.setEngineCapacity(resultSet.getInt("engineCapacity"));
            vehicle.setPower(resultSet.getInt("power"));
            vehicle.setProductionYear(resultSet.getInt("productionYear"));
            vehicle.setCarMilage(resultSet.getInt("carMilage"));
            return vehicle;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isConnected() {
        return connection.isConnected();
    }

    public VehicleRealDatabase(DBConnection dbConnection) {
        this.connection = dbConnection;
    }

}
