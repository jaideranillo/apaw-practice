package es.upm.miw.apaw_practice.adapters.rest.ticketbus;


import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.ticketbus.Bus;
import es.upm.miw.apaw_practice.domain.models.ticketbus.BusCreation;
import es.upm.miw.apaw_practice.domain.models.ticketbus.BusTicketsDatesUpdate;
import es.upm.miw.apaw_practice.domain.services.ticketbus.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(BusResource.BUSES)
public class BusResource {
    static final String BUSES = "/ticketbus/buses";
    static final String ID = "/{id}";
    static final String TICKETS_DATES = "/tickets/dates";
    static final String PASSENGERS = "/passengers";

    private BusService busService;

    @Autowired
    public BusResource(BusService busService) {
        this.busService = busService;
    }

    @PostMapping
    public Bus create(@RequestBody BusCreation busCreation) {
        return this.busService.create(busCreation);
    }

    @PatchMapping(path = ID + TICKETS_DATES)
    public Bus updateTicketsDates(@PathVariable String id, @RequestBody BusTicketsDatesDto busTicketsDates) {
        BusTicketsDatesUpdate busTicketsDatesUpdate = new BusTicketsDatesUpdate(id, busTicketsDates.getDepartureTime(), busTicketsDates.getArriveTime());
        return this.busService.updateTicketsDates(busTicketsDatesUpdate);
    }

    @GetMapping(path = ID + PASSENGERS)
    public Stream<String> findNamePassengersByBusReference(@PathVariable("id") String reference, @RequestParam String fields) {
        if (!"name".equals(fields)) {
            throw new BadRequestException("Field '" + fields + "' not supported");
        }
        return this.busService.findNamePassengersByReference(reference);
    }

}
