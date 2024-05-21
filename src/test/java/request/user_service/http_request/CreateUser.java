package request.user_service.http_request;

import base_urls.QuasparepartsBaseUrl;
import base_urls.QuasparepartsBaseUrl2;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static request.user_service.http_request.GetUser.*;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class CreateUser extends QuasparepartsBaseUrl2 {

    //_________________________TC04___________________________
    @Test(dependsOnMethods = "request.user_service.http_request.GetUser.getUserInfo"
            , testName = "TC04",priority = 1)

    public void addNewUser(){
        // Set Url
        spec.pathParams("first","v1",
                "second","organization",
                         "third","user",
                         "fourth","register-manual");

        // Set Expected data
        Map<String,Object> payLoad = new HashMap<>();
        payLoad.put("app_id",appId);
        payLoad.put("organization_id",orgId);
        payLoad.put("email","apitest@email.com");
        payLoad.put("subscription_id",subId);
        payLoad.put("default_role_id",23);

        // Send request and get response
        Response response = given(spec).when().body(payLoad).post("{first}/{second}/{third}/{fourth}");
        response.prettyPrint();

        // Assertion
        Map<String,Object> actualData = convertJsonToJava(response.asString(),Map.class);
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");
        assertTrue(actualData.containsKey("id"));
        assertEquals(actualData.get("username"),payLoad.get("email"));
        assertEquals(actualData.get("is_email_verified"),false);

        writeNewUserIdToFile(actualData.get("id"));

    }

    //_________________________TC05___________________________
    @Test(dependsOnMethods = "request.user_service.http_request.GetUser.getUserInfo",testName = "TC05",priority = 2)
    public void addNewUserWithoutEmail(){
        // Set Url
        spec.pathParams("first","v1",
                "second","organization",
                "third","user",
                "fourth","register-manual");

        // Set Expected data
        Map<String,Object> payLoad = new HashMap<>();
        payLoad.put("app_id",appId);
        payLoad.put("organization_id",orgId);
        payLoad.put("subscription_id",subId);
        payLoad.put("default_role_id",23);

        // Send request and get response
        Response response = given(spec).when().body(payLoad).post("{first}/{second}/{third}/{fourth}");
        response.prettyPrint();

        // Assertion
        response.then()
                .statusCode(406)
                .contentType(ContentType.JSON)
                .body("detail",equalTo("Expected fields of email, app_id, subscription_id, organization_id, and default_role_id can not be null"));

    }

    //_________________________TC06___________________________
    @Test(dependsOnMethods = "request.user_service.http_request.GetUser.getUserInfo",testName = "TC06",priority = 3)
    public void addNewUserWithoutRole(){
        // Set Url
        spec.pathParams("first","v1",
                "second","organization",
                "third","user",
                "fourth","register-manual");

        // Set Expected data
        Map<String,Object> payLoad = new HashMap<>();
        payLoad.put("app_id",appId);
        payLoad.put("organization_id",orgId);
        payLoad.put("email","apitest@email.com");
        payLoad.put("subscription_id",subId);
        payLoad.put("default_role_id",null);

        // Send request and get response
        Response response = given(spec).when().body(payLoad).post("{first}/{second}/{third}/{fourth}");
        response.prettyPrint();

        // Assertion
        response.then()
                .statusCode(406)
                .contentType(ContentType.JSON)
                .body("detail",equalTo("Expected fields of email, app_id, subscription_id, organization_id, and default_role_id can not be null"));
    }
    public void writeNewUserIdToFile(Object newUserId){
        String separator = System.getProperty("file.separator");
        String fileName = System.getProperty("user.dir")+separator+"src"+separator+
                "test"+separator+"java"+separator+"request"+separator+"user_service"+
                separator+"http_request"+separator+"zNewUserId.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(newUserId+"");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
