package kz.nargiza.Lwqz.services;

import kz.nargiza.Lwqz.models.entities.City;
import kz.nargiza.Lwqz.models.exceptions.SystemServiceException;

import java.util.List;

public interface CityService {
    List<City> findAll();

    City findById(Long id) throws SystemServiceException;

    City findByName(String name) throws SystemServiceException;
}
