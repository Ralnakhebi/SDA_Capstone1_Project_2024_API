package utilities;

import io.restassured.internal.support.FileReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileUtils {

    public void writeUserStatusIdToFile(Integer userStatusId){
        String separator = System.getProperty("file.separator");
        String fileName = System.getProperty("user.dir")+separator+"src"+separator+
                "test"+separator+"java"+separator+"request"+separator+"user_status_service"+
                separator+"pojo"+separator+"UserStatusId.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(userStatusId+"");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static Integer readUserStatusIdToFile(){
//        String separator = System.getProperty("file.separator");
//        String fileName = System.getProperty("user.dir")+separator+"src"+separator+
//                "test"+separator+"java"+separator+"request"+separator+"user_status_service"+
//                separator+"pojo"+separator+"UserStatusId.txt";
//        String line = null;
//        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//                break;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return Integer.parseInt(line);
//    }












}
