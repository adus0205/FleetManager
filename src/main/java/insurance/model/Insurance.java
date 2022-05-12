package insurance.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Insurance implements Serializable {

    private Long id;
    private LocalDate startDate ;
    private LocalDate expireDate;
    private String insuranceCompany;
    private InsuranceType insuranceType;
    private int lastPriceOfInsurence;

    public Insurance(Long id,LocalDate startDate, LocalDate expireDate, String insuranceCompany, InsuranceType insuranceType, int lastPriceOfInsurence) {
        this.id = id;
        this.startDate = startDate;
        this.expireDate = expireDate;
        this.insuranceCompany = insuranceCompany;
        this.insuranceType = insuranceType;
        this.lastPriceOfInsurence = lastPriceOfInsurence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public int getLastPriceOfInsurence() {
        return lastPriceOfInsurence;
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
