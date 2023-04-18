package veyselkarani.yazici.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import veyselkarani.yazici.dto.PersonDto;
import veyselkarani.yazici.repository.entity.Person;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-18T22:41:32+0300",
    comments = "version: 1.5.4.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person toPerson(PersonDto dto) {
        if ( dto == null ) {
            return null;
        }

        Person.PersonBuilder person = Person.builder();

        person.name( dto.getName() );
        person.lastName( dto.getLastName() );

        return person.build();
    }

    @Override
    public PersonDto toDto(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonDto.PersonDtoBuilder personDto = PersonDto.builder();

        personDto.name( person.getName() );
        personDto.lastName( person.getLastName() );

        return personDto.build();
    }
}
