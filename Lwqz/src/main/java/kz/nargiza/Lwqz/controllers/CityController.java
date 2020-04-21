package kz.nargiza.Lwqz.controllers;

import kz.nargiza.Lwqz.models.dtos.CityDto;
import kz.nargiza.Lwqz.models.entities.City;
import kz.nargiza.Lwqz.models.mappers.CityMapper;
import kz.nargiza.Lwqz.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cities")
@AllArgsConstructor
public class CityController extends BaseController {

    private CityService cityService;
    private CityMapper cityMapper;

    @GetMapping
    public ResponseEntity<?> loadAll(){
        return buildResponse(cityMapper.toDtoList(cityService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return buildResponse(cityMapper.toDto(cityService.findById(id)), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> addCity(@RequestBody @Validated CityDto cityDto){
        City city = cityMapper.toEntity(cityDto);
        city = cityService.addCity(city.getName());
        return buildResponse(cityMapper.toDto(city), HttpStatus.OK);
    }


}
