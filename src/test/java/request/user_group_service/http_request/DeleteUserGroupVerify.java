package request.user_group_service.http_request;

import base_urls.QuasparepartsBaseUrl2;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class DeleteUserGroupVerify extends QuasparepartsBaseUrl2 {
    @Test(dependsOnMethods = {"request.user_group_service.http_request.CreateUserGroup.createUserGroupTest", "request.user_group_service.http_request.DeleteUserGroup.deleteUserGroupTest"})
    public void deleteUserGroupVerifyTest() {

        // Set the url
        spec.pathParams("first", "user-group", "second", CreateUserGroup.userGroupId);

        // Send request and get response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Assertions
        assertEquals(404, response.statusCode());
        assertEquals("", response.asString());
    }
}