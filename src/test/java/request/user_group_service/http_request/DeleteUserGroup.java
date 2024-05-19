package request.user_group_service.http_request;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class DeleteUserGroup {
    /*
Given
   url: "https://qa-gm3.quaspareparts.com/a3m/auth/api:id
When
   user send DELETE request
Then
   verify status code is 200
And
   response is like : ""
*/
    @Test(dependsOnMethods = {"request.user_group_service.http_request.CreateUserGroup.createUserGroupTest"})
    public void deleteUserGroupTest() {

        //Set the url
        spec.pathParams("first", "user-group", "second", CreateUserGroup.userGroupId);

        //Set Expected Data


        //Send the request and get the response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(200, response.statusCode());


    }

}
