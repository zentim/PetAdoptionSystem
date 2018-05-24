package sa.group2.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Adopter {
    private final StringProperty adoptingPet;
    private final StringProperty adopterID;
    private final StringProperty name;
    private final StringProperty email;
    private final ObjectProperty<LocalDate> adopterBirthday;
    private final StringProperty phone;
    private final StringProperty idCardNumber;
    private final StringProperty incomeProof;
    private final ObjectProperty<LocalDate> appointmentTime;
    private final StringProperty adoptionStatus;

    public Adopter() {
        this(null, null);
    }

    public Adopter(String adopterID, String name) {
        this.adoptingPet = new SimpleStringProperty("PXXX");
        this.adopterID = new SimpleStringProperty(adopterID);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty("xxxxx@gmail.com");
        this.adopterBirthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(2018, 5, 22));
        this.phone = new SimpleStringProperty("09xxxxxxxx");
        this.idCardNumber = new SimpleStringProperty("P123xxxxxx");
        this.incomeProof = new SimpleStringProperty("some income of proof");
        this.appointmentTime = new SimpleObjectProperty<LocalDate>(LocalDate.of(2018, 5, 22));
        this.adoptionStatus = new SimpleStringProperty("Not verified");
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

    public String getAdopterID() {
        return adopterID.get();
    }

    public StringProperty adopterIDProperty() {
        return adopterID;
    }

    public void setAdopterID(String adopterID) {
        this.adopterID.set(adopterID);
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

    public LocalDate getAdopterBirthday() {
        return adopterBirthday.get();
    }

    public ObjectProperty<LocalDate> adopterBirthdayProperty() {
        return adopterBirthday;
    }

    public void setAdopterBirthday(LocalDate adopterBirthday) {
        this.adopterBirthday.set(adopterBirthday);
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

    public LocalDate getAppointmentTime() {
        return appointmentTime.get();
    }

    public ObjectProperty<LocalDate> appointmentTimeProperty() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDate appointmentTime) {
        this.appointmentTime.set(appointmentTime);
    }

    public String getAdoptionStatus() {
        return adoptionStatus.get();
    }

    public StringProperty adoptionStatusProperty() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus.set(adoptionStatus);
    }
}
