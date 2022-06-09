package com.po;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exist;

public class AccountPageBurgers {

    //локатор текста "Выход"
    @FindBy(how = How.XPATH,using = "//*[contains(text(),'Выход')]")
    private SelenideElement exit;
    //локатор кнопки "Конструктор"
    @FindBy(how = How.LINK_TEXT,using = "Конструктор")
    private SelenideElement constructorButton;
    //Локатор логотипа
    @FindBy(how = How.XPATH,using = "//*[@id=\"root\"]/div/header/nav/div")
    private SelenideElement logoStellar;
    //метод клика по тексту "Выход"
    @Step("Клик по тексту \"Выход\"")
    public void clickExit() {
        exit.click();
    }
    //метод нахождения текста "Выход"
    public boolean isExitDisplayed() {
        exit.shouldBe(exist);
        return exit.isDisplayed();
    }
    //метод клика по кнопке "Конструктор"
    @Step("Клик по кнопке \"Конструктор\"")
    public void clickConstructorButton() {
        constructorButton.shouldBe(exist);
        constructorButton.click();
    }
    //метод клика на логотип
    public void clickLogoStellar() {
        logoStellar.click();
    }
}