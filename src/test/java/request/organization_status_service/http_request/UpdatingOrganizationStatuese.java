package request.organization_status_service.http_request;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static request.organization_status_service.http_request.CreateNewOrganizationStatu.organizationId;



public class UpdatingOrganizationStatuese {

    @Test
    public void createAndUpdateOrganizationStatus() {
        // Create organization
        CreateNewOrganizationStatu createNewOrganizationStatu = new CreateNewOrganizationStatu();
        createNewOrganizationStatu.createOrganizationStatus();

        // Get the organization ID
        int organizationId = createNewOrganizationStatu.getOrganizationId();

        // Update organization
        Map<String, Object> updateRequestBody = new HashMap<>();
        updateRequestBody.put("id", organizationId);
        updateRequestBody.put("name", "Abbey Test has been updated");
        updateRequestBody.put("description", "This is a description for Abbey test updated will be deleyed");

        spec.pathParam("first", "organization-status");
        Response updateResponse = given(spec)
                .contentType("application/json")
                .body(updateRequestBody)
                .when()
                .put("/{first}");

        // Print the response
        updateResponse.prettyPrint();

        // Assertion
        updateResponse.then().statusCode(200);
        updateResponse.then().body("name", equalTo("Abbey Test has been updated"));
        updateResponse.then().body("description", equalTo("This is a description for Abbey test updated"));
        updateResponse.then().body("id", equalTo(organizationId));
    }
}



//_______________________________________________________


//public class UpdatingOrganizationStatuese {
//
//    @Test(dependsOnMethods = {"request.organization_status_service.http_request.CreateNewOrganizationStatu.createOrganizationStatus"})
//    public void updateOrganizationStatus() {
//        // _____________PUt_Using_Map______________________
////        int id = CreateNewOrganizationStatu.organizationId;
//        Map<String, Object> updateRequestBody = new HashMap<>();
//        updateRequestBody.put("id", organizationId);
//        updateRequestBody.put("name", "Abbey Test has been updated");
//        updateRequestBody.put("description", "This is a description for Abbey test updated");
//
//        spec.pathParam("first", "organization-status");
//        Response updateResponse = given(spec)
//                .contentType("application/json")
//                .body(updateRequestBody)
//                .when()
//                .put("/{first}");
//
//        // _____________Print_the_response_______________________
//        updateResponse.prettyPrint();
//
//        // _____________Assertion_______________________
//        updateResponse.then().statusCode(200);
//        updateResponse.then().body("name", equalTo("Abbey Test has been updated"));
//        updateResponse.then().body("description", equalTo("This is a description for Abbey test updated"));
//        updateResponse.then().body("id", equalTo(organizationId));
//    }
//}
//




