package com.hims.transitapi.pojo.agency;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Agency {
    private String title;
    private String regionTitle;
    private String shortTitle;
    private String tag;
}
