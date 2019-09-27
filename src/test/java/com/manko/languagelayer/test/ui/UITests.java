package com.manko.languagelayer.test.ui;

import com.manko.languagelayer.utils.Utils;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UITests {
    private static final String BASE_URL = "https://languagelayer.com/";
    private static final String ENGLISH = "English";
    private static final String SPANISH = "Spanish";
    private static final String MATCHING = "Match: 100.00%";

    @Test
    public void userCanDetectEnglishLanguage() {
        String text = Utils.readResource("/en.txt");
        MainPage mainPage = open(BASE_URL, MainPage.class);
        mainPage.enterText(text);
        mainPage.detectLanguage(ENGLISH);

        String language = mainPage.getLanguage();
        String matching = mainPage.getMatching();

        assertThat(language).isEqualTo(ENGLISH);
        assertThat(matching).isEqualTo(MATCHING);
    }

    @Test
    public void userCanDetectSpanishLanguage() {
        String text = Utils.readResource("/es.txt");
        MainPage mainPage = open(BASE_URL, MainPage.class);
        mainPage.enterText(text);
        mainPage.detectLanguage(SPANISH);

        String language = mainPage.getLanguage();
        String matching = mainPage.getMatching();

        assertThat(language).isEqualTo(SPANISH);
        assertThat(matching).isEqualTo(MATCHING);
    }
}