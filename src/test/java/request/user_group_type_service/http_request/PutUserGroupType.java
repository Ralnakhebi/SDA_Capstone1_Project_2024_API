package request.user_group_type_service.http_request;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtilities;

import java.util.HashMap;
import java.util.Map;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static request.user_group_type_service.http_request.AddUserGroupType.*;

public class PutUserGroupType {


    @Test(priority = 3,dependsOnMethods = {"request.user_group_type_service.http_request.AddUserGroupType.AddNewUserGroupType"})
    public void putUserGroupType(){


        //SET URL
        spec.pathParams("1", "user-group-type");

        //------------------------------------------------------------------------------

        //SEND EXPECTED DATA


        payload.replace("id",null,userGroupTypeId);
        payload.replace("name",userGroupTypeName,"Smart Tester");
        payload.replace("description",userGroupTypeDes,"This Is Software Testing Update");

        // Update the static variables to match the expected values after the update

        userGroupTypeName = "Smart Tester";
        userGroupTypeDes = "This Is Software Testing Update";


        //------------------------------------------------------------------------------


       //SEND REQUEST AND GET RESPONSE
        Response response = given(spec).body(payload).put("{1}");
        response.prettyPrint();


        //-------------------------------------------------------------------------------


        //DO ASSERTION

       Map<String,Object> actualData =
               ObjectMapperUtilities.convertJsonToJava(response.asString(),Map.class);


        // Check that the response contains the updated fields
        assertEquals(200, response.statusCode());

        // Check that the response contains the updated fields
        assertEquals(payload.get("id"), actualData.get("id"));
        assertEquals(payload.get("name"), actualData.get("name"));
        assertEquals(payload.get("description"), actualData.get("description"));

    }
}
