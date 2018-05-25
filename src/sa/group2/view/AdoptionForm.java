package sa.group2.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
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
    private DatePicker birthdayField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField idCardNumberField;
    @FXML
    private TextField incomeProofField;
    @FXML
    private DatePicker appointmentTimeField;

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
            adopter.setAdopterBirthday(DateUtil.parse(birthdayField.getValue().toString()));
            adopter.setEmail(emailField.getText());
            adopter.setPhone(phoneField.getText());
            adopter.setIdCardNumber(idCardNumberField.getText());
            adopter.setIncomeProof(incomeProofField.getText());
            adopter.setAppointmentTime(DateUtil.parse(appointmentTimeField.getValue().toString()));

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
        
        int year = DateUtil.parse(birthdayField.getText()).getYear();
        if (birthdayField.getValue() == null) {
            errorMessage += "No valid birthday!\n";
        } else if ((2018-year)<20) {
            	errorMessage += "age less than 20!\n";
    	} else {
            if (!DateUtil.validDate(birthdayField.getValue().toString())) {
                errorMessage += birthdayField.getValue().toString() + "No valid birthday. Use the format yyyy-MM-dd!\n";
            }
        }

        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "No valid email!\n";
        }
        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += "No valid phone!\n";
        }

        if (idCardNumberField.getText() == null || idCardNumberField.getText().length() == 0) {
            errorMessage += "No valid idCardNumber!\n";
        }

        if (incomeProofField.getText() == null || incomeProofField.getText().length() == 0) {
            errorMessage += "No valid incomeProof!\n";
        }

        if (appointmentTimeField.getValue() == null) {
            errorMessage += "No valid appointmentTime!\n";
        } else {
            if (!DateUtil.validDate(appointmentTimeField.getValue().toString())) {
                errorMessage += "No valid appointmentTime. Use the format yyyy-MM-dd!\n";
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
