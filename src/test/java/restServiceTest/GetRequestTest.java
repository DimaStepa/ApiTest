package restServiceTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetRequestTest {

    @Test
    public void getResponce200status() {
        Response response = RestAssured
                .get("http://localhost:8084/api/v1/accounts/list")
                .andReturn();
        response.prettyPrint();
    }

}
