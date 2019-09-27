package com.manko.languagelayer.test.api;


import java.util.Collections;

import com.manko.languagelayer.Detect;
import com.manko.languagelayer.DetectResponse;
import com.manko.languagelayer.ErrorResponse;
import com.manko.languagelayer.utils.Utils;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApiTests extends BaseApiTest {
    private static final int INVALID_ACCESS_KEY_CODE = 101;
    private static final int NO_QUERY_TEXT_PROVIDED_CODE = 210;
    private static final int DETECTION_FAILED_CODE = 910;
    private static final String DETECT_PATH = "/detect";
    private static final String QUERY_PARAM = "query";

    @Test
    public void shouldDetectEnglishLanguage(){
        String text = Utils.readResource("/en.txt");
        DetectResponse response = getDetectResponseByText(text);

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(buildSuccessLanguageDetect("en", "English"));
    }

    @Test
    public void shouldDetectSpanishLanguage(){
        String text = Utils.readResource("/es.txt");
        DetectResponse response = getDetectResponseByText(text);

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(buildSuccessLanguageDetect("es", "Spanish"));
    }

    @Test
    public void shouldDetectTwoLanguagesIfTextHasDifferentLanguages(){
        String text = Utils.readResource("/different.txt");
        DetectResponse response = getDetectResponseByText(text);

        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getResults().size()).isEqualTo(2);
    }

    @Test
    public void shouldNotSuccessDetectLanguageIfTextIsNumber(){
        ErrorResponse response = given().queryParam(QUERY_PARAM, 1)
                                        .when()
                                        .get(DETECT_PATH)
                                        .then().assertThat().statusCode(200).extract().body().as(ErrorResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getError().getCode()).isEqualTo(DETECTION_FAILED_CODE);
    }

    @Test
    public void shouldNotSuccessDetectLanguageIfTextIsAbsent(){
        ErrorResponse response = given().queryParam(QUERY_PARAM, "")
                                        .when()
                                        .get(DETECT_PATH)
                                        .then().assertThat().statusCode(200).extract().body().as(ErrorResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getError().getCode()).isEqualTo(NO_QUERY_TEXT_PROVIDED_CODE);
    }

    @Test
    public void shouldNotSuccessDetectLanguageIfAccessKeyIsInvalid(){
        String text = Utils.readResource("/en.txt");
        ErrorResponse response = given().queryParam(QUERY_PARAM, text)
                                        .queryParam("access_key", "invalid")
                                        .when()
                                        .get(DETECT_PATH)
                                        .then().assertThat().statusCode(200).extract().body().as(ErrorResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getError().getCode()).isEqualTo(INVALID_ACCESS_KEY_CODE);
    }


    private DetectResponse getDetectResponseByText(String text) {
        return given().queryParam(QUERY_PARAM, text)
                      .when()
                      .get(DETECT_PATH)
                      .then().assertThat().statusCode(200).extract().body().as(DetectResponse.class);
    }

    private DetectResponse buildSuccessLanguageDetect(String languageCode, String languageName) {
        Detect detectBuilder = Detect.builder()
                                     .languageCode(languageCode)
                                     .languageName(languageName)
                                     .percentage(100)
                                     .reliableResult(true).build();
        return DetectResponse.builder()
                             .success(true)
                             .results(Collections.singletonList(detectBuilder)).build();
    }
}