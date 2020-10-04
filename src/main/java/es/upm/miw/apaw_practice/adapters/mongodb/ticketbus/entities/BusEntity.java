package es.upm.miw.apaw_practice.adapters.mongodb.ticketbus.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class BusEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String reference;
    private String company;
    private Boolean accesibility;
    private Boolean wifi;

    @DBRef
    private List<TicketBusEntity> tickets;

    @DBRef
    private List<JourneyEntity> journeys;

    public BusEntity() {
        // empty from framework
    }

    public BusEntity(String reference, String company, Boolean accesibility, Boolean wifi, List<TicketBusEntity> tickets, List<JourneyEntity> journeys) {
        this.id = UUID.randomUUID().toString();
        this.reference = reference;
        this.company = company;
        this.accesibility = accesibility;
        this.wifi = wifi;
        this.tickets = tickets;
        this.journeys = journeys;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Boolean getAccesibility() {
        return accesibility;
    }

    public void setAccesibility(Boolean accesibility) {
        this.accesibility = accesibility;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public List<TicketBusEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketBusEntity> tickets) {
        this.tickets = tickets;
    }

    public List<JourneyEntity> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<JourneyEntity> journeys) {
        this.journeys = journeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusEntity busEntity = (BusEntity) o;
        return id.equals(busEntity.id) &&
                reference.equals(busEntity.reference) &&
                company.equals(busEntity.company);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "BusEntity{" +
                "id='" + id + '\'' +
                ", reference='" + reference + '\'' +
                ", company='" + company + '\'' +
                ", accesibility=" + accesibility +
                ", wifi=" + wifi +
                ", tickets=" + tickets +
                ", journeys=" + journeys +
                '}';
    }
}
