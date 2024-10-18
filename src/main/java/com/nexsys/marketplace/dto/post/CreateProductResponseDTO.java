package com.nexsys.marketplace.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nexsys.marketplace.util.Constants;

public record CreateProductResponseDTO (
        @JsonProperty(Constants.ID) Long pid) {}
