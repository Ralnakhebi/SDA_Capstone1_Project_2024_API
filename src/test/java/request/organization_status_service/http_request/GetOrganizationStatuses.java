package request.organization_status_service.http_request;

import org.testng.annotations.Test;
import io.restassured.response.Response;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
public class GetOrganizationStatuses {
    @Test
    public void testGetRequest() throws InterruptedException {

        // _____________Get_______________________
        spec.pathParam("first", "organization-status");
        Response response = given(spec)
                .when()
                .get("{first}");
        response.prettyPrint();
        // _____________Assertion_______________________
        assertEquals(200, response.getStatusCode());


    }

}

