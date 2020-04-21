package kz.nargiza.Lwqz.models.mappers;

import kz.nargiza.Lwqz.models.dtos.CityDto;
import kz.nargiza.Lwqz.models.entities.City;
import kz.nargiza.Lwqz.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityMapper extends AbstractModelMapper<City, CityDto> {
    private ModelMapper modelMapper;

    @Autowired
    public CityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CityDto toDto(City city) {
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    public City toEntity(CityDto cityDto) {
        return modelMapper.map(cityDto, City.class);
    }

    @Override
    public List<CityDto> toDtoList(List<City> cities) {
        return cities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<City> toEntityList(List<CityDto> cityDtos) {
        return cityDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
