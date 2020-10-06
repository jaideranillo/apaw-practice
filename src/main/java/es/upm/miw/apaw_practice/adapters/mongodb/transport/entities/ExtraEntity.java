package es.upm.miw.apaw_practice.adapters.mongodb.transport.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class ExtraEntity {
    @Id
    private String id;
    private Integer workedHours;
    private Boolean paid;

    public ExtraEntity() {
        //empty for framework
    }

    public ExtraEntity(Integer workedHours, Boolean paid) {
        this.id = UUID.randomUUID().toString();
        this.workedHours = workedHours;
        this.paid = paid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(Integer workedHours) {
        this.workedHours = workedHours;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((ExtraEntity) obj).id));
    }

    @Override
    public String toString() {
        return "ExtraEntity{" +
                "id='" + id + '\'' +
                ", workedHours=" + workedHours +
                ", paid=" + paid +
                '}';
    }
}