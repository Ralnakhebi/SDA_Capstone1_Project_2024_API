package request.Country_Service.http_request;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Get_country_Request {

    @Test
    public void  getcountryTest(){
        // _____________Get_______________________
        spec.pathParam("first", "country");
        Response response = given(spec)
                .when()
                .get("{first}");
        response.prettyPrint();
        // _____________Assertion_______________________
        assertEquals(200, response.getStatusCode());


    }
}
