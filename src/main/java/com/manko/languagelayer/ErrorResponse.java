package com.manko.languagelayer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
@Builder(toBuilder = true)
public class ErrorResponse {
    @JsonProperty("success")
    boolean success;
    @JsonProperty("error")
    ErrorInfo error;
}