package com.hims.transitapi;

import com.hims.transitapi.agency.AgencyWrapper;
import com.hims.transitapi.cmn.ApiResponse;
import com.hims.transitapi.predictions.PredictionsWrapper;
import com.hims.transitapi.route_details.RouteConfigWrapper;
import com.hims.transitapi.routes.RouteWrapper;

public interface MainService {
    ApiResponse getAllAgencies() ;

    ApiResponse allRoutesOfAgency(String agencyTag) ;

    ApiResponse routeDetailsOfRoute(String agencyTag, String routeTag) ;

    ApiResponse predictionsOfVehicleForStop(String agencyTag, String routeTag, String stopTag);
}
