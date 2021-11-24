package model.vehicle;
import java.io.Serializable;
import java.time.LocalDate;

public class Insurance implements Serializable {

    private LocalDate startDate ;
    private LocalDate expireDate;
    private String insuranceCompany;
    private InsuranceType insuranceType;
    private int lastPriceOfInsurence;

    public Insurance(LocalDate startDate, LocalDate expireDate, String insuranceCompany, InsuranceType insuranceType, int lastPriceOfInsurence) {
        this.startDate = startDate;
        this.expireDate = expireDate;
        this.insuranceCompany = insuranceCompany;
        this.insuranceType = insuranceType;
        this.lastPriceOfInsurence = lastPriceOfInsurence;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "startDate=" + startDate +
                ", expireDate=" + expireDate +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                ", insuranceType=" + insuranceType +
                ", lastPriceOfInsurence=" + lastPriceOfInsurence +
                '}';
    }
}
