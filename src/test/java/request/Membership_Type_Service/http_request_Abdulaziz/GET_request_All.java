package request.Membership_Type_Service.http_request_Abdulaziz;

import CaptonAPI.BaseUrl.Create_Membership_types;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

public class GET_request_All extends Create_Membership_types {

    @Test
    public void GET_All_membership_types() {
        //Set the Url

        spes.pathParams("first", "membership-type");


        // Send Request and get response
        Response response = given(spes).when().get("{first}");
        response.prettyPrint();
        // Do assertions
        response
                .then()
                .statusCode(200)
                .body("name", hasItems("Company Membership", "Guest Membership"))
                .body("short_name", hasItems("Comp. Membr.", "Guest Membr."))
                .body("is_individual_plan", hasItems(false, true))
                .body("default_role_id", hasItems(5, 6))
                .body("subscription_type_id", hasItems(5, 6))
                .body("seat_quota", hasItem(100))
                .body("app_id", hasItem(2))
                .body("updated_by", hasItem(1))
        ;
    }
}
