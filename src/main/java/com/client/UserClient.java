package com.client;

import com.info.InfoForCreateNewUser;
import com.info.UserCredentials;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends RestAssuredClient {

    final static private String USER_PATH = "/api/auth/";

    @Step("Создание юзера")
    public ValidatableResponse create(InfoForCreateNewUser dataForCreateNewUser) {
        return given()
                .spec(getBaseSpec())
                .body(dataForCreateNewUser)
                .when()
                .post(USER_PATH + "register")
                .then();
    }

    @Step("Логин юзера")
    public ValidatableResponse login(UserCredentials userCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(userCredentials)
                .when()
                .post(USER_PATH + "login")
                .then();
    }

    @Step("Удаление юзера")
    public void delete(String accessToken) {
        if (accessToken == null) {
            return;
        }
        given()
                .header("Authorization", accessToken)
                .spec(getBaseSpec())
                .when()
                .delete(USER_PATH + "user")
                .then()
                .statusCode(202);
    }
}