package com.nexsys.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nexsys.marketplace.util.Constants;
import lombok.Data;

@Data
@JsonPropertyOrder({Constants.ID, Constants.NAME})
public class CategoryDTO {
    @JsonProperty(Constants.ID)
    private Long cId;
    @JsonProperty(Constants.NAME)
    private String title;
}
