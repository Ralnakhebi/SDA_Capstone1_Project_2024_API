package request.user_group_service.http_request;

import base_urls.QuasparepartsBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import request.user_group_service.pojo.UserGroupPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class CreateUserGroup extends QuasparepartsBaseUrl {

    public static int userGroupId;
    public static long orgId;
    static UserGroupPojo payload;

    @Test
    public void createUserGroupTest() {

        //Set the url
        //spec.pathParam("first", "user-group");

        //Set Expected Data
        String payloadStr = """
                {
                    "name": "SDA_Group1",
                    "short_name": "SDAG1",
                    "group_type_id": 1,
                    "description": "Testing POST request for creating new user group"
                }""";

        payload = convertJsonToJava(payloadStr, UserGroupPojo.class);

        //Send the request and get the response
        Response response = given(spec).body(payload).when().post("user-group");
        response.prettyPrint();

        //Assertions
        UserGroupPojo actualData = convertJsonToJava(response.asString(), UserGroupPojo.class);

        assertEquals(201, response.statusCode());
        assertEquals(payload.getName(), actualData.getName());
        assertEquals(payload.getShort_name(), actualData.getShort_name());
        assertEquals(payload.getGroup_type_id(), actualData.getGroup_type_id());
        assertEquals(payload.getDescription(), actualData.getDescription());


        userGroupId = actualData.getId();
        System.out.println("userGroupId = " + userGroupId);

        orgId=actualData.getOrganization_id();
        System.out.println("orgId = " + orgId);

    }

}
