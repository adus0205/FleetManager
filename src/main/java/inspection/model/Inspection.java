package inspection.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Inspection implements Serializable {

    private Long id;
    private LocalDate inspectionDate;
    private LocalDate endOfInspection;
    private int odometer;
    private boolean result;

    public Inspection(Long id, LocalDate inspectionDate, LocalDate endOfInspection, int odometer, boolean result) {
        this.id = id;
        this.inspectionDate = inspectionDate;
        this.endOfInspection = endOfInspection;
        this.odometer = odometer;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public LocalDate getEndOfInspection() {
        return endOfInspection;
    }

    public void setEndOfInspection(LocalDate endOfInspection) {
        this.endOfInspection = endOfInspection;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return
                "inspectionDate=" + inspectionDate +
                ", endOfInspection=" + endOfInspection +
                ", odometer=" + odometer +
                ", result=" + result +
                '}';
    }
}
