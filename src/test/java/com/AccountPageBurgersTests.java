package com;

import com.codeborne.selenide.Configuration;
import com.po.LoginPageBurgers;
import com.po.MainPageBurgers;
import com.po.AccountPageBurgers;
import com.po.RegistrationPageBurgers;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.po.MainPageBurgers.HOME_PAGE_BURGERS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountPageBurgersTests {

    static String name = RandomStringUtils.randomAlphabetic(10);
    static String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    static String password = RandomStringUtils.randomAlphabetic(10);

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
    @Description("Переход в Личный кабинет")
    public void goToPersonalAccountTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickLoginButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.fillFormLogin(email, password);
        mainPage.clickPersonalAccountButton();
        AccountPageBurgers accountProfile = page(AccountPageBurgers.class);
        assertTrue(accountProfile.isExitDisplayed());
    }

    @Test
    @Description("Переход в Конструктор")
    public void goToConstructorFromPersonalAccountTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickLoginButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.fillFormLogin(email, password);
        mainPage.clickPersonalAccountButton();
        AccountPageBurgers accountProfile = page(AccountPageBurgers.class);
        accountProfile.clickConstructorButton();
        assertTrue(mainPage.isCheckoutButtonDisplayed());
    }

    @Test
    @Description("Переход по клику на логотип Stellar Burgers")
    public void goToMainPageByClickOnLogoTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickLoginButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.fillFormLogin(email, password);
        mainPage.clickPersonalAccountButton();
        AccountPageBurgers accountProfile = page(AccountPageBurgers.class);
        accountProfile.clickLogoStellar();
        assertEquals(mainPage.getUrl(), HOME_PAGE_BURGERS);
    }

    @Test
    @Description("При клике на кнопку Выход происходит выход из профиля")
    public void clickByExitButtonFromAccountProfileTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickLoginButton();
        LoginPageBurgers loginPage = page(LoginPageBurgers.class);
        loginPage.fillFormLogin(email, password);
        mainPage.clickPersonalAccountButton();
        AccountPageBurgers accountProfile = page(AccountPageBurgers.class);
        accountProfile.clickExit();
        assertTrue(loginPage.isLoginButtonDisplayed());
    }
}
