package vehicle.model;

import cost.model.Cost;
import inspection.model.Inspection;
import insurance.model.Insurance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle implements Serializable {

    private Long id;
    private VehicleType type;
    private String brand;
    private String model;
    private String vin;
    private int engineCapacity;
    private int power;
    private int productionYear;
    private int carMilage;
    private List<Insurance> insurances = new ArrayList<>();//1:n
    private List<Inspection> inspections = new ArrayList<>();//1:N
    private List<Cost> costs = new ArrayList<>();// 1:N

    public Vehicle(Long id,VehicleType type, String brand, String model, String vin, int engineCapacity, int power, int productionYear, int carMilage, List<Insurance> insurances, List<Inspection> inspections, List<Cost> costs) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.engineCapacity = engineCapacity;
        this.power = power;
        this.productionYear = productionYear;
        this.carMilage = carMilage;
        this.insurances = insurances;
        this.inspections = inspections;
        this.costs = costs;
    }

    public Vehicle(VehicleType type,String brand, String model, String vin, int engineCapacity) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.vin = vin;
        this.engineCapacity = engineCapacity;
    }

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }




    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", vin='" + vin + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", power=" + power +
                ", productionYear=" + productionYear +
                ", carMilage=" + carMilage +
                ", insurances=" + insurances +
                ", inspections=" + inspections +
                ", costs=" + costs +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getVin() {
        return vin;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public int getPower() {
        return power;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public int getCarMilage() {
        return carMilage;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public List<Inspection> getInspections() {
        return inspections;
    }

    public List<Cost> getCosts() {
        return costs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCarMilage(int carMilage) {
        this.carMilage = carMilage;
    }

}

