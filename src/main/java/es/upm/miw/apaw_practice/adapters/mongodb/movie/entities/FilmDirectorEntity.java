package es.upm.miw.apaw_practice.adapters.mongodb.movie.entities;

import es.upm.miw.apaw_practice.domain.models.movie.FilmDirector;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FilmDirectorEntity {
    @Id
    private String id;
    private String name;
    private String fullName;
    private Integer age;

    public FilmDirectorEntity() {
        //empty from framework
    }

    public FilmDirectorEntity(String name, String fullName, int age) {
        this.name = name;
        this.fullName = fullName;
        this.age = age;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public FilmDirector toFilmDirector() {
        FilmDirector filmDirector = new FilmDirector();
        BeanUtils.copyProperties(this, filmDirector);
        return filmDirector;
    }

    @Override
    public String toString() {
        return "FilmDirectorEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                '}';
    }
}