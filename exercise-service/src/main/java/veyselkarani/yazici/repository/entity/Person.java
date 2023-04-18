package veyselkarani.yazici.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Person{

    @Id
    @GeneratedValue(generator = "guid")
    @Type(type = "uuid-char")
    UUID id;
    String name;
    @Column(name = "last_name")
    String lastName;
    int age;
    @Column(name = "identity_number", unique = true, nullable = false, length = 11)
    @Min(value = 10000000000L, message = "Identity number must be at least 11 digits")
    @Max(value = 99999999999L, message = "Identity number cannot be longer than 11 digits")
    Long identityNumber;

    @Embedded
    RequiredValues requiredValues;

}
