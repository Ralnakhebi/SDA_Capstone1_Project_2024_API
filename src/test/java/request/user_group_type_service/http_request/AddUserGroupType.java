package request.user_group_type_service.http_request;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtilities;

import java.util.Map;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class AddUserGroupType {

    static int userGroupTypeId;
    static String userGroupTypeName;
    static String userGroupTypeDes;
    static Map<String,Object> payload;

    @Test(dependsOnMethods = {"request.user_group_type_service.http_request.GetAllUserGroupType.getAllUserGroupType"})
    public void AddNewUserGroupType(){

        //SET URL
        spec.pathParam("1","user-group-type");


        //-----------------------------------------------------------------------------

        //SEND EXPECTED DATA
        String expectedDataStr = """
                {
                    "id": null,
                    "name": "Tester",
                    "description": "This Is Software Testing"
                }""";

        payload =
                ObjectMapperUtilities.convertJsonToJava(expectedDataStr,Map.class);

        userGroupTypeName= payload.get("name").toString();
        userGroupTypeDes= payload.get("description").toString();



        //------------------------------------------------------------------------------

        //SEND REQUEST AND GET RESPONSE
        Response response = given(spec).body(payload).when().post("{1}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();

       /* userGroupTypeId =json.getInt("id");
        userGroupTypeName = json.getString("name");
        userGroupTypeDes = json.getString("description");

        */

        //------------------------------------------------------------------------------

        //DO ASSERTION
        Map<String,Object> actualData =
                ObjectMapperUtilities.convertJsonToJava(response.asString() , Map.class);

        userGroupTypeId= (int)actualData.get("id"); //casting
        assertEquals(201,response.statusCode());
        assertEquals(payload.get("name"),actualData.get("name"));
        assertEquals(payload.get("description"),actualData.get("description"));

    }
}
