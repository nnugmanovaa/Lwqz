package kz.nargiza.Lwqz.controllers;

import kz.nargiza.Lwqz.models.entities.City;
import kz.nargiza.Lwqz.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cities")
@AllArgsConstructor
public class CityController {

    private CityService cityService;

    @GetMapping
    public List<City> loadAll(){
        return this.cityService.findAll();
    }

    @GetMapping("{id}")
    public City getById(@PathVariable Long id){
        return this.cityService.findById(id);
    }

    @GetMapping("{name}")
    public City getByCityName(@PathVariable String name){
        return this.cityService.findByName(name);
    }
}
