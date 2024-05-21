package request.organization_service.http_request;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class DeleteOrganization {

    @Test(description = "DeleteOrganizationById")
    public void TC07() {
        spec.pathParams("first", "organization","second",DeleteOrganizationIdToFile());
        //send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        // Assertion
        response
                .then()
                .assertThat()
                .statusCode(200);
        assertTrue(response.asString().isEmpty());

    }

    public static Long DeleteOrganizationIdToFile(){

        String separator = System.getProperty("file.separator");
        String fileName = System.getProperty("user.dir")+separator+"src"+separator+
                "test"+separator+"java"+separator+"request"+separator+"organization_service"+
                separator+"file"+separator+"OrganizationId.txt";
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Long.parseLong(line);
    }

}

