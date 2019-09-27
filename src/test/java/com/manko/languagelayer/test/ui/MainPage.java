package com.manko.languagelayer.test.ui;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement textInput = $(By.id("action_input"));
    private SelenideElement detectButton = $(byXpath("//*[@id='realtime_check']/label"));
    private SelenideElement language = $(byXpath("//*[@id='code_5']/li/header"));
    private SelenideElement matching = $(byXpath("//*[@id='code_5']/li/div"));

    public void enterText(String text) {
        textInput.setValue(text);
    }

    public void detectLanguage(String text) {
        detectButton.click();
        language.shouldHave(text(text));
    }

    public String getLanguage() {
        return language.getText();
    }

    public String getMatching() {
        return matching.getText();
    }
}