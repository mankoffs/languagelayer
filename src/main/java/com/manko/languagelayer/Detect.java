package com.manko.languagelayer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
@Builder(toBuilder = true)
public class Detect {
    @JsonProperty("language_code")
    String languageCode;
    @JsonProperty("language_name")
    String languageName;
    @JsonProperty("percentage")
    Integer percentage;
    @JsonProperty("reliable_result")
    boolean reliableResult;
}