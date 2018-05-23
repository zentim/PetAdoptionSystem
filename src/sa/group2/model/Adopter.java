package sa.group2.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Adopter {
    private final StringProperty adoptingPet;
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty email;
    private final ObjectProperty<LocalDate> birthday;
    private final StringProperty phone;
    private final StringProperty idCardNumber;
    private final StringProperty incomeProof;
    private final StringProperty appointmentTime;

    public Adopter() {
        this(null, null);
    }

    public Adopter(String id, String name) {
        this.adoptingPet = new SimpleStringProperty("");
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty("");

        // Some initial dummy data, just for testing.
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(2018, 5, 22));
        this.phone = new SimpleStringProperty("09xxxxxxxx");
        this.idCardNumber = new SimpleStringProperty("P123xxxxxx");
        this.incomeProof = new SimpleStringProperty("some income of proof");
        this.appointmentTime = new SimpleStringProperty("YYYY/MM/DD");
    }

    /*
    Getter and Setter
     */

    public String getAdoptingPet() {
        return adoptingPet.get();
    }

    public StringProperty adoptingPetProperty() {
        return adoptingPet;
    }

    public void setAdoptingPet(String adoptingPet) {
        this.adoptingPet.set(adoptingPet);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getIdCardNumber() {
        return idCardNumber.get();
    }

    public StringProperty idCardNumberProperty() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber.set(idCardNumber);
    }

    public String getIncomeProof() {
        return incomeProof.get();
    }

    public StringProperty incomeProofProperty() {
        return incomeProof;
    }

    public void setIncomeProof(String incomeProof) {
        this.incomeProof.set(incomeProof);
    }

    public String getAppointmentTime() {
        return appointmentTime.get();
    }

    public StringProperty appointmentTimeProperty() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime.set(appointmentTime);
    }
}
