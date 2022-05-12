package app.utils;

import java.time.LocalDate;
import java.util.Scanner;

public class ScannerUtils {

    public static LocalDate readDate(Scanner scanner){
        String stringDate = scanner.nextLine();
        return LocalDate.parse(stringDate);
    }
}
