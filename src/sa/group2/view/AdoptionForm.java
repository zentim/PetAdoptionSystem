package sa.group2.view;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import sa.group2.MainApp;
import sa.group2.model.Adopter;
import sa.group2.model.Pet;
import sa.group2.util.DateUtil;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class AdoptionForm {
    @FXML
    private ImageView petImageView;
    @FXML
    private Label petNameLabel;
    @FXML
    private Label pidLabel;
    @FXML
    private Label petRankLabel;

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
    private Label incomeProofLabel;
    @FXML
    private DatePicker appointmentTimeField;

    // Reference to the main application.
    private MainApp mainApp;
    private Stage dialogStage;
    private Adopter adopter;
    private Pet pet;
    private boolean okClicked = false;
    private File incomeProofFile;


    @FXML
    private void initialize() {
        this.adopter = new Adopter();
    }


    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Set unique adopterID for new pet.
        int listSize = mainApp.getAdopterList().size();  // ex: 2
        String tempAdopterID;
        String idNumberString;
        if (listSize != 0) {
            String lastAdopterID = mainApp.getAdopterList().get(listSize - 1).getAdopterID(); // ex: A0002
            idNumberString = "" + (10000 + Integer.parseInt(lastAdopterID.substring(1)) + 1);  // ex: 10003
        } else {
            idNumberString = "" + (10000 + listSize + 1);  // ex: 10001
        }
        tempAdopterID = "A" + idNumberString.substring(1);  // ex: A0003
        System.out.println("temp adopter id: " + tempAdopterID);

        adopter.setAdopterID(tempAdopterID);
    }
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPet(Pet pet) {
        this.pet = pet;

        File file = new File("src/sa/group2/resources/" + pet.getPid() + ".png");
        Image image = new Image(file.toURI().toString());
        petImageView.setImage(image);
        pidLabel.setText(pet.getPid());
        petNameLabel.setText(pet.getName());
        petRankLabel.setText(pet.getPetRankDescription());
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
            adopter.setAppointmentTime(DateUtil.parse(appointmentTimeField.getValue().toString()));

            mainApp.getAdopterList().add(adopter);
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

        if (birthdayField.getValue() == null || birthdayField.getValue().toString().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else if (!DateUtil.validDate(birthdayField.getValue().toString())) {
            errorMessage += "No valid birthday. Use the format yyyy-MM-dd!\n";
    	} else {
            String birthday = birthdayField.getValue().toString();
            int nowYear = LocalDateTime.now().getYear();
            int birthdayYear = DateUtil.parse(birthday).getYear();
            int age  = nowYear - birthdayYear;
            System.out.println("birthdayYear: " + birthdayYear + " nowYear: " + nowYear + " age:" + age);
            if (age < 20) {
                errorMessage += "Age less than 20. Ask the legal representative as the owner.\n";
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

        if (incomeProofFile == null) {
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

    @FXML
    public void uploadIncomeProofImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        incomeProofFile = fileChooser.showOpenDialog(dialogStage);
//        String localFilePaht = incomeProofFile.getAbsolutePath();
//        String fileName = incomeProofFile.getName();
//        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        File outputPath = new File("src/sa/group2/resources/" + adopter.getAdopterID() + "_incomeProof" +".png");
        adopter.setIncomeProof(adopter.getAdopterID() + "_incomeProof" +".png");
        Image image = new Image(incomeProofFile.toURI().toString());
        if (incomeProofFile != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image,
                        null), "png", outputPath);
                incomeProofLabel.setText(incomeProofFile.getAbsolutePath());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
