package com.example.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    @ApiModelProperty(required = true)
    private String firstname;

    @ApiModelProperty(required = true)
    private String lastname;

    @ApiModelProperty(value = "Link to customer resource",required = true)
    @JsonProperty("customer_url")
    private String customerurl;
}
