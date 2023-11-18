package restServiceTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequestTest {

    private final String URI_REQRES = "https://reqres.in/api/users"; //тестовый стенд
    @Test (description = "Проверка статуса 201 (created)")
    public void postRequestTest() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name","John Week");
        data.put("job","leader");

        RestAssured
                .given()
                    .baseUri(URI_REQRES)
                    .contentType("application/json")
                    .body(data)
                    .log().all()
//выполнение запроса
                .when()
                    .post()
//выполнение необходимых проверок
                .then()
                    .log().all()
                    .statusCode(201)
                    .assertThat().contentType("application/json");
    }
}
