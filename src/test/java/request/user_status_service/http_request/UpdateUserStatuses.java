package request.user_status_service.http_request;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import request.user_status_service.pojo.UserStatusPojo;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class UpdateUserStatuses {
    /************************************************
     *         Update Existing User Status          *
     ************************************************/
    @Test(description = "To verify that the user status can be updated using an existing Id")
    public void testCase4(){
        // Set Url

        spec.pathParam("first","user-status");

        //---------------------------------------------------

        //Set Expected Data
        String payloadStr= "{\n" +
                "    \"id\": "+GetUserStatuses.readUserStatusIdFromFile()+",\n" +
                "    \"name\": \"Deactivated\",\n" +
                "    \"description\": \"User account is deactivated, and not authorized to access any the application TEST\"\n" +
                "}";

        // Convert String to Pojo Class Using ObjectMapper
        UserStatusPojo expectedData =convertJsonToJava(payloadStr, UserStatusPojo.class);

        // Send Request and Get Response
        Response response = given(spec).body(expectedData).when().put("{first}");
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
     *       Update Non-existing User Status        *
     ************************************************/
    @Test(description = "To verify that non-existing user status cannot be updated",dependsOnMethods = {"request.user_status_service.http_request.DeleteUserStatuses.testCase5"})
    public void testCase6(){
        // Set Url
        spec.pathParam("first","user-status");

        //---------------------------------------------------

        //Set Expected Data
        String payloadStr= "{\n" +
                "    \"id\": "+GetUserStatuses.readUserStatusIdFromFile()+",\n" +
                "    \"name\": \"Deactivated\",\n" +
                "    \"description\": \"User account is deactivated, and not authorized to access any the application TEST\"\n" +
                "}";

        // Convert String to Pojo Class Using ObjectMapper
        UserStatusPojo expectedData =convertJsonToJava(payloadStr, UserStatusPojo.class);

        // Send Request and Get Response
        Response response = given(spec).body(expectedData).when().put("{first}");
        response.prettyPrint();
        //---------------------------------------------------

        // Do the assertion

        // Status Code should be 404 Not Found and response body is empty
        response.then().statusCode(404);
        Assert.assertTrue(response.asString().isEmpty());


    }
}
