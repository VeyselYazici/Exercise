package veyselkarani.yazici.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import veyselkarani.yazici.dto.CreateOrUpdatePersonDto;
import veyselkarani.yazici.dto.PersonDto;
import veyselkarani.yazici.repository.entity.Person;
import veyselkarani.yazici.services.PersonService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static veyselkarani.yazici.constants.ApiUrls.*;

@RestController
@RequestMapping(PERSON)
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    @PostMapping(CREATE)
    public ResponseEntity<?> createPerson(CreateOrUpdatePersonDto createOrUpdatePersonDto)
    {
        try{
            personService.createPerson(createOrUpdatePersonDto);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Person is created successfully");
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Person> updatePerson(@RequestBody CreateOrUpdatePersonDto createOrUpdatePersonDto) {
        Person updatedPerson = personService.updatePerson(createOrUpdatePersonDto);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deletePerson(@RequestParam UUID id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            personService.deletePerson(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
    @GetMapping(GETALL)
    public ResponseEntity<List<Person>> getAllPerson()
    {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(FIRST_ELEMENT_OF_LIST)
    public ResponseEntity<PersonDto> firstElementOfList()
    {
        return ResponseEntity.ok(personService.firstElementOfList());
    }

    @GetMapping(FIRST_ELEMENT_OF_LIST_STREAM)
    public ResponseEntity<PersonDto> firstElementOfListStream()
    {
        return ResponseEntity.ok(personService.firstElementOfListStream());
    }

    @PostMapping(FIND_BY_IDENTITY_NUMBER_STREAM)
    public ResponseEntity<PersonDto> findByIdentityNumberStream(@RequestParam Long identityNumber)
    {
        PersonDto personDto = personService.findByIdentityNumberStream(identityNumber);
        if(personDto == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personDto);
    }
    @PostMapping(FIND_BY_IDENTITY_NUMBER)
    public ResponseEntity<PersonDto> findByIdentityNumber(@RequestParam Long  identityNumber)
    {
       PersonDto personDto = personService.findByIdentityNumber(identityNumber);
       if (personDto == null)
       {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(personDto);
    }
    @GetMapping(FINDALL_BY_AGE_GREAT_THAN_25)
    public ResponseEntity<List<PersonDto>> findAllByAgeGreatThan25()
    {
        return ResponseEntity.ok(personService.findAllByAgeGreatThan25());
    }

    @GetMapping(STREAM_MAP)
    public ResponseEntity<List<String>> streamMap()
    {
        return ResponseEntity.ok(personService.streamMapNameUpperCase());
    }

    @GetMapping(STREAM_TO_MAP_FILTER_AGE_AND_NAME)
    public ResponseEntity<Map<String, Integer>> streamToMapFilterAgeAndName()
    {
        return ResponseEntity.ok(personService.streamToMapFilterAgeAndName());
    }

    @GetMapping(STREAM_ANYMATCH)
    public ResponseEntity<Boolean> streamAnyMatchGreatThan25YearsOld()
    {
        return ResponseEntity.ok(personService.streamAnyMatchGreatThan25YearsOld());
    }

    @PostMapping(FIND_BY_IDENTITY_NUMBER_QUERY)
    public ResponseEntity<PersonDto> findByIdentityNumberQuery(@RequestParam Long identityNumber)
    {
        PersonDto personDto = personService.findByIdentityNumberQuery(identityNumber);
        if (personDto == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personDto);
    }

    @PostMapping(FIND_BY_NAME_AND_LASTNAME_QUERY)
    public ResponseEntity<PersonDto> findByNameAndLastNameQuery(@RequestParam String personName,@RequestParam String personLastname)
    {
        PersonDto personDto = personService.findByNameAndLastNameQuery(personName, personLastname);
        if (personDto == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personDto);
    }

}
