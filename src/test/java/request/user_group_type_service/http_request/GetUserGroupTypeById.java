package request.user_group_type_service.http_request;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtilities;

import java.util.Map;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static request.user_group_type_service.http_request.AddUserGroupType.*;

public class GetUserGroupTypeById {

    @Test(priority = 1,dependsOnMethods = {"request.user_group_type_service.http_request.AddUserGroupType.AddNewUserGroupType"})
    public void GetUserGroupById(){

        //SET URL
        spec.pathParams("1","user-group-type", "2", userGroupTypeId);

        //SEND REQUEST AND GET RESPONSE
        Response response = given(spec).when().get("{1}/{2}");
        response.prettyPrint();


        response
                .then()
                .statusCode(200)
                .body("id",equalTo(userGroupTypeId))
                .body("name",equalTo(userGroupTypeName))
                .body("description" , equalTo(userGroupTypeDes));
    }
}
