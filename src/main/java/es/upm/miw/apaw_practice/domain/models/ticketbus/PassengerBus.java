package es.upm.miw.apaw_practice.domain.models.ticketbus;


public class PassengerBus {

    private String id;
    private String docIdentify;
    private String name;
    private String familyName;
    private String phone;
    private String email;
    private Boolean accesibility;

    public PassengerBus() {
        //empty from framework
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAccesibility() {
        return accesibility;
    }

    public void setAccesibility(Boolean accesibility) {
        this.accesibility = accesibility;
    }

    public String getDocIdentify() {
        return docIdentify;
    }

    public void setDocIdentify(String docIdentify) {
        this.docIdentify = docIdentify;
    }

    @Override
    public String toString() {
        return "PassengerBus{" +
                "id='" + id + '\'' +
                ", docIdentify='" + docIdentify + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", accesibility=" + accesibility +
                '}';
    }
}
