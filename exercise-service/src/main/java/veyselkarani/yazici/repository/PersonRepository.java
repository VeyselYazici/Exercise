package veyselkarani.yazici.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import veyselkarani.yazici.repository.entity.Person;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, UUID> {

    Person findByIdentityNumber(Long identityNumber);

    @Query("SELECT p FROM Person p WHERE p.identityNumber = ?1")
    Person findByIdentityNumberQuery(Long identitynumber);

    @Query("SELECT p FROM Person p WHERE p.name = :name and p.lastName = :lastName")
    Person findByNameAndLastNameQuery(@Param("name") String personName, @Param("lastName") String personLastName);



}
