package Country_Service.http_request;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Get_CountryBy_ID {

    @Test
    public void  getcountryTest(){
        // _____________Get_______________________
        spec.pathParams("first", "country","second","SA");
        Response response = given(spec)
                .when()
                .get("{first}/{second}");
        response.prettyPrint();
        // _____________Assertion_______________________
        assertEquals(200, response.getStatusCode());



    }
}
