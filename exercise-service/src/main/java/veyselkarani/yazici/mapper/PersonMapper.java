package veyselkarani.yazici.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import veyselkarani.yazici.dto.PersonDto;
import veyselkarani.yazici.repository.entity.Person;

import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toPerson(PersonDto dto);
    PersonDto toDto(Person person);
}
