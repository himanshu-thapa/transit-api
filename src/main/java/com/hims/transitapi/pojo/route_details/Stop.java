package com.hims.transitapi.pojo.route_details;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stop {
    private Long stopId;
    private String tag;
    private String title;
    private String shortTitle;
    private String lat;
    private String lon;
}
