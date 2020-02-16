package com.hims.transitapi.service;

import com.hims.transitapi.cmn.ApiResponse;

public interface MainService {
    ApiResponse getAllAgencies() ;

    ApiResponse allRoutesOfAgency(String agencyTag) ;

    ApiResponse routeDetailsOfRoute(String agencyTag, String routeTag) ;

    ApiResponse predictionsOfVehicleForStop(String agencyTag, String routeTag, String stopTag);
}
