package insurance.model;

import java.io.Serializable;

public enum InsuranceType implements Serializable {

    OC,AC,AC_OC;

    public static InsuranceType mapIntToInsuranceType(int i){
        switch (i){
            case 1:
                return OC;
            case 2:
                return AC;
            case 3:
                return AC_OC;
            default:
                throw new IllegalArgumentException("Nie ma takiego typu ubezpieczenia");

        }
    }

}
