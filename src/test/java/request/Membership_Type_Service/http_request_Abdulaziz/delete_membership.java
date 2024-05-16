package request.Membership_Type_Service.http_request_Abdulaziz;

import CaptonAPI.BaseUrl.Create_Membership_types;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class delete_membership extends Create_Membership_types {

    @Test
    public void DELETE_Membership_types() {

        // Set Url
        spes.pathParams("first", "membership-type", "second", 6);


        Response response = given(spes).when().delete("{first}/{second}");
        response.prettyPrint();

        //do assertion
        response
                .then()
                .statusCode(403)
                .body("error", equalTo("Forbidden")
                        , "message", equalTo("Principal not authorized")
                        , "detail", equalTo("Principal(a) not authorized to delete the membershipType of the App")
                );


    }
}
