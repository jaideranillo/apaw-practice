package es.upm.miw.apaw_practice.adapters.mongodb.school.entities;

import es.upm.miw.apaw_practice.domain.models.school.Teacher;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class TeacherEntity {
    @Id
    private String id;
    private String name;
    private String familyName;
    private Boolean intern;
    @Indexed(unique = true)
    private String dni;
    private String email;

    public TeacherEntity() {
        //empty from framework
    }

    public static TeacherBuilders.Name builder() {
        return new Builder();
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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Boolean isIntern() {
        return intern;
    }

    public void setIntern(Boolean intern) {
        this.intern = intern;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Teacher toTeacher() {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(this, teacher);
        return teacher;
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (dni.equals(((es.upm.miw.apaw_practice.adapters.mongodb.school.entities.TeacherEntity) obj).dni));
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", intern=" + intern +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class Builder implements TeacherBuilders.Name, TeacherBuilders.FamilyName, TeacherBuilders.Dni, TeacherBuilders.Optionals {

        private final TeacherEntity teacher;

        public Builder() {
            this.teacher = new TeacherEntity();
            BeanUtils.copyProperties(teacher, this);
            this.teacher.id = UUID.randomUUID().toString();
            this.teacher.intern = teacher.isIntern();
        }

        @Override
        public TeacherBuilders.FamilyName name(String name) {
            this.teacher.name = name;
            return this;
        }

        @Override
        public TeacherBuilders.Dni familyName(String familyName) {
            this.teacher.familyName = familyName;
            return this;
        }

        @Override
        public TeacherBuilders.Optionals dni(String dni) {
            this.teacher.dni = dni;
            return this;
        }

        @Override
        public TeacherBuilders.Optionals intern(Boolean intern) {
            this.teacher.intern = intern;
            return this;
        }

        @Override
        public TeacherBuilders.Optionals email(String email) {
            this.teacher.email = email;
            return this;
        }

        @Override
        public TeacherEntity build() {
            return this.teacher;
        }
    }
}
