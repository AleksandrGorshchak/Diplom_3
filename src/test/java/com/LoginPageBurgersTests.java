package com;

import com.codeborne.selenide.Configuration;
import com.po.ForgotPasswordPageBurgers;
import com.po.LoginPageBurgers;
import com.po.MainPageBurgers;
import com.po.RegistrationPageBurgers;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.po.MainPageBurgers.HOME_PAGE_BURGERS;
import static org.junit.Assert.assertTrue;

public class LoginPageBurgersTests {

    String name = RandomStringUtils.randomAlphabetic(10);
    String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    String password = RandomStringUtils.randomAlphabetic(10);

    @Before
    public void setup() {

        Configuration.startMaximized = true;
        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickPersonalAccountButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.clickRegister();
        RegistrationPageBurgers registrationPage = page(RegistrationPageBurgers.class);
        registrationPage.fillFormRegistration(name, email, password);
    }

    @After
    public void tearDown() {

        closeWebDriver();
    }

    @Test
    @Description("Вход по кнопке 'Войти в аккаунт' на главной странице")
    public void loginByLoginButtonOnMainPageTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickLoginButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.fillFormLogin(email, password);
        assertTrue(mainPage.isCheckoutButtonDisplayed());
    }

    @Test
    @Description("Вход через кнопку 'Личный кабинет'")
    public void loginByPersonalAccountButtonTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickPersonalAccountButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.fillFormLogin(email, password);
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
        loginPage.fillFormLogin(email, password);
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
        loginPage.fillFormLogin(email, password);
        assertTrue(mainPage.isCheckoutButtonDisplayed());
    }
}


