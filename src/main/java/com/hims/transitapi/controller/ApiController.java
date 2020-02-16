package com.hims.transitapi.controller;

import com.hims.transitapi.cmn.ApiResponse;
import com.hims.transitapi.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    private MainService service;

    @Autowired
    public ApiController(MainService service) {
        this.service = service;
    }

    @GetMapping("/agency/all")
    public ApiResponse allAgencies() {
        return this.service.getAllAgencies();
    }

    @GetMapping("/agency/routes")
    public ApiResponse allRoutesOfAgency(@RequestParam String agencyTag) {
        return this.service.allRoutesOfAgency(agencyTag);
    }

    @GetMapping("/agency/route-details")
    public ApiResponse routeDetailsPerRouteOfAgency(@RequestParam String agencyTag, @RequestParam String routeTag) {
        return this.service.routeDetailsOfRoute(agencyTag, routeTag);
    }

    @GetMapping("/agency/stop/predictions")
    public ApiResponse timePredictionPerAgencyOfRouteForStop(@RequestParam String agencyTag, @RequestParam String routeTag,
                                                             @RequestParam String stopTag) {
        return this.service.predictionsOfVehicleForStop(agencyTag, routeTag, stopTag);
    }
}
