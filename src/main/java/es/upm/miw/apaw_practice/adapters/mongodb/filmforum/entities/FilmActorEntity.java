package es.upm.miw.apaw_practice.adapters.mongodb.filmforum.entities;

import es.upm.miw.apaw_practice.domain.models.filmforum.FilmActor;
import es.upm.miw.apaw_practice.domain.models.filmforum.FilmActorCreation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class FilmActorEntity {
    @Id
    private String id;
    private String name;
    private String surname;
    private Integer age;

    public FilmActorEntity() {
        // Framework needs it
    }

    public FilmActorEntity(String id, String name, String surname, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public FilmActorEntity(FilmActorCreation actor) {
        this.id = UUID.randomUUID().toString();
        this.name = actor.getName();
        this.surname = actor.getSurname();
        this.age = actor.getAge();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public FilmActor toFilmActor() {
        return FilmActor.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .age(age)
                .build();
    }

    public void fromFilmActor(FilmActor actor) {
        BeanUtils.copyProperties(actor, this);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((FilmActorEntity) obj).id));
    }

    @Override
    public String toString() {
        return "FilmActorEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
