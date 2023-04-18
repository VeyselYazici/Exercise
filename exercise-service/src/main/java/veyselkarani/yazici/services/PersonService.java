package veyselkarani.yazici.services;

import org.springframework.stereotype.Service;
import veyselkarani.yazici.dto.CreateOrUpdatePersonDto;
import veyselkarani.yazici.dto.PersonDto;
import veyselkarani.yazici.exception.NotFoundException;
import veyselkarani.yazici.mapper.CreateOrUpdatePersonMapper;
import veyselkarani.yazici.mapper.PersonMapper;
import veyselkarani.yazici.repository.PersonRepository;
import veyselkarani.yazici.repository.entity.Person;
import veyselkarani.yazici.repository.entity.RequiredValues;
import veyselkarani.yazici.utility.ServiceManager;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService extends ServiceManager<Person, UUID> {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository)
    {
        super(personRepository);
        this.personRepository = personRepository;
    }

    public void createPerson(CreateOrUpdatePersonDto createOrUpdatePersonDto)
    {
        Person person = CreateOrUpdatePersonMapper.INSTANCE.toPerson(createOrUpdatePersonDto);
        RequiredValues requiredValues = new RequiredValues();
        requiredValues.setCreationDate(LocalDate.now());
        person.setRequiredValues(requiredValues);
        person.setId(UUID.randomUUID());
        personRepository.save(person);
    }
    public Person updatePerson(CreateOrUpdatePersonDto createOrUpdatePersonDto) {
        Person personToUpdate = personRepository.findById(createOrUpdatePersonDto.getId())
                .orElseThrow(() -> new NotFoundException("Person not found with id: " + createOrUpdatePersonDto.getId()));

        personToUpdate.setName(createOrUpdatePersonDto.getName());
        personToUpdate.setLastName(createOrUpdatePersonDto.getLastName());

        Person updatedPerson = personRepository.save(personToUpdate);
        return updatedPerson;
    }

    public void deletePerson(UUID id)
    {
        if (id == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }

        Person person = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found with id: " + id));
        personRepository.deleteById(id);
    }

    public PersonDto firstElementOfList()
    {
        Person person = personRepository.findAll().get(0);
        return PersonMapper.INSTANCE.toDto(person);
    }

    public PersonDto firstElementOfListStream()
    {
        Optional<Person> personOptional = personRepository.findAll().stream().findFirst();
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            return PersonMapper.INSTANCE.toDto(person);
        }
        else
        {
            throw new RuntimeException("Person not found");
        }
    }
    public PersonDto findByIdentityNumber(Long identityNumber)
    {
        Person person = personRepository.findByIdentityNumber(identityNumber);
        return PersonMapper.INSTANCE.toDto(person);
    }
    //
    public PersonDto findByIdentityNumberStream(Long identityNumber)
    {
        List<Person> personList = personRepository.findAll();
        Optional<Person> personOptional = personList.stream().filter(x -> x.getIdentityNumber().equals(identityNumber)).findFirst();
        if (personOptional.isPresent())
        {
            Person person = personOptional.get();
            return PersonMapper.INSTANCE.toDto(person);
        }
        else
        {
            throw new RuntimeException("Person not found");
        }
    }

    public List<PersonDto> findAllByAgeGreatThan25()
    {
        List<Person> personList = personRepository.findAll().stream().filter(x -> x.getAge() > 25).collect(Collectors.toList());
        List<PersonDto> personDtos = personList.stream().map(x -> PersonMapper.INSTANCE.toDto(x)).collect(Collectors.toList());
        return personDtos;
    }

    // Map kullanarak Isimleri buyuk harflere donusturup yeni bir string list donduruyorum
    // Map islemleri sonucunda yeni bir stream doner
    public List<String> streamMapNameUpperCase()
    {
        List<Person> personList = personRepository.findAll();
        List<String> personNames = personList.stream().map(x -> x.getName().toUpperCase()).collect(Collectors.toList());
        return personNames;
    }
    public Map<String, Integer> streamToMapFilterAgeAndName()
    {
        List<Person> personList = personRepository.findAll();
        Map<String, Integer> personMap = personList.stream()
                .filter(person -> person.getAge() > 25)
                .collect(Collectors.toMap(person -> person.getName(), person -> person.getAge()));
        return personMap;
    }
    // AllMatch > Tüm veri seti kosulu sagliyorsa true doner
    // AnyMatch > Tüm veri setinden 1 veri kosulu sagliyorsa true doner
    // NoneMatch > Tüm verinin kosulu saglamaması durumunda true doner
    public Boolean streamAnyMatchGreatThan25YearsOld()
    {
        Boolean isAgeGreat25Person = personRepository.findAll().stream().anyMatch(x -> x.getAge() > 25);
        return isAgeGreat25Person;
    }

    public PersonDto findByIdentityNumberQuery(Long identityNumber)
    {
        Person person = personRepository.findByIdentityNumberQuery(identityNumber);
        return PersonMapper.INSTANCE.toDto(person);
    }

    public PersonDto findByNameAndLastNameQuery(String personName, String personLastName)
    {
        Person person = personRepository.findByNameAndLastNameQuery(personName, personLastName);
        return PersonMapper.INSTANCE.toDto(person);
    }
}
