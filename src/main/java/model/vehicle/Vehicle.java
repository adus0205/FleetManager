package model.vehicle;

import java.io.Serializable;
import java.util.List;

public abstract class Vehicle implements Serializable {

    private Long id;
    private String brand;
    private String model;
    private String vin;
    private int engineCapacity;
    private int power;
    private int productionYear;
    private int carMilage;
    private List<Insurance> insurances;
    private List<Inspection> inspections;
    private List<Cost> costs;

    public Vehicle(Long id, String brand, String model, String vin, int engineCapacity, int power, int productionYear, int carMilage, List<Insurance> insurances, List<Inspection> inspections, List<Cost> costs) {
        this.id = id;
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
    // wzorzec builder ??

    public Vehicle(String brand, String model, String vin) {
        this.brand = brand;
        this.model = model;
        this.vin = vin;
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
    //        public static VehicleBuilder builder(){
//            return new VehicleBuilder();
//        }
//
//         public static class VehicleBuilder {
//
//            private Long id;
//            private String brand;
//            private String model;
//            private String vin;
//            private int engineCapacity;
//            private int power;
//            private int productionYear;
//            private int carMilage;
//            private List<Insurance> insurances;
//            private List<Inspection> inspections;
//            private List<Cost> costs;
//
//            public VehicleBuilder id(Long id) {
//                this.id = id;
//                return this;
//            }
//
//            public VehicleBuilder brand(String brand) {
//                this.brand = brand;
//                return this;
//            }
//
//            public VehicleBuilder model(String model) {
//                this.model = model;
//                return this;
//            }
//
//            public VehicleBuilder vin(String vin) {
//                this.vin = vin;
//                return this;
//            }
//
//            public VehicleBuilder engineCapacity(int engineCapacity) {
//                this.engineCapacity = engineCapacity;
//                return this;
//            }
//
//            public VehicleBuilder power(int power) {
//                this.power = power;
//                return this;
//            }
//
//
//             public VehicleBuilder productionYear(int productionYear) {
//                this.productionYear = productionYear;
//                return this;
//            }
//
//            public VehicleBuilder carMilage(int carMilage) {
//                this.carMilage = carMilage;
//                return this;
//            }
//
//            public VehicleBuilder insurences(List insurances) {
//                this.insurances = insurances;
//                return this;
//            }
//
//            public VehicleBuilder inspections(List inspections) {
//                this.inspections = inspections;
//                return this;
//            }
//
//            public VehicleBuilder costs(List costs) {
//                this.costs = costs;
//                return this;
//            }

    //public Vehicle build(){
    //return new Vehicle(id,brand,model,vin,engineCapacity,power,productionYear,carMilage,insurances,inspections,costs);
    //}
}

