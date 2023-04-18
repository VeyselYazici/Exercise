package veyselkarani.yazici.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import veyselkarani.yazici.dto.CreateOrUpdatePersonDto;
import veyselkarani.yazici.repository.entity.Person;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-18T22:41:32+0300",
    comments = "version: 1.5.4.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class CreateOrUpdatePersonMapperImpl implements CreateOrUpdatePersonMapper {

    @Override
    public Person toPerson(CreateOrUpdatePersonDto dto) {
        if ( dto == null ) {
            return null;
        }

        Person.PersonBuilder person = Person.builder();

        person.id( dto.getId() );
        person.name( dto.getName() );
        person.lastName( dto.getLastName() );
        person.age( dto.getAge() );
        person.identityNumber( dto.getIdentityNumber() );

        return person.build();
    }

    @Override
    public CreateOrUpdatePersonDto toDto(Person person) {
        if ( person == null ) {
            return null;
        }

        CreateOrUpdatePersonDto.CreateOrUpdatePersonDtoBuilder createOrUpdatePersonDto = CreateOrUpdatePersonDto.builder();

        createOrUpdatePersonDto.id( person.getId() );
        createOrUpdatePersonDto.name( person.getName() );
        createOrUpdatePersonDto.lastName( person.getLastName() );
        createOrUpdatePersonDto.identityNumber( person.getIdentityNumber() );
        createOrUpdatePersonDto.age( person.getAge() );

        return createOrUpdatePersonDto.build();
    }
}
