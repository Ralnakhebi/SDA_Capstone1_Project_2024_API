package request.user_service.http_request;

import base_urls.QuasparepartsBaseUrl;
import base_urls.QuasparepartsBaseUrl2;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class UpdateUser extends QuasparepartsBaseUrl2 {
    //______________________TC07_____________________
    //dependsOnMethods = {"request.user_service.http_request.CreateUser.addNewUser",
    //                              "request.user_service.http_request.GetUser.getUserInfo"}
    @Test(dependsOnMethods = {"request.user_service.http_request.CreateUser.addNewUser"},testName = "TC07",priority = 1)

    public void updateTheNewUser(){
        // Set Url
        spec.pathParams("first","v1"
                ,"second","user");

        // Set expected data
        Map<String,Object> payLoad = new HashMap<>();

        Integer newUserId = readNewUserIdFromFile();
        payLoad.put("id",newUserId);
        payLoad.put("email","apitest@email.com");
        payLoad.put("name","Norah");

        // Send request and get response
        Response response = given().spec(spec).body(payLoad).when().put("{first}/{second}");
        response.prettyPrint();

        // Assertion
        Map<String,Object> actualData = convertJsonToJava(response.asString(),Map.class);

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertEquals(actualData.get("id"),newUserId);
        assertEquals(actualData.get("name"),payLoad.get("name"));
        assertEquals(actualData.get("username"),payLoad.get("email"));
        assertEquals(actualData.get("email"),payLoad.get("email"));
    }

    //______________________TC08_____________________
    @Test(testName = "TC08",priority = 2)
    public void updateTheNewUserWithoutId(){
        // Set Url
        spec.pathParams("first","v1","second","user");

        // Set expected data
        Map<String,Object> payLoad = new HashMap<>();
        payLoad.put("email","apitest@email.com");
        payLoad.put("name","norah");

        // Send request and get response
        Response response = given(spec).when().body(payLoad).put("{first}/{second}");
        response.prettyPrint();

        // Assertion
        response.then()
                .statusCode(406)
                .contentType(ContentType.JSON)
                .body("message",equalTo("User update failed"))
                .body("detail",equalTo("Expected field of user_id can not be null"));
    }
    public static Integer readNewUserIdFromFile(){
        String separator = System.getProperty("file.separator");
        String fileName = System.getProperty("user.dir")+separator+"src"+separator+
                "test"+separator+"java"+separator+"request"+separator+"user_service"+
                separator+"http_request"+separator+"zNewUserId.txt";
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(line);
    }
}

