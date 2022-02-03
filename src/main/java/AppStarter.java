import aplication.FleetManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppStarter {
    public static void main(String[] args) {

//        InputStream resourceAsStream = null ;
//        try {
//            resourceAsStream = new FileInputStream("project.properties");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Properties properties = new Properties();
//        try {
//            properties.load(resourceAsStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(properties.getProperty("db.user"));
//        while (true){
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(args[0]);
//        System.out.println(args[1]);
        FleetManager fleetManager = new FleetManager();
        fleetManager.start();
    }
}
