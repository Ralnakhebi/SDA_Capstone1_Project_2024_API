package request.organization_status_service.http_request;

import org.testng.annotations.Test;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static request.organization_status_service.http_request.CreateNewOrganizationStatu.organizationId;

public class DeletteOrganizationStatus {

    @Test
    public void deleteOrganizationStatus() {
        // _____________Delete______________________

        spec.pathParams("first", "organization-status" ,"second",organizationId);
        // _____________Delete_the_organization_status_______________________
        given(spec)
                .when()
                .delete("/{first}/{second}");
        // _____________Assertion_______________________
        given(spec)
                .when()
                .get("/{first}/{second}")
                .then()
                .statusCode(404);

    }


}
