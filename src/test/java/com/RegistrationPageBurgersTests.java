package com;

import com.codeborne.selenide.Configuration;
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

public class RegistrationPageBurgersTests {

    String name = RandomStringUtils.randomAlphabetic(10);
    String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    String passwordCorrectWithSixCharacters = RandomStringUtils.randomAlphabetic(6);
    String passwordUnCorrectWithFiveCharacters = RandomStringUtils.randomAlphabetic(5);

    @Before
    public void setup() {
        Configuration.startMaximized = true;
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    @Description("Успешная регистрация пользователя")
    public void successfulUserRegistrationTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickPersonalAccountButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.clickRegister();
        RegistrationPageBurgers registrationPage = page(RegistrationPageBurgers.class);
        registrationPage.fillFormRegistration(name, email, passwordCorrectWithSixCharacters);
        assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    @Description("Неудачная регистрация юзера с пятизначным паролем")
    public void unsuccessfulUserRegistrationWithPasswordFiveCharactersTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickPersonalAccountButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.clickRegister();
        RegistrationPageBurgers registrationPage = page(RegistrationPageBurgers.class);
        registrationPage.fillFormRegistration(name, email, passwordUnCorrectWithFiveCharacters);
        assertTrue(registrationPage.isUnCorrectPasswordDisplayed());
    }
}