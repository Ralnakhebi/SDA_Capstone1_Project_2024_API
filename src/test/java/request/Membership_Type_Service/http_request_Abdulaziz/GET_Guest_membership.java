package request.Membership_Type_Service.http_request;

import base_urls.QuasparepartsBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GET_Guest_membership extends QuasparepartsBaseUrl {

    @Test
    public void User_Get_Company_Memberships_to_verify_Guest_membership() {

        // Set Url
        spec.pathParams("first", "membership-type");


        // Send Request and get response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();
        // Do assertions
        response
                .then()
                .statusCode(200)
                .body("[1].name", equalTo("Guest Membership"))
                .body("[1].default_role_id", equalTo(6))
        ;
    }
}
