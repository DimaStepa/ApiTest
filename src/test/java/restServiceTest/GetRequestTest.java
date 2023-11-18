package restServiceTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractIterableAssert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class GetRequestTest {

    private final static String BASE_URI = "https://reqres.in/";
    private final static String BASE_PATH = "/api/users?page=2";

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
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .log().all()
                .body("data[0].last_name", equalTo("Bluth"));
    }

    @Test(description = "Записать все email в список")
    public void getListEmails() {
        List<String> listOfEmails = given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .extract().jsonPath().getList("data.email");
        System.out.println(listOfEmails);
    }

    @Test(description = "Рассмотрение десерелиализации")
    public void useDeserelization() {
        List<UserPojo> listOfEmails = given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .extract().jsonPath().getList("data", UserPojo.class);
        assertEquals(listOfEmails.get(0).getEmail(), "george.bluth@reqres.in");

//        assertThat(listOfEmails).extracting(UserPojo::getEmail).contains("george.bluth@reqres.in");

    }
}
