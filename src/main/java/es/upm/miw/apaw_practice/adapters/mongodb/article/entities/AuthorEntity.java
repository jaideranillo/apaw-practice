package es.upm.miw.apaw_practice.adapters.mongodb.article.entities;

import es.upm.miw.apaw_practice.domain.models.article.Author;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class AuthorEntity {
    @Id
    private String id;
    private String name;
    private String surname;
    private String nationality;

    public AuthorEntity() {
        //empty from framework
    }

    public AuthorEntity(String name, String surname, String nationality) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
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

    public String getNationality() { return nationality; }

    public void setNationality(String nationality) { this.nationality = nationality; }

    public  static AuthorBuilders.Name builder(){ return new Builder(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Author toAuthor(){
        Author author = new Author();
        BeanUtils.copyProperties(this,author);
        return author;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public static class Builder implements AuthorBuilders.Name,AuthorBuilders.Surname,AuthorBuilders.Nationality,AuthorBuilders.Optionals {

        private AuthorEntity authorEntity;

        public Builder() {
            authorEntity = new AuthorEntity();
            authorEntity.setId(UUID.randomUUID().toString());
        }

        @Override
        public AuthorBuilders.Surname name(String name) {
            authorEntity.setName(name);
            return this;
        }

        @Override
        public AuthorBuilders.Nationality surname(String surname) {
            authorEntity.setSurname(surname);
            return this;
        }

        @Override
        public AuthorBuilders.Optionals nationality(String nationality) {
            authorEntity.setNationality(nationality);
            return  this;
        }

        @Override
        public AuthorEntity build() { return this.authorEntity;}
    }
}
