package sa.group2.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import sa.group2.model.Adopter;
import sa.group2.model.Pet;
import sa.group2.util.DateUtil;

public class AdoptionForm {
    @FXML
    private Label petNameLabel;
    @FXML
    private Label pidLabel;

    @FXML
    private TextField nameField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField idCardNumberField;
    @FXML
    private TextField incomeProofField;
    @FXML
    private TextField appointmentTimeField;

    private Stage dialogStage;
    private Pet pet;
    private Adopter adopter;
    private boolean okClicked = false;

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAdopter(Adopter adopter) {
        this.adopter = adopter;

        nameField.setText(adopter.getName());
        emailField.setText(adopter.getEmail());
    }

    public void setPet(Pet pet) {
        this.pet = pet;

        pidLabel.setText(pet.getPid());
        petNameLabel.setText(pet.getName());
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
            adopter.setAdoptingPet(pet.getPid());
            adopter.setName(nameField.getText());
            adopter.setBirthday(DateUtil.parse(birthdayField.getText()));
            adopter.setEmail(emailField.getText());
            adopter.setPhone(phoneField.getText());
            adopter.setIdCardNumber(idCardNumberField.getText());
            adopter.setIncomeProof(incomeProofField.getText());
            adopter.setAppointmentTime(appointmentTimeField.getText());

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

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No valid email!\n";
        }
        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += "No valid phone!\n";
        }

        if (idCardNumberField.getText() == null || idCardNumberField.getText().length() == 0) {
            errorMessage += "No valid idCardNumberField!\n";
        }

        if (incomeProofField.getText() == null || incomeProofField.getText().length() == 0) {
            errorMessage += "No valid incomeProofField!\n";
        }

        if (appointmentTimeField.getText() == null || appointmentTimeField.getText().length() == 0) {
            errorMessage += "No valid appointmentTimeField!\n";
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
