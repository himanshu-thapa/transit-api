package com.hims.transitapi.predictions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DirectionInPrediction {
    private String title;
    private List<Prediction> prediction;
}
