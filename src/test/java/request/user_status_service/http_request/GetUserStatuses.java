package request.user_status_service.http_request;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import request.user_status_service.pojo.UserStatusPojo;

import java.io.*;
import java.util.List;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class GetUserStatuses {

    /************************************************
     *             Get All User Statuses            *
     ************************************************/
    @Test(description = "To verify that all user statuses can be fetched",priority = -1)
    public void testCase1(){
        // Set Url
        spec.pathParam("first","user-status");

        //---------------------------------------------------

        // Send Request and Get Response
        Response response = given(spec).when().get("{first}");
        //Set ID form exist Ids to use in GET, PUT and DELETE Request
        List<Integer> ids=response.jsonPath().getList("id");
        writeUserStatusIdToFile(ids.get(0));
        response.prettyPrint();

        //---------------------------------------------------

        // Do the assertion

        // Status Code should be 200
        response.then().statusCode(200);
        // There are at least 2 User Status
        Assert.assertTrue(response.jsonPath().getList("id").size()>2);

    }

    /************************************************
     *             Get User Status by ID            *
     ************************************************/
    @Test(description = "To verify that user status can be fetched by id")
    public void testCase3(){
        // Set Url
        Integer userStatusesId =readUserStatusIdFromFile();
        System.out.println("userStatusesId = " + userStatusesId);
        spec.pathParams("first","user-status","second",userStatusesId);

        //---------------------------------------------------

        //Set Expected Data
        String payloadStr= """
                {
                    "name": "Active",
                    "description": "User account is activated and authorized to use the application"
                }""";

        // Convert String to Pojo Class Using ObjectMapper
        UserStatusPojo expectedData =convertJsonToJava(payloadStr, UserStatusPojo.class);

        // Send Request and Get Response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        UserStatusPojo actualData =convertJsonToJava(response.asString(), UserStatusPojo.class);

        //---------------------------------------------------

        // Do the assertion

        // Status Code should be 200
        response.then().statusCode(200);
        Assert.assertEquals(actualData.getName(),expectedData.getName());
        Assert.assertEquals(actualData.getDescription(),expectedData.getDescription());

    }


    /************************************************
     *      Get Non-existing User Status by ID      *
     ************************************************/
    @Test(description = "To verify that non-existing user status cannot be fetched",dependsOnMethods = {"request.user_status_service.http_request.DeleteUserStatuses.testCase5"})
    public void testCase7(){
        // Set Url
        Integer userStatusesId =readUserStatusIdFromFile();
        System.out.println("userStatusesId = " + userStatusesId);
        spec.pathParams("first","user-status","second",userStatusesId);

        //---------------------------------------------------

        //Set Expected Data
        String payloadStr= """
                {
                    "name": "Deactivated",
                    "description": "User account is deactivated, and not authorized to access any the application"
                }""";

        // Convert String to Pojo Class Using ObjectMapper
        UserStatusPojo expectedData =convertJsonToJava(payloadStr, UserStatusPojo.class);

        // Send Request and Get Response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        UserStatusPojo actualData =convertJsonToJava(response.asString(), UserStatusPojo.class);

        //---------------------------------------------------

        // Do the assertion

        // Status Code should be 404 Not Found and response body is empty
        response.then().statusCode(404);
        Assert.assertTrue(response.asString().isEmpty());

    }
    public static Integer readUserStatusIdFromFile(){
        String separator = System.getProperty("file.separator");
        String fileName = System.getProperty("user.dir")+separator+"src"+separator+
                "test"+separator+"java"+separator+"request"+separator+"user_status_service"+
                separator+"pojo"+separator+"UserStatusId.txt";
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