package com.hims.transitapi.route_details;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Direction {
    private String tag;
    private String title;
    private String name;
    private String useForUI;
    private List<Stop> stop;
}
