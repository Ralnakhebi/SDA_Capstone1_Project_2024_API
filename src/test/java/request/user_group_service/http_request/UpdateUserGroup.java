package request.user_group_service.http_request;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import request.user_group_service.pojo.UserGroupPojo;

import static base_urls.QuasparepartsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static request.user_group_service.http_request.CreateUserGroup.orgId;
import static request.user_group_service.http_request.CreateUserGroup.userGroupId;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class UpdateUserGroup {
      /*
   Given
       1) https://qa-gm3.quaspareparts.com/a3m/auth/api

       body:
       2) {
            "id": {{new_usergroup_Id}},
            "name": "SDA_Group100",
            "short_name": "SDAG100",
            "description": "Testing POST request for creating new user group",
            "group_type_id": 1,
            "organization_id": 1715291772802536,
            "roles": []
        }
   When
       I send PUT Request to the Url
   Then
       Status code is 200
       And response body should be like
       {
            "id": 1945,
            "name": "SDA_Group100",
            "short_name": "SDAG100",
            "description": "Testing POST request for creating new user group",
            "group_type_id": 1,
            "organization_id": 1715291772802536,
            "roles": []
        }
*/

    @Test(dependsOnMethods = {"request.user_group_service.http_request.CreateUserGroup.createUserGroupTest"})
    public void updateUserGroupTest() {

        //Set the url
        spec.pathParam("first", "user-group");

        //Set Expected Data
        UserGroupPojo expectedData = CreateUserGroup.payload;
        expectedData.setId(userGroupId);
        expectedData.setOrganization_id(orgId);


        //Send the request and get the response
        Response response = given(spec).body(expectedData).when().put("{first}");
        response.prettyPrint();


        //Do assertion
        UserGroupPojo actualData = convertJsonToJava(response.asString(), UserGroupPojo.class);


        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getShort_name(), actualData.getShort_name());
        assertEquals(expectedData.getDescription(), actualData.getDescription());

    }
}
