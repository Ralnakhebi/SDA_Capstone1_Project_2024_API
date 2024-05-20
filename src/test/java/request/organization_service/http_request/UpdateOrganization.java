package request.organization_service.http_request;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class UpdateOrganization {
        @Test(description = "updateOrganizationAddressTest")
        public void TC06() {

            spec.pathParams("first", "organization");
            Map<String, Object> requestBody = new HashMap<>();

            requestBody.put("address","KSA");
            requestBody.put("id",UpdateOrganizationIdToFile());

            // Send the PUT request to update the address
            Response response = given(spec)
                    .body(requestBody)
                    .when()
                    .put("{first}");
            response.prettyPrint();

            // assertions to validate the response
            assertEquals(200, response.getStatusCode());
            assertEquals("KSA", response.jsonPath().getString("address"));
        }


        public static Long UpdateOrganizationIdToFile(){
                //   String fileName = "OrganizationId.txt";
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
