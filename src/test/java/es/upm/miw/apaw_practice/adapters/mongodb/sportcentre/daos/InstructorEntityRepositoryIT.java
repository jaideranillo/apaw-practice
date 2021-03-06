package es.upm.miw.apaw_practice.adapters.mongodb.sportcentre.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.sportcentre.entities.InstructorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.sportcentre.entities.SpecialityEntity;
import es.upm.miw.apaw_practice.domain.models.sportcentre.Speciality;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class InstructorEntityRepositoryIT {

    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    void testFindByDni() {
        assertTrue(this.instructorRepository.findByDni("11111111D").isPresent());
        InstructorEntity instructor = this.instructorRepository.findByDni("11111111D").get();
        assertEquals("Javier", instructor.getName());
        assertEquals("Fernandez", instructor.getLastName());
        assertEquals(new BigDecimal(1200), instructor.getSalary());
        List<SpecialityEntity> specialities = instructor.getSpecialities();
        assertEquals(1, specialities.size());
    }

}
