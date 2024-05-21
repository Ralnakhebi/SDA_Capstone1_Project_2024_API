package request.organization_service.http_request;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.instanceOf;
import static org.testng.Assert.assertEquals;


public class GetOrganization {


    @Test(description = "GetOrganizationByApplication")
    void TC01() {
        spec.pathParams("first", "application", "second", "2", "third", "organization");

        Response response = given(spec)
                .when()
                .get("{first}/{second}/{third}")
                .then()
                .extract()
                .response();
       // response.prettyPrint();
        // Assert response status code
        assertEquals(200, response.statusCode());
        List<Map<String, Object>> data = response.jsonPath().getList("");

        for (Map<String, Object> item : data) {
            assertThat(item, hasKey("id"));
            assertThat(item, hasKey("name"));
            assertThat(item, hasKey("founder_id"));

            // "short_name" key is optional, so check only if it exists
            if (item.containsKey("short_name")) {
                assertThat(item.get("short_name"), instanceOf(String.class));


            }
        }
    }

        @Test(description = "getOrganizationById")
        public void TC02 () {

            spec.pathParams("first", "organization", "2", readOrganizationIdToFile());

            Response response = given(spec)
                    .get("{first}/{2}");
           // response.prettyPrint();
            assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals("Team1Company", response.jsonPath().getString("name"));
            Assert.assertEquals("29", response.jsonPath().getString("founder_id"));
            Assert.assertEquals("Team1", response.jsonPath().getString("short_name"));
            Assert.assertEquals("Saudi Arabia", response.jsonPath().getString("address"));
            Assert.assertEquals("SA", response.jsonPath().getString("country_id"));
            Assert.assertEquals("966131313131311", response.jsonPath().getString("phone"));
            Assert.assertEquals("Team1Company@gmail.com", response.jsonPath().getString("email"));
            Assert.assertEquals("www.Team1Company.com", response.jsonPath().getString("website"));
            Assert.assertEquals("1", response.jsonPath().getString("status_id"));
            Assert.assertEquals("SAR", response.jsonPath().getString("currency"));

        }

        public static Long readOrganizationIdToFile () {
            String separator = System.getProperty("file.separator");
            String fileName = System.getProperty("user.dir") + separator + "src" + separator +
                    "test" + separator + "java" + separator + "request" + separator + "organization_service" +
                    separator + "file" + separator + "OrganizationId.txt";
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





