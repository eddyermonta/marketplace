package com.nexsys.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nexsys.marketplace.util.Constants;
@JsonPropertyOrder({Constants.ID, Constants.TITLE, Constants.PRICE, Constants.DESCRIPTION})
public record ProductDTO
        ( @JsonProperty(Constants.ID) Long pid,
          @JsonProperty(Constants.TITLE) String name,
          @JsonProperty(Constants.PRICE) double priceFinal,
          String description)
{}
