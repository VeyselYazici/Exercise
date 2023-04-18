package veyselkarani.yazici.repository.entity;



import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Data
public class RequiredValues {

    @Column(name = "creation_date")
    private LocalDate creationDate;

}
