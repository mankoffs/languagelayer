package com.manko.languagelayer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
@Builder(toBuilder = true)
public class DetectResponse {
    @JsonProperty("success")
    boolean success;
    @JsonProperty("results")
    List<Detect> results;
}