package es.upm.miw.apaw_practice.domain.services.airport;

import es.upm.miw.apaw_practice.domain.models.airport.Flight;
import es.upm.miw.apaw_practice.domain.models.airport.FlightPriceUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.airport.FlightPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class FlightService {

    private FlightPersistence flightPersistence;

    @Autowired
    public FlightService(FlightPersistence flightPersistence) {
        this.flightPersistence = flightPersistence;
    }

    public Stream<Flight> readAll() {
        return flightPersistence.readAll();
    }

    public void updatePrices(List<FlightPriceUpdating> flightPriceUpdatingList) {
        flightPriceUpdatingList.stream()
                .map(flightNewPrice -> {
                    Flight flight = this.flightPersistence.readById(flightNewPrice.getId());
                    flight.setPrice(flightNewPrice.getPrice());
                    return flight;
                }).forEach(flight -> this.flightPersistence.update(flight));
    }
}
