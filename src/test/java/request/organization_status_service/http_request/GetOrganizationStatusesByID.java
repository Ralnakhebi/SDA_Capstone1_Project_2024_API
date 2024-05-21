package request.organization_status_service.http_request;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class GetOrganizationStatusesByID {
    @Test
    public void testGetRequest() throws InterruptedException {

        // _____________Get_______________________
        spec.pathParams("first", "organization-status","second",2);
        Response response = given(spec)
                .when()
                .get("{first}/{second}");
        response.prettyPrint();
        // _____________Assertion_______________________
        assertEquals(200, response.getStatusCode());


    }

}
