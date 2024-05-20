package request.user_group_type_service.http_request;

import base_urls.QuasparepartsBaseUrl2;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtilities;

import java.util.Map;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static request.user_group_type_service.http_request.AddUserGroupType.payload;
import static request.user_group_type_service.http_request.AddUserGroupType.userGroupTypeDes;

public class AddUserGroupTypeWithoutName extends QuasparepartsBaseUrl2 {

    @Test
    public void AddNewUserGroupTypeWithoutName(){

        //SET URL
        spec.pathParam("1","user-group-type");


        //-----------------------------------------------------------------------------

        //SEND EXPECTED DATA
        String expectedDataStr = """
                {
                    "id": null,
                    "description": "This Is Software Testing"
                }""";

        payload =
                ObjectMapperUtilities.convertJsonToJava(expectedDataStr, Map.class);


        userGroupTypeDes= payload.get("description").toString();



        //------------------------------------------------------------------------------

        //SEND REQUEST AND GET RESPONSE
        Response response = given(spec).body(payload).when().post("{1}");
        response.prettyPrint();


        //------------------------------------------------------------------------------

        //DO ASSERTION
        response
                .then()
                .statusCode(500)
                .body("error" , equalTo("Internal Server Error"))
                .body("detail" , equalTo(null));
    }
}
