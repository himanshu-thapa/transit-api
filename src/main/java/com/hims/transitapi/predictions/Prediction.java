package com.hims.transitapi.predictions;

import lombok.Data;

@Data
public class Prediction {
    private  boolean isDeparture;
    private String vehicle;
    private boolean affectedByLayover;
    private String minutes;
    private String seconds;
    private String epochTime;


}
