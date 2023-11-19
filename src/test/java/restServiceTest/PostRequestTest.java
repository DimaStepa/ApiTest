package restServiceTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import pojo.UserRequest;
import pojo.CreateUserResponce;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PostRequestTest {

    //uri тестового стенда
    private static String BASE_URI = "https://reqres.in";
    private static String BASE_PATH = "/api/users";

    //Задание спецификации настроек запроса. Тоже самое можно настроить и для ответа
    private static RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri(BASE_URI)
                    .setBasePath(BASE_PATH)
                    .setContentType(ContentType.JSON)
                    .build();

    @Test (description = "Проверка статуса 201 (created). Просто выполняется тест с проверкой без записи в результов в переменную класса")
    public void postRequestTest() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name","John Week");
        data.put("job","leader");

        RestAssured
                .given()
                    .baseUri(BASE_URI)
                    .basePath(BASE_PATH)
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

    @Test (description = "Создание нового пользователя (рефактор теста)")
    public void postNewCreateUser() {
        UserRequest request = new UserRequest();
        request.setName("Alibaba");
        request.setJob("Matrica");

        CreateUserResponce responce = given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .body(request)
                .when().post()
                //Преобразовываем json в java-объект c помощью указанного класса
                .then()
                .log().all()
                .extract().as(CreateUserResponce.class);

        assertThat(responce)
                .isNotNull()
                .extracting(CreateUserResponce::getName)
                .isEqualTo(request.getName());
    }

    @Test(description = "Использование спеки запроса для отправки запроса")
    public void sendPostSpec() {
        UserRequest rq = new UserRequest();
        rq.setName("Batman");
        rq.setJob("Gotham");

        CreateUserResponce responce = given()
                .spec(REQ_SPEC)
                .body(rq)
                .when().post()
                .then().extract().as(CreateUserResponce.class);
        assertThat(responce)
                .isNotNull()
                .extracting(CreateUserResponce::getJob)
                .isEqualTo(rq.getJob());

    }
}
