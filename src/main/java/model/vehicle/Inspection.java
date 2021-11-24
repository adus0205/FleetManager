package model.vehicle;

import java.io.Serializable;
import java.time.LocalDate;

public class Inspection implements Serializable {

    private LocalDate inspectionDate;
    private LocalDate endOfInspection;
    private int odometer;
    private boolean result;

    public Inspection(LocalDate inspectionDate, LocalDate endOfInspection, int odometer, boolean result) {
        this.inspectionDate = inspectionDate;
        this.endOfInspection = endOfInspection;
        this.odometer = odometer;
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
