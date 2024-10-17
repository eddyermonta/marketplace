package com.nexsys.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nexsys.marketplace.util.Constants;
import lombok.Data;
@JsonPropertyOrder({Constants.ID, Constants.TITLE, Constants.PRICE, Constants.DESCRIPTION})
@Data
public class ProductDTO {
    @JsonProperty(Constants.ID)
    private Long pid;
    @JsonProperty(Constants.TITLE)
    private String name;
    @JsonProperty(Constants.PRICE)
    private double priceFinal;
    private String description;
}
