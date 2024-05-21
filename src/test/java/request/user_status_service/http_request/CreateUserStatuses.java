package request.user_status_service.http_request;

import io.cucumber.java.sl.In;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import request.user_status_service.pojo.UserStatusPojo;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class CreateUserStatuses {


    /************************************************
     *             Add New User Status              *
     ************************************************/
    @Test(description = "To verify that the user status can be created")
    public void testCase2(){
        // Set Url
        spec.pathParam("first","user-status");

        // Set Expected Data Using Pojo
        String payloadStr= """
                {
                    "name": "Deactivated",
                    "description": "User account is deactivated, and not authorized to access any the application"
                }""";

        // Convert String to Pojo Class Using ObjectMapper
        UserStatusPojo payload =convertJsonToJava(payloadStr, UserStatusPojo.class);

        //---------------------------------------------------

        // Send Request and Get Response
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();
        UserStatusPojo actualData =convertJsonToJava(response.asString(), UserStatusPojo.class);
        //Set ID to use in GET, PUT and DELETE Request
        writeUserStatusIdToFile(actualData.getId());

        //---------------------------------------------------

        // Do the assertion

        // Status Code should be 201
        response.then().statusCode(201);
        // The Name is "Deactivated"
        Assert.assertEquals(actualData.getName(),payload.getName());
        // The Description is "User account is deactivated, and not authorized to access any the application"
        Assert.assertEquals(actualData.getDescription(),payload.getDescription());

    }

    /************************************************
     *          Add New User Status with ID         *
     ************************************************/
    @Test(description = "To verify that creating a new user status with a specified id gives correct Status Code and error message")
    public void testCase9(){
        // Set Url
        spec.pathParam("first","user-status");

        //---------------------------------------------------

        // Set Expected Data Using Pojo
        String payloadStr= """
                {
                    "id":0,
                    "name": "Deactivated",
                    "description": "User account is deactivated, and not authorized to access any the application"
                }""";

        // Convert String to Pojo Class Using ObjectMapper
        UserStatusPojo payload =convertJsonToJava(payloadStr, UserStatusPojo.class);

        //---------------------------------------------------

        // Send Request and Get Response
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();

        //---------------------------------------------------

        // Do the assertion

        //Using Soft Assertion
        SoftAssert softAssert=new SoftAssert();
        // Status Code should be 406 Not Acceptable status
        softAssert.assertEquals(response.statusCode(),406);
        // Body should be having "User status id must be null" error message
        response.then().body("message", equalTo("User status id must be null"));
        softAssert.assertAll();


    }

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
}
