package restServiceTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequestTest {

    private final String BASE_URI = "https://reqres.in/";

    @Test(description = "использовать локальный мок для выполнения запроса GET")
    public void getResponce200statusMyMock() {
        Response response = RestAssured
                .get("http://localhost:8084/api/v1/accounts/list")
                .andReturn();
        response.prettyPrint();
    }

    @Test(description = "Вызвать GET-запрос для https://reqres.in/")
    public void getListUsers() {
        given()
                .baseUri(BASE_URI)
                .basePath("/api/users?page=2")
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .log().all();
    }
}
