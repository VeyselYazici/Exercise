package veyselkarani.yazici.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateOrUpdatePersonDto {
    private UUID id;
    private String name;
    private String lastName;
    private Long identityNumber;
    private int age;
}
