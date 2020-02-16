package com.hims.transitapi.service.impl;

import com.hims.transitapi.pojo.agency.AgencyWrapper;
import com.hims.transitapi.cmn.ApiResponse;
import com.hims.transitapi.pojo.predictions.PredictionsWrapper;
import com.hims.transitapi.pojo.route_details.RouteConfigWrapper;
import com.hims.transitapi.pojo.routes.RouteWrapper;
import com.hims.transitapi.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@SuppressWarnings("unchecked")
public class MainServiceImpl implements MainService {
    private static final Logger log = LoggerFactory.getLogger(MainServiceImpl.class);
    @Value("${base.uri}")
    private String uri;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ApiResponse<AgencyWrapper> getAllAgencies() {
        try {
            AgencyWrapper data = restTemplate.getForObject(uri + "agencyList", AgencyWrapper.class);
            return setResponse(data, true, "");
        } catch (RestClientException e) {
            e.printStackTrace();
            return setResponse(null, false, "Could not fetch Agencies.Please try again!");
        }
    }

    @Override
    public ApiResponse<RouteWrapper> allRoutesOfAgency(String agencyTag) {
        try {
            RouteWrapper data = restTemplate.getForObject(uri + "routeList&a=" + agencyTag, RouteWrapper.class);
            return setResponse(data, true, "");
        } catch (RestClientException e) {
            e.printStackTrace();
            return setResponse(null, false, "Could not fetch Routes.Please try again!");
        }
    }

    @Override
    public ApiResponse<RouteConfigWrapper> routeDetailsOfRoute(String agencyTag, String routeTag) {
        try {
            RouteConfigWrapper data = restTemplate.getForObject(uri + "routeConfig&a=" + agencyTag + "&r=" + routeTag, RouteConfigWrapper.class);
            return setResponse(data, true, "");
        } catch (RestClientException e) {
            e.printStackTrace();
            return setResponse(null, false, "Could not fetch Route details.Please try again!");
        }
    }

    @Override
    public ApiResponse<PredictionsWrapper> predictionsOfVehicleForStop(String agencyTag, String routeTag, String stopTag) {
        try {
            PredictionsWrapper data = restTemplate.getForObject(uri + "predictions&a=" + agencyTag + "&r=" + routeTag + "&s=" + stopTag, PredictionsWrapper.class);
            return setResponse(data, true, "");
        } catch (RestClientException e) {
            e.printStackTrace();
            return setResponse(null, false, "Could not fetch Predictions.Please try again!");
        }
    }

    private ApiResponse setResponse(Object data, boolean success, String message) {
        ApiResponse response = ApiResponse.getInstance();
        if (success) {
            response.setResponse(data);
            response.setMessage("success");
            response.setStatusCode(200);
            return response;
        } else {
            response.setResponse(null);
            response.setMessage(message);
            response.setStatusCode(400);
            return response;
        }
    }
}
