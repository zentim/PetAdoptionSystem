package sa.group2.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Pet {
    private final StringProperty pid;
    private final StringProperty name;
    private final StringProperty breed;
    private final ObjectProperty<LocalDate> birthday;
    private final IntegerProperty petRank;

    public Pet() {
        this(null, null);
    }

    public Pet(String pid, String name) {
        this.name = new SimpleStringProperty(name);
        this.pid = new SimpleStringProperty(pid);

        // Some initial dummy data, just for testing.
        this.breed = new SimpleStringProperty("some breed");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(2018, 5, 22));
        this.petRank = new SimpleIntegerProperty(0);
    }

    // pet rank description
    public String getPetRankDescription() {
        int rank = Integer.parseInt(this.petRank.getValue().toString());
        String description = "";

        switch (rank) {
            case 0:
                description = "no adoption fee";
                break;
            case 1:
                description = "需要預防針500";
                break;
            case 2:
                description = "需要預防針500 + 結紮1000";
                break;
            case 3:
                description = "需要預防針500 + 結紮1000 + 晶片500";
                break;
            default:
                description = "not valid pet rank";
        }

        return description;
    }

    /*
    Getter and Setter
     */

    public String getPid() {
        return pid.get();
    }

    public StringProperty pidProperty() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid.set(pid);
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

    public String getBreed() {
        return breed.get();
    }

    public StringProperty breedProperty() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed.set(breed);
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

    public int getPetRank() {
        return petRank.get();
    }

    public IntegerProperty petRankProperty() {
        return petRank;
    }

    public void setPetRank(int petRank) {
        this.petRank.set(petRank);
    }
}
