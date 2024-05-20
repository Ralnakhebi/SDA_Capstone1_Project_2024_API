package request.user_group_type_service.http_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetAllUserGroupType {


    @Test
    public void getAllUserGroupType(){

        //SET URL
        spec.pathParam("1","user-group-type");

        //SEND REQUEST AND GET RESPONSE
        Response response = given(spec).when().get("{1}");
        response.prettyPrint();

        //DO ASSERTION
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
                 int idSize = response.jsonPath().getList("id").size();
                 System.out.println("idSize = " + idSize);
                 Assert.assertTrue(idSize>0);
    }
}
