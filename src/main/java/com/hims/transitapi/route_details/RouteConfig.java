package com.hims.transitapi.route_details;

import lombok.Data;

import java.util.List;

@Data
public class RouteConfig {
    private String tag;
    private String title;
    private String color;
    private String oppositeColor;
    private String latMin;
    private String latMax;
    private String lonMin;
    private String lonMax;

    private List<Direction> direction;
    private List<Stop> stop;
    private List<Path> path;

}
