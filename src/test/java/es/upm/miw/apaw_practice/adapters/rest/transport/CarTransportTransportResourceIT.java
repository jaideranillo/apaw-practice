package es.upm.miw.apaw_practice.adapters.rest.transport;

import es.upm.miw.apaw_practice.adapters.mongodb.transport.entities.ExtraEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.transport.entities.WorkerEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.transport.VehicleTransport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.util.Arrays;

import static es.upm.miw.apaw_practice.adapters.rest.transport.VehicleTransportResource.VEHICLES;
import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class CarTransportTransportResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        ExtraEntity[] extras = {
                new ExtraEntity("1",1, true),
                new ExtraEntity("2",2, false)
        };
        WorkerEntity worker = new WorkerEntity("nameTest", "telephoneTest", "dniTest", Arrays.asList(extras[0], extras[1]) );
        VehicleTransport vehicleTransport =
                new VehicleTransport("brandTest","modelTest","plateTest", LocalDate.now(), worker);
        this.webTestClient
                .post()
                .uri(VEHICLES)
                .body(BodyInserters.fromValue(vehicleTransport))
                .exchange()
                .expectStatus().isOk()
                .expectBody(VehicleTransport.class)
                .value(Assertions::assertNotNull)
                .value(vehicleData -> assertNotNull(vehicleData.getId()));
    }
}