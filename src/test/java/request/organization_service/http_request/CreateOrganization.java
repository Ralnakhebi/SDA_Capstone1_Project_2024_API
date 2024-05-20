package request.organization_service.http_request;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class CreateOrganization {


    @Test(description = "createOrganization")
    public void  TC03() {


        spec.pathParam("first", "organization");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Team1Company");
        requestBody.put("founder_id", 29);
        requestBody.put("short_name", "Team1");
        requestBody.put("address", "Saudi Arabia");
        requestBody.put("country_id", "SA");
        requestBody.put("phone", "966131313131311");
        requestBody.put("email", "Team1Company@gmail.com");
        requestBody.put("website", "www.Team1Company.com");
        requestBody.put("fax", "966131313131311");
        requestBody.put("status_id", 1);
        requestBody.put("currency", "SAR");

        // Send the request and get the response
        Response response = given(spec)
                .body(requestBody)
                .when()
                .post("{first}");

        response.prettyPrint();

        // Save the organization ID from the response
        writeOrganizationIdToFile( response.jsonPath().getLong("id"));

        assertEquals(201, response.getStatusCode());
        Assert.assertEquals("Team1Company", response.jsonPath().getString("name"));
        Assert.assertEquals("29",response.jsonPath().getString("founder_id"));
        Assert.assertEquals("Team1",response.jsonPath().getString("short_name"));
        Assert.assertEquals("Saudi Arabia",response.jsonPath().getString("address"));
        Assert.assertEquals("SA",response.jsonPath().getString("country_id"));
        Assert.assertEquals("966131313131311",response.jsonPath().getString("phone"));
        Assert.assertEquals("Team1Company@gmail.com",response.jsonPath().getString("email"));
        Assert.assertEquals("www.Team1Company.com",response.jsonPath().getString("website"));
        Assert.assertEquals("1",response.jsonPath().getString("status_id"));
        Assert.assertEquals("SAR",response.jsonPath().getString("currency"));


    }

    @Test(description = "createOrganizationAlreadyExists")
    public void TC04(){
        spec.pathParams("first","organization");
        // .... AlreadyExists

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Team1Company");
        requestBody.put("founder_id", 29);
        requestBody.put("short_name", "Team1");
        requestBody.put("address", "Saudi Arabia");
        requestBody.put("country_id", "SA");
        requestBody.put("phone", "966131313131311");
        requestBody.put("email", "Team1Company@gmail.com");
        requestBody.put("website", "www.Team1Company.com");
        requestBody.put("fax", "966131313131311");
        requestBody.put("status_id", 1);
        requestBody.put("currency", "SAR");

        // Send the request and get the response
        Response response = given(spec)
                .body(requestBody)
                .when()
                .post("{first}");

        response.prettyPrint();

        assertEquals(406, response.getStatusCode());
        // Assert the response body
        JsonPath jsonPath = response.jsonPath();
        assertEquals("Organization not created", jsonPath.getString("message"));
        assertEquals("Organization with given name already exists", jsonPath.getString("detail"));

    }

    @Test(description = "createOrganizationById")
    public void TC05() {
        spec.pathParams("first", "organization");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id",2030);
        requestBody.put("name", "Team1Company");
        requestBody.put("founder_id", 29);
        requestBody.put("short_name", "Team1");
        requestBody.put("address", "Saudi Arabia");
        requestBody.put("country_id", "SA");
        requestBody.put("phone", "966131313131311");
        requestBody.put("email", "Team1Company@gmail.com");
        requestBody.put("website", "www.Team1Company.com");
        requestBody.put("fax", "966131313131311");
        requestBody.put("status_id", 1);
        requestBody.put("currency", "SAR");

        // Send the request and get the response
        Response response = given(spec)
                .body(requestBody)
                .when()
                .post("/{first}");

        assertEquals(406, response.getStatusCode());
        // Assert the response body
        JsonPath jsonPath = response.jsonPath();
        assertEquals("Organization not created", jsonPath.getString("message"));
        assertEquals("Organization name can not be null or empty, organization id must be null", jsonPath.getString("detail"));

    }

    public void writeOrganizationIdToFile(Long OrganizationId){
        //String fileName = "OrganizationId.txt";
        String separator = System.getProperty("file.separator");
        String fileName = System.getProperty("user.dir")+separator+"src"+separator+
                "test"+separator+"java"+separator+"request"+separator+"organization_service"+
                separator+"file"+separator+"OrganizationId.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(OrganizationId+"");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}





