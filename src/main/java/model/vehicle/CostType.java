package model.vehicle;

import java.io.Serializable;

public enum CostType implements Serializable {

    SERVICE,INSPECTION;

    public static CostType mapIntToCostType(int i){
        switch (i){
            case 1:
                return SERVICE;
            case 2:
                return INSPECTION;
            default:
                throw new IllegalArgumentException("Nie ma takiego typu kosztu");

        }
    }

}
