package request.membership_service.http_request;

import base_urls.QuasparepartsBaseUrl2;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;
import static utilities.Authentication.getSessionId;

public class GetMembership  extends QuasparepartsBaseUrl2 {
    public Long orgId;
    public int appId;
    public int userId;
    public String username;

    @Test
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
        username = json.getString("sub");
    }

    //_________________________TC01____________________________
    @Test(dependsOnMethods = "getUserInfo",testName = "TC01")
    public void getMembershipsByAppId(){
        // Set url
        spec.pathParams("first","v1"
                ,"second","application"
                ,"third",appId
                ,"fourth","membership");

        // Send request and get response
        Response response = given(spec).when().get("{first}/{second}/{third}/{fourth}");
        response.prettyPrint();

        // Assertion
        response
                .then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .body("detail",equalTo("Principal("+username+") not authorized to fetch memberships"));
    }
    //_________________________TC02____________________________
    @Test(testName = "TC02")
    public void getAllMemberships(){
        // Set url
        spec.pathParams("first","v1"
                ,"second","membership");

        // Send request and get response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();

        List<String> ids = json.getList("findAll{it.id>0}.id");
        assertTrue(ids.size()>0);

        List<String> appNames = json.getList("findAll{it.id>0}.app_name");
        assertTrue(appNames.size()>0);

        List<String> userIds = json.getList("findAll{it.id>0}.user_id");
        assertTrue(userIds.size()>0);

        List<String> usernames = json.getList("findAll{it.id>0}.username");
        assertTrue(usernames.size()>0);

    }

    //_________________________TC03____________________________
    @Test(dependsOnMethods = "getUserInfo",testName = "TC03")
    public void getMembershipsByUserId(){
        // Set url
        spec.pathParams("first","v1"
                 ,"second","user"
                          ,"third",userId
                          ,"fourth","application"
                          ,"fifth",appId
                          ,"sixth","membership");

        // Send request and get response
        Response response = given(spec).when().get("{first}/{second}/{third}/{fourth}/{fifth}/{sixth}");
        response.prettyPrint();

        // Assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();

        List<String> ids = json.getList("findAll{it.id>0}.id");
        assertTrue(ids.size()>0);

        List<String> appIds = json.getList("findAll{it.id>0}.app_id");
        assertTrue(appIds.size()>0);

        List<String> userIds = json.getList("findAll{it.id>0}.user_id");
        assertTrue(userIds.size()>0);


    }

    //_________________________TC04____________________________
    @Test(dependsOnMethods = "getUserInfo",testName = "TC04")
    public void getUserMembership(){
        // Set url
        spec.pathParams("first","v1"
                ,"second","user"
                ,"third",userId
                ,"fourth","membership");

        // Send request and get response
        Response response = given(spec).when().get("{first}/{second}/{third}/{fourth}");
        response.prettyPrint();

        // Assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
        JsonPath json = response.jsonPath();

        List<String> ids = json.getList("findAll{it.id>0}.id");
        assertTrue(ids.size()>0);

        List<String> appIds = json.getList("findAll{it.id>0}.app_id");
        assertTrue(appIds.size()>0);

        List<String> userIds = json.getList("findAll{it.id>0}.user_id");
        assertTrue(userIds.size()>0);
    }
}
