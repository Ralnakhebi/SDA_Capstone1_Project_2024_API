package request.user_service.http_request;

import base_urls.QuasparepartsBaseUrl;
import base_urls.QuasparepartsBaseUrl2;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static utilities.Authentication.getSessionId;

public class GetUser extends QuasparepartsBaseUrl2 {
    static int appId;
    static Long orgId;
    static int userId;
    static String subId;
    static String username;
    @Test()
    public void getUserInfo() {
        String url = "https://qa-gm3.quaspareparts.com/a3m/auth/userinfo";

        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .header("Cookie", "GSESSIONID="+getSessionId())
                .get(url);

        response.prettyPrint();
        JsonPath json = response.jsonPath();


        appId = json.getInt("sub_app");
        orgId = json.getLong("sub_default_org_id");
        userId = json.getInt("sub_id");
        subId = json.get("sub_default_subscription_id");
        username = json.getString("sub");
    }



    //____________________________TC01_______________________________
    //dependsOnMethods = "getUserInfo"
    @Test(dependsOnMethods = "getUserInfo",testName = "TC01",priority = 1)
    public void getUsersByAppId(){

        // Set url
        spec.pathParams("first","v1"
                , "second","application"
                ,"third",appId
                ,"fourth","user");

        Response response = given(spec).when().get("{first}/{second}/{third}/{fourth}");
        //response.prettyPrint();

        response
                .then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("message",equalTo("Principal not authorized"));
    }

    //____________________________TC02_______________________________
    //dependsOnMethods = "getUserInfo"
    @Test(dependsOnMethods = "getUserInfo",testName = "TC02",priority = 2)
    public void getUsersByOrgId(){

        spec.pathParams("first","v1"
                , "second","organization"
                ,"third",orgId
                ,"fourth","user");

        Response response = given().spec(spec).when().get("{first}/{second}/{third}/{fourth}");
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",hasItem(userId))
                .body("username",hasItem(username));
    }

    //____________________________TC03______________________________
    //dependsOnMethods = "getUserInfo"
    @Test(dependsOnMethods = "getUserInfo",testName = "TC03",priority = 3)
    public void getUesrByUserId() {

        spec.pathParams("first", "v1"
                , "second", "user"
                , "third", userId);

        Response response = given().spec(spec).when().get("{first}/{second}/{third}");
        response.prettyPrint();

        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(userId))
                .body("username", equalTo(username));
    }


}
