package client_adapter_http;

import client_adapter_http.dto.*;
import client_adapter_http.helper.Helper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import pojo.CreateUserResponce;

import java.util.Arrays;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class GeneralTest {

    @Test
    public void authorizationTest() {
        var uuid = UUID.randomUUID();

        RecipientDto recipientDto = new RecipientDto();
        recipientDto.setType("MSISDN");
        recipientDto.setValue("79057098282");

        FailoverDto failoverDto = new FailoverDto();
        failoverDto.setTll("300");
        failoverDto.setConditionStatus("DELIVERED");

        ScenarioDto scenarioDto = new ScenarioDto();
        scenarioDto.setChannel(ChannelList.SMS);
        scenarioDto.setRecipient(recipientDto);
        scenarioDto.setSender("senderTech");
        scenarioDto.setText("текстовое сообщение");
        scenarioDto.setFailover(failoverDto);

        ClientRequestDto clientRequestDto = new ClientRequestDto();
        clientRequestDto.setScenario(scenarioDto);
        clientRequestDto.setTrackdata(null);
        clientRequestDto.setCallback("http://callback");

        ResponceDto rq = given()
                .baseUri(new Helper().getURI())
                .contentType(ContentType.JSON)
                .log().all()
                .body(clientRequestDto)
                .when().post()
                //Преобразовываем json в java-объект c помощью указанного класса
                .then()
                .log().all()
                .extract().as(ResponceDto.class);

        System.out.println(rq.getState());
    }
}
