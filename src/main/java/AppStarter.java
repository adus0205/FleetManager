import aplication.FleetManager;
import manager.VehicleBase;
import manager.VehicleManager;
import model.vehicle.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppStarter {
    public static void main(String[] args) {

        FleetManager fleetManager = new FleetManager();
        fleetManager.start();

    }
}
