package veyselkarani.yazici.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import veyselkarani.yazici.dto.CreateOrUpdatePersonDto;
import veyselkarani.yazici.repository.entity.Person;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CreateOrUpdatePersonMapper {
    CreateOrUpdatePersonMapper INSTANCE = Mappers.getMapper(CreateOrUpdatePersonMapper.class);

    Person toPerson(CreateOrUpdatePersonDto dto);
    CreateOrUpdatePersonDto toDto(Person person);
}
