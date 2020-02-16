package com.hims.transitapi.predictions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Predictions {
    private String agencyTitle;
    private String routeTag;
    private String routeTitle;
    private String stopTitle;
    private String stopTag;
    private DirectionInPrediction direction;
}
