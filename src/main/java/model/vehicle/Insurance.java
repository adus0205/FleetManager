package model.vehicle;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    public int getLastPriceOfInsurence() {
        return lastPriceOfInsurence;
    }

    public void setLastPriceOfInsurence(int lastPriceOfInsurence) {
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
