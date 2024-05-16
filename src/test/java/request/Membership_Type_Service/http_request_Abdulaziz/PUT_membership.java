package request.Membership_Type_Service.http_request_Abdulaziz;

import CaptonAPI.BaseUrl.Create_Membership_types;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PUT_membership extends Create_Membership_types {

    @Test
    public void User_Get_Company_Memberships_to_verify_Guest_membership() {

        // Set Url
        spes.pathParams("first", "membership-type");

        String payloadstr = """
                {
                    "name": "NewCompany abdulaziz Jameel",
                    "is_enabled": true
                }""";


        Response response = given(spes).body(payloadstr).when().put("{first}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .statusCode(403)
                .body("error", equalTo("Forbidden")
                        , "message", equalTo("Principal not authorized")
                        , "detail", equalTo("Principal(bo@testevolve.com) not authorized to update the membershipType of the App")
                );


    }
}
