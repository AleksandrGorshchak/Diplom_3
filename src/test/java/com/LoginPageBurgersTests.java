package com;

import com.client.UserClient;
import com.codeborne.selenide.Configuration;
import com.po.ForgotPasswordPageBurgers;
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

public class LoginPageBurgersTests {

    private InfoForCreateNewUser user;
    private UserClient userClient;

    @Before
    public void setup() {

        user = InfoForCreateNewUser.getRandomWithCorrectPassword();
        userClient = new UserClient();
        Configuration.startMaximized = true;
        userClient.create(user);
    }

    @After
    public void tearDown() {
        String accessToken = getAccessToken(user);
        userClient.delete(accessToken);
        closeWebDriver();
    }

    @Test
    @Description("Вход по кнопке 'Войти в аккаунт' на главной странице")
    public void loginByLoginButtonOnMainPageTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickLoginButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.fillFormLogin(user.email, user.password);
        assertTrue(mainPage.isCheckoutButtonDisplayed());
    }

    @Test
    @Description("Вход через кнопку 'Личный кабинет'")
    public void loginByPersonalAccountButtonTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickPersonalAccountButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.fillFormLogin(user.email, user.password);
        assertTrue(mainPage.isCheckoutButtonDisplayed());
    }

    @Test
    @Description("Вход через кнопку в форме регистрации")
    public void loginViaButtonInRegistrationFormTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickPersonalAccountButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.clickRegister();
        RegistrationPageBurgers registrationPage = page(RegistrationPageBurgers.class);
        registrationPage.clickLogin();
        loginPage.fillFormLogin(user.email, user.password);
        assertTrue(mainPage.isCheckoutButtonDisplayed());
    }

    @Test
    @Description("Вход через кнопку в форме восстановления пароля")
    public void loginViaButtonInForgotPasswordFormTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickPersonalAccountButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.clickRecoverPassword();
        ForgotPasswordPageBurgers forgotPassword = page(ForgotPasswordPageBurgers.class);
        forgotPassword.clickLogin();
        loginPage.fillFormLogin(user.email, user.password);
        assertTrue(mainPage.isCheckoutButtonDisplayed());
    }

    private String getAccessToken(InfoForCreateNewUser infoForCreateNewUser) {
        UserCredentials userCredentials = new UserCredentials(infoForCreateNewUser.email, infoForCreateNewUser.password);
        ValidatableResponse response = userClient.login(userCredentials);
        return response.extract().path("accessToken");
    }
}


