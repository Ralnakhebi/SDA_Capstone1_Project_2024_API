package request.Role_Service;

import base_urls.QuasparepartsBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get_All_Roles extends QuasparepartsBaseUrl {
    @Test
    public void get_All_Roles_by_Organization() {
        spec.pathParams("first", "application"
                , "second", 2
                , "third", "organization"
                , "forth", "1716152660399179"
                , "fifth", "role");
        // Send GET request to retrieve user inf

        Response response = given(spec).when().get("{first}/{second}/{third}/{forth}/{fifth}");

        // Print response body
        response.prettyPrint();

        //Assert response status code is 200
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void get_All_Roles_by_Application() {
        spec.pathParams("first", "application"
                , "second", 2
                , "third", "role");
        // Send GET request to retrieve user inf

        Response response = given(spec).when().get("{first}/{second}/{third}");

        // Print response body
        response.prettyPrint();

        //Assert response status code is 200
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void get_All_Roles_of_The_Subscription() {
        spec.pathParam("first", "role");
        // Send GET request to retrieve user inf

        Response response = given(spec).when().get("{first}");

        // Print response body
        response.prettyPrint();

        //Assert response status code is 200
        assertEquals(response.statusCode(), 200);
    }
}
