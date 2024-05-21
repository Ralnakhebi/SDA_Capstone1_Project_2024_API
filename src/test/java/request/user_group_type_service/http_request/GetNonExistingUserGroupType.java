package request.user_group_type_service.http_request;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static request.user_group_type_service.http_request.AddUserGroupType.userGroupTypeId;

public class GetNonExistingUserGroupType {

    @Test
    public void GetNonExistingUserGroupTypeById(){

        //SET URL
        spec.pathParams("1","user-group-type", "2" , userGroupTypeId);


        //SEND REQUEST AND GET RESPONSE
        Response response = given(spec).when().get("{1}/{2}");
        response.prettyPrint();


        //DO ASSERTION
        response
                .then()
                .statusCode(404);
    }
}
