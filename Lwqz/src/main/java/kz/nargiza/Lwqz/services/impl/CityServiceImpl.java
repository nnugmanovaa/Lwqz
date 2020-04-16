package kz.nargiza.Lwqz.services.impl;

import kz.nargiza.Lwqz.models.entities.City;
import kz.nargiza.Lwqz.models.errors.ErrorCode;
import kz.nargiza.Lwqz.models.exceptions.SystemServiceException;
import kz.nargiza.Lwqz.repositories.CityRepository;
import kz.nargiza.Lwqz.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        return this.cityRepository.findAll();
    }

    @Override
    public City findById(Long id) throws SystemServiceException {
        return this.cityRepository.findCityById(id).orElseThrow(() -> SystemServiceException.builder()
                .message("No city with such id")
                .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                .build()
        );
    }

    @Override
    public City findByName(String name) throws SystemServiceException {
        return this.cityRepository.findCityByName(name).orElseThrow(() -> SystemServiceException.builder()
                .message("No city with such name")
                .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                .build()
        );
    }
}
