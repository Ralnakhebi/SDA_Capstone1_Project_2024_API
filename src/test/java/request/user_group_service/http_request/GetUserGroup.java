package request.user_group_service.http_request;

import base_urls.QuasparepartsBaseUrl;
import base_urls.QuasparepartsBaseUrl2;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import request.user_group_service.pojo.UserGroupPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static request.user_group_service.http_request.CreateUserGroup.orgId;
import static request.user_group_service.http_request.CreateUserGroup.userGroupId;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class GetUserGroup extends QuasparepartsBaseUrl2 {

    /*
   Given
       url: "https://qa-gm3.quaspareparts.com/a3m/auth/api:id

   When
       user send GET request
   Then
       verify status code is 200
   And
       response is like :
       {
    "id": 2202,
    "name": "SDA_Group1",
    "short_name": "SDAG1",
    "description": "Testing POST request for creating new user group",
    "group_type_id": 1,
    "group_type": {
        "id": 1,
        "name": "Department",
        "description": "List All Department, and not authorized to access any the application"
    },
    "organization_id": 1716027192963795
    }

    */
    @Test(dependsOnMethods = {"request.user_group_service.http_request.CreateUserGroup.createUserGroupTest"})
    public void getUserGroup() {

        //Set the url
        //spec.pathParams("first", "user-group", "second", userGroupId);

        //Set Expected Data

        UserGroupPojo expectedData = CreateUserGroup.payload;
        expectedData.setId(userGroupId);
        expectedData.setOrganization_id(orgId);


        //Send the request and get the response
        Response response = given(spec).when().get("user-group/"+userGroupId);
        response.prettyPrint();


        UserGroupPojo actualData = convertJsonToJava(response.asString(), UserGroupPojo.class);

        System.out.println("actualData.getOrganization_id() = " + actualData.getOrganization_id());
        //Assertions
        assertEquals(200, response.statusCode());
        assertEquals(CreateUserGroup.userGroupId, actualData.getId());
        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getShort_name(), actualData.getShort_name());
        assertEquals(expectedData.getDescription(), actualData.getDescription());
        assertEquals(expectedData.getGroup_type_id(), actualData.getGroup_type_id());
        assertEquals(expectedData.getOrganization_id(), actualData.getOrganization_id());


    }
}
