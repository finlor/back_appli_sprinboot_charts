package com.api.julien.JavelotDemo.controller;

import com.api.julien.JavelotDemo.model.City;
import com.api.julien.JavelotDemo.model.CityTemperature;
import com.api.julien.JavelotDemo.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TemperatureController {

    public final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("cities")
    public List<City> getAllCity() {
        return temperatureService.getAllCity();    }

    @GetMapping("temperature/{cityId}")
    public CityTemperature getTemperatureByCityId(@PathVariable Long cityId){
        return temperatureService.getTemperatureByCityId(cityId);
    }
}
