package request.Membership_Type_Service.http_request_Abdulaziz;

import CaptonAPI.BaseUrl.Create_Membership_types;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GET_company_membership extends Create_Membership_types {

    @Test
    public void User_Get_Company_Memberships_to_verify_company_membership() {

        // Set Url
        spes.pathParams("first", "membership-type");


        // Send Request and get response
        Response response = given(spes).when().get("{first}");
        response.prettyPrint();
        // Do assertions
        response
                .then()
                .statusCode(200)
                .body("[0].name", equalTo("Company Membership"))
                .body("[0].default_role_id", equalTo(5))
        ;
    }
}
