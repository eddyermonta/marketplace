package com.nexsys.marketplace.dto.get;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nexsys.marketplace.util.Constants;

@JsonPropertyOrder({Constants.ID, Constants.NAME})
public record CategoryResponseDTO(
        @JsonProperty(Constants.ID) Long cId,
        @JsonProperty(Constants.NAME) String title)
{}
