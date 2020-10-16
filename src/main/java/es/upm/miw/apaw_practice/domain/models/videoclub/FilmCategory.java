package es.upm.miw.apaw_practice.domain.models.videoclub;

import java.util.UUID;

public class FilmCategory {

    private String id;
    private String reference;
    private String name;
    private Boolean plus18;

    public FilmCategory() {
        // empty from framework
    }

    public FilmCategory(String reference, String name, Boolean plus18) {
        this.id = UUID.randomUUID().toString();
        this.reference = reference;
        this.name = name;
        this.plus18 = plus18;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPlus18() {
        return plus18;
    }

    public void setPlus18(Boolean plus18) {
        this.plus18 = plus18;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategory filmCategory = (FilmCategory) o;
        return id.equals(filmCategory.id) &&
                name.equals(filmCategory.name) &&
                plus18.equals(filmCategory.plus18);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "FilmCategory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", plus18=" + plus18 +
                '}';
    }
}
