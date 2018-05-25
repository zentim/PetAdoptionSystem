package sa.group2.view;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sa.group2.model.Pet;
import sa.group2.util.DateUtil;

public class PetEditDialog {
    @FXML
    private Label pidLabel;
    @FXML
    private TextField petNameField;
    @FXML
    private TextField petBreedField;
    @FXML
    private DatePicker petBirthdayField;
    @FXML
    private ChoiceBox petRankChoiceBox;


    private Stage dialogStage;
    private Pet pet;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        petRankChoiceBox.setItems(FXCollections.observableArrayList(
                "no adoption fee",
                "需要預防針500",
                "需要預防針500 + 結紮1000",
                "需要預防針500 + 結紮1000 + 晶片500"
        ));
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the pet to be edited in the dialog.
     *
     * @param pet
     */
    public void setPet(Pet pet) {
        this.pet = pet;
        pidLabel.setText(pet.getPid());
        petNameField.setText(pet.getName());
        petBreedField.setText(pet.getBreed());
        petBirthdayField.setValue(pet.getBirthday());
        petBirthdayField.setPromptText("yyyy-mm-dd");
        petRankChoiceBox.getSelectionModel().select(pet.getPetRank());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            pet.setName(petNameField.getText());
            pet.setBreed(petBreedField.getText());
            pet.setBirthday(petBirthdayField.getValue());
            pet.setPetRank(petRankChoiceBox.getSelectionModel().selectedIndexProperty().getValue());
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (petNameField.getText() == null || petNameField.getText().length() == 0) {
            errorMessage += "No valid pet's name!\n";
        }
        if (petBreedField.getText() == null || petBreedField.getText().length() == 0) {
            errorMessage += "No valid pet's breed!\n";
        }

        if (petBirthdayField.getValue() == null || petBirthdayField.getValue().toString().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(petBirthdayField.getValue().toString())) {
                errorMessage += "No valid birthday. Use the format yyyy-mm-dd!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }
}
