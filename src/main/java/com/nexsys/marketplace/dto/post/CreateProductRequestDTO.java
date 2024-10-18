package com.nexsys.marketplace.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nexsys.marketplace.util.Constants;

import java.util.List;


@JsonPropertyOrder({Constants.TITLE, Constants.PRICE, Constants.DESCRIPTION, Constants.CATEGORY_ID_JSON, Constants.IMAGES})
public record CreateProductRequestDTO(
        @JsonProperty(Constants.TITLE) String name,
        @JsonProperty(Constants.PRICE) double priceFinal,
        String description,
        @JsonProperty(Constants.CATEGORY_ID_JSON)Long categoryId,
        @JsonProperty(Constants.IMAGES) List<String> imageUrl
) { }
