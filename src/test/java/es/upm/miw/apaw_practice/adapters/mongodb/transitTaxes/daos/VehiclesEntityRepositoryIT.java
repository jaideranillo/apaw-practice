package es.upm.miw.apaw_practice.adapters.mongodb.transitTaxes.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.transitTaxes.entities.AccidentEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.transitTaxes.entities.TransitTaxesEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.transitTaxes.entities.VehicleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class VehiclesEntityRepositoryIT {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void testCreateAndRead() {

        assertTrue(this.vehicleRepository.findByEnrollment("AAAA").isPresent());
        VehicleEntity vehicle = this.vehicleRepository.findByEnrollment("AAAA").get();
        assertEquals("AAAA", vehicle.getEnrollment());
        assertEquals("SEAT", vehicle.getBrand());
        assertEquals("Maria", vehicle.getOwner().getName());
        assertEquals("Perez", vehicle.getOwner().getFamilyName());

        assertTrue(vehicle.getAccidents().stream()
                .map(AccidentEntity::getRefAccident)
                .collect(Collectors.toList())
                .containsAll(Arrays.asList("ACC001")));

        assertTrue(vehicle.getTransitTaxes().stream()
                .map(TransitTaxesEntity::getDescription)
                .collect(Collectors.toList())
                .containsAll(Arrays.asList("Driving without a driver's license")));


        }
    }
