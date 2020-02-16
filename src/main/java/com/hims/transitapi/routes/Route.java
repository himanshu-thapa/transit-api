package com.hims.transitapi.routes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Route {
    private String title;
    private String shortTitle;
    private String tag;
}
