package vehicle.model;

import java.io.Serializable;

public enum VehicleType implements Serializable {

    CAR,MOTORCYCLE;

    public static VehicleType mapInToVehicleType(int i){
        switch (i){
            case 1:
                return CAR;
            case 2:
                return MOTORCYCLE;
            default:
                throw new IllegalArgumentException("Nie ma takiego typu pojazdu");

        }

    }
}
