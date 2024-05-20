package request.user_service.http_request;

import base_urls.QuasparepartsBaseUrl;
import base_urls.QuasparepartsBaseUrl2;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.collections4.Get;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static request.user_service.http_request.GetUser.orgId;
import static request.user_service.http_request.UpdateUser.readNewUserIdFromFile;

public class DeleteUser extends QuasparepartsBaseUrl2 {
    public Integer newUserId = readNewUserIdFromFile();
    //______________________TC09______________________
    //dependsOnMethods={"request.user_service.http_request.GetUser.getUserInfo","request.user_service.http_request.CreateUser.addNewUser"},
    @Test(dependsOnMethods = {"request.user_service.http_request.GetUser.getUserInfo"
                             ,"request.user_service.http_request.UpdateUser.updateTheNewUser"},testName = "TC09",priority = 1)
    public void deleteUserFromOrg(){
        // Set Url
        spec.pathParams("first","v1"
        ,"second","organization"
        ,"third",orgId
        ,"fourth","user"
        ,"fifth",newUserId);

        // Expected response
        String ExpectedBody = "";

        // Send request and get response
        Response response = given(spec).when().delete("{first}/{second}/{third}/{fourth}/{fifth}");
        response.prettyPrint();

        // Assertion
        assertEquals(response.statusCode(),200);
        assertEquals(response.asString(),ExpectedBody);

    }

    //______________________TC10______________________
    @Test(testName = "TC10",priority = 2)
    public void deleteAnyUser(){
        // Set url
        spec.pathParams("first","v1"
        ,"second","user"
        ,"third",newUserId);

        //Send request and get response
        Response response = given(spec).when().delete("{first}/{second}/{third}");
        response.prettyPrint();

        // Assertion
        response.then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("detail",containsString("not authorized to delete user"));
    }
}
