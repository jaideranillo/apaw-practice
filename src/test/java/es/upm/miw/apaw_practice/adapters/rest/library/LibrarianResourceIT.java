package es.upm.miw.apaw_practice.adapters.rest.library;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.library.NameUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class LibrarianResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateName(){
        NameUpdating nameUpdating=new NameUpdating("Sanchez");
        this.webTestClient
                .put()
                .uri(LibrarianResource.LIBRARIAN+LibrarianResource.ID_ID+LibrarianResource.NAME,"kk")
                .body(BodyInserters.fromValue(nameUpdating))
                .exchange()
                .expectStatus().isNotFound();
    }
}