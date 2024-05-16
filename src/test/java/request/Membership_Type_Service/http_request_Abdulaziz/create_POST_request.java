package request.Membership_Type_Service.http_request;

import base_urls.QuasparepartsBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class create_POST_request extends QuasparepartsBaseUrl {

    @Test
    public void Create_Membership_types() {
        //Set the Url

        spec.pathParams("first", "membership-type");

        //Set the expected data(Payload) --> Preparing expected data as String is not recommended. Because we can not extract specific field from String body for assertion.

        String payload = """
                {
                    "name": "NewCompany abdulaziz",
                    "is_enabled": true
                }""";


        Response response = given(spec)
                .body(payload)
                .when()
                .post("{first}");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(403)
                .body("error", equalTo("Forbidden")
                        , "message", equalTo("Principal not authorized")
                        , "detail", equalTo("Principal(bo@testevolve.com) not authorized to create new membershipType for the App")
                );

    }
}
