package com.hims.transitapi.agency;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Agency {
    @JsonProperty("title")
    private String title;
    @JsonProperty("regionTitle")
    private String regionTitle;
    @JsonProperty("shortTitle")
    private String shortTitle;
    @JsonProperty("tag")
    private String tag;
}
