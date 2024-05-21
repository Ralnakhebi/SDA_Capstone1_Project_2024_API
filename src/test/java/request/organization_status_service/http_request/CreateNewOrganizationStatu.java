package request.organization_status_service.http_request;


import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.testng.AssertJUnit.assertEquals;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;

public class CreateNewOrganizationStatu {
     static int organizationId;
    @Test
    public void createOrganizationStatus() {
        // _____________Post_Using_Map______________________

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", null);
        requestBody.put("name", "Abbey Test");
        requestBody.put("description", "This is a description for Abbey test");
        //___________________________________________//
        spec.pathParam("first", "organization-status");
        Response response = given(spec)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/{first}");

        // _____________Print_the_response_______________________
        response.prettyPrint();
        // _____________Assertion_______________________
        response.then().body("name", equalTo("Abbey Test"));
        response.then().body("description", equalTo("This is a description for Abbey test"));
        response.then().body("id", notNullValue());

        // _____________Extract_the_ID_______________________
//        organizationId = response.path("id");
        organizationId = response.jsonPath().getInt("id");
    }
        public int getOrganizationId() {
            return organizationId;
        }





}
