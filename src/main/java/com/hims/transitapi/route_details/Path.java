package com.hims.transitapi.route_details;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Path {
    private List<Point> point;
}
