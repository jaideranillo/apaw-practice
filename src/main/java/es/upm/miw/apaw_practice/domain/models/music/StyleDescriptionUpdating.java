package es.upm.miw.apaw_practice.domain.models.music;

public class StyleDescriptionUpdating {
    private String name;
    private String description;

    public StyleDescriptionUpdating(){
        //empty for framework
    }

    public StyleDescriptionUpdating(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StyleDescriptionUpdating{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
