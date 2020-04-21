package kz.nargiza.Lwqz.models.mappers;

import kz.nargiza.Lwqz.models.dtos.RoleDto;
import kz.nargiza.Lwqz.models.entities.Role;
import kz.nargiza.Lwqz.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper{
    private ModelMapper modelMapper;

    @Autowired
    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public RoleDto toDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }


    public Role toEntity(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }


    public Set<RoleDto> toDtoList(Set<Role> roles) {
        return roles.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }


    public Set<Role> toEntityList(Set<RoleDto> roleDtos) {
        return roleDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}
