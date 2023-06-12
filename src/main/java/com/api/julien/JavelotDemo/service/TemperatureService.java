package com.api.julien.JavelotDemo.service;

import com.api.julien.JavelotDemo.dataAccess.TemperatureDataAccessService;
import com.api.julien.JavelotDemo.model.City;
import com.api.julien.JavelotDemo.model.CityTemperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureService {

    public final TemperatureDataAccessService temperatureDataAccessService;

    @Autowired
    public TemperatureService(TemperatureDataAccessService temperatureDataAccessService) {
        this.temperatureDataAccessService = temperatureDataAccessService;}

    public List<City> getAllCity() {
        return temperatureDataAccessService.selectAllCity();
    }

    public CityTemperature getTemperatureByCityId(Long cityId){
        return temperatureDataAccessService.selectAllTemperatureByCity(cityId);
    }
}
