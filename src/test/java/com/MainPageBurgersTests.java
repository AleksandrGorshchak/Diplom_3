package com;

import com.codeborne.selenide.Configuration;
import com.po.*;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.po.MainPageBurgers.HOME_PAGE_BURGERS;
import static org.junit.Assert.assertTrue;

public class MainPageBurgersTests {

    @Before
    public void setup() {

        Configuration.startMaximized = true;
    }

    @After
    public void tearDown() {

        closeWebDriver();
    }

    @Test
    @Description("Переход в раздел Начинки")
    public void goToFillingsSectionTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickFilling();
        assertTrue("Нахождение не в разделе 'Начинки'", mainPage.isHeaderFillingVisible());
    }

    @Test
    @Description("Переход в раздел Соусы")
    public void goToSaucesSectionTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        mainPage.clickSauces();
        assertTrue("Нахождение не в разделе 'Соусы'", mainPage.isHeaderSaucesVisible());
    }

    @Test
    @Description("Переход в раздел Булки")
    public void goToBunsSectionTest() {

        MainPageBurgers mainPage = open(HOME_PAGE_BURGERS, MainPageBurgers.class);
        assertTrue(mainPage.isHeaderBunsVisible());
    }

}
