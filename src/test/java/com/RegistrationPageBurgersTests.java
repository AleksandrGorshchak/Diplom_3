package com;

import com.client.UserClient;
import com.codeborne.selenide.Configuration;
import com.po.LoginPageBurgers;
import com.po.MainPageBurgers;
import com.po.RegistrationPageBurgers;
import com.info.InfoForCreateNewUser;
import com.info.UserCredentials;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.po.MainPageBurgers.HOME_PAGE_BURGERS;
import static org.junit.Assert.assertTrue;

public class RegistrationPageBurgersTests {

    private UserClient userClient;
    private String accessToken;

    @Before
    public void setup() {
        userClient = new UserClient();
        Configuration.startMaximized = true;
    }

    @After
    public void tearDown() {
        userClient.delete(accessToken);
        closeWebDriver();
    }

    @Test
    @Description("Успешная регистрация пользователя")
    public void successfulUserRegistrationTest() {

        InfoForCreateNewUser randomWithCorrectPassword = InfoForCreateNewUser.getRandomWithCorrectPassword();
        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickPersonalAccountButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.clickRegister();
        RegistrationPageBurgers registrationPage = page(RegistrationPageBurgers.class);
        registrationPage.fillFormRegistration(randomWithCorrectPassword);
        accessToken = getAccessToken(randomWithCorrectPassword);
        assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    @Description("Неудачная регистрация юзера с пятизначным паролем")
    public void unsuccessfulUserRegistrationWithPasswordFiveCharactersTest() {

        InfoForCreateNewUser randomWithNotCorrectPassword = InfoForCreateNewUser.getRandomWithNotCorrectPassword();
        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickPersonalAccountButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.clickRegister();
        RegistrationPageBurgers registrationPage = page(RegistrationPageBurgers.class);
        registrationPage.fillFormRegistration(randomWithNotCorrectPassword);
        accessToken = getAccessToken(randomWithNotCorrectPassword);
        assertTrue(registrationPage.isUnCorrectPasswordDisplayed());
    }

    private String getAccessToken(InfoForCreateNewUser infoForCreateNewUser) {
        UserCredentials userCredentials = new UserCredentials(infoForCreateNewUser.email, infoForCreateNewUser.password);
        ValidatableResponse response = userClient.login(userCredentials);
        return response.extract().path("accessToken");
    }
}