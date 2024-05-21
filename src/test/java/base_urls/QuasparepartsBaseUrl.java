package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static utilities.Authentication.getSessionId;


public class QuasparepartsBaseUrl {


    public static RequestSpecification spec;

    static {

        spec = new RequestSpecBuilder()
                .setBaseUri("https://qa-gm3.quaspareparts.com/a3m/auth/api")
                .setContentType(ContentType.JSON)
                .addHeader("Cookie", "GSESSIONID=" + getSessionId())
                .build();

        }
    }
    
