package request.user_status_service.http_request;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;


public class DeleteUserStatuses {


    /************************************************
     *      Delete Existing User Status by ID       *
     ************************************************/
    @Test(description = "To verify that existing user status can be deleted by Id",priority = 1)
    public void testCase5() {
        // Set Url
        Integer userStatusesId = GetUserStatuses.readUserStatusIdFromFile();
        System.out.println("userStatusesId = " + userStatusesId);
        spec.pathParams("first", "user-status", "second", userStatusesId);

        //---------------------------------------------------

        // Send Request and Get Response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        //---------------------------------------------------

        // Do the assertion

        // Status Code should be 200
        response.then().statusCode(200);
        Assert.assertTrue(response.asString().isEmpty());


    }

    /************************************************
     *      Delete Existing User Status by ID       *
     ************************************************/
    @Test(description = "To verify that existing user status can be deleted by Id",dependsOnMethods = {"request.user_status_service.http_request.DeleteUserStatuses.testCase5"})
    public void testCase8() {
        // Set Url
        Integer userStatusesId = GetUserStatuses.readUserStatusIdFromFile();
        System.out.println("userStatusesId = " + userStatusesId);
        spec.pathParams("first", "user-status", "second", userStatusesId);

        //---------------------------------------------------

        // Send Request and Get Response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        //---------------------------------------------------

        // Do the assertion
        //Using Soft Assertion
        SoftAssert softAssert=new SoftAssert();
        // Status Code should be 404 Not Found and response body is empty
        softAssert.assertEquals(response.statusCode(),404);
        softAssert.assertTrue(response.asString().isEmpty());


    }


}
