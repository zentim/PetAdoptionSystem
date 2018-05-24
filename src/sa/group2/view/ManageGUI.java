package sa.group2.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sa.group2.MainApp;
import sa.group2.model.Adopter;
import sa.group2.model.Pet;
import sa.group2.util.DateUtil;

public class ManageGUI {
    // Pet
    @FXML
    private TableView<Pet> petTable;
    @FXML
    private TableColumn<Pet, String> pidColumn;
    @FXML
    private TableColumn<Pet, String> nameColumn;

    @FXML
    private Label petIDLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label breedLabel;
    @FXML
    private Label birthdayLabel;

    // Adopter
    @FXML
    private TableView<Adopter> adopterTable;
    @FXML
    private TableColumn<Adopter, String> adopterIdColumn;
    @FXML
    private TableColumn<Adopter, String> adoptionStatusColumn;

    @FXML
    private Label adoptingPetLabel;
    @FXML
    private Label adopterIdLabel;
    @FXML
    private Label adopterNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label adopterBirthdayLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label idCardNumberLabel;
    @FXML
    private Label incomeProofLabel;
    @FXML
    private Label appointmentTimeLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the pet table with the two columns.
        pidColumn.setCellValueFactory(
                cellData -> cellData.getValue().pidProperty());
        nameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());

        // Clear pet details.
        showPetDetails(null);

        // Listen for selection changes and show the pet details when changed.
        petTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPetDetails(newValue));


        // Initialize the pet table with the two columns.
        adopterIdColumn.setCellValueFactory(
                cellData -> cellData.getValue().adopterIDProperty());
        adoptionStatusColumn.setCellValueFactory(
                cellData -> cellData.getValue().adoptionStatusProperty());

        // Clear pet details.
        showAdopterDetails(null);

        // Listen for selection changes and show the pet details when changed.
        adopterTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showAdopterDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        petTable.setItems(mainApp.getPetList());

        adopterTable.setItems(mainApp.getAdopterList());

    }

    /**
     * Fills all text fields to show details about the pet.
     * If the specified pet is null, all text fields are cleared.
     *
     * @param pet the pet or null
     */
    private void showPetDetails(Pet pet) {
        if (pet != null) {
            // Fill the labels with info from the pet object.
            petIDLabel.setText(pet.getPid());
            nameLabel.setText(pet.getName());
            breedLabel.setText(pet.getBreed());
            birthdayLabel.setText(pet.getBirthday().toString());
        } else {
            // pet is null, remove all the text.
            petIDLabel.setText("");
            nameLabel.setText("");
            breedLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    private void showAdopterDetails(Adopter adopter) {
        if (adopter != null) {
            // Fill the labels with info from the adopter object.
            adoptingPetLabel.setText(adopter.getAdoptingPet());
            adopterIdLabel.setText(adopter.getAdopterID());
            adopterNameLabel.setText(adopter.getName());
            emailLabel.setText(adopter.getEmail());
            adopterBirthdayLabel.setText(DateUtil.format(adopter.getAdopterBirthday()));
            phoneLabel.setText(adopter.getPhone());
            idCardNumberLabel.setText(adopter.getIdCardNumber());
            incomeProofLabel.setText(adopter.getIncomeProof());
            appointmentTimeLabel.setText(DateUtil.format(adopter.getAppointmentTime()));
        } else {
            // adopter is null, remove all the text.
            adoptingPetLabel.setText("");
            adopterIdLabel.setText("");
            adopterNameLabel.setText("");
            emailLabel.setText("");
            adopterBirthdayLabel.setText("");
            phoneLabel.setText("");
            idCardNumberLabel.setText("");
            incomeProofLabel.setText("");
            appointmentTimeLabel.setText("");
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPet() {
        Pet tempPet = new Pet();
        boolean okClicked = mainApp.showPetEditDialog(tempPet);
        if (okClicked) {
            mainApp.getPetList().add(tempPet);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPet() {
        Pet selectedPet = petTable.getSelectionModel().getSelectedItem();
        if (selectedPet != null) {
            boolean okClicked = mainApp.showPetEditDialog(selectedPet);
            if (okClicked) {
                showPetDetails(selectedPet);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Pet Selected");
            alert.setContentText("Please select a pet in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePet() {
        int selectedIndex = petTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            petTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Pet Selected");
            alert.setContentText("Please select a pet in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleVerifySucess() {
        Adopter selectedAdopter = adopterTable.getSelectionModel().getSelectedItem();
        if (selectedAdopter != null) {
            selectedAdopter.setAdoptionStatus("success");
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Adopter Selected");
            alert.setContentText("Please select a Adopter in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleVerifyFail() {
        Adopter selectedAdopter = adopterTable.getSelectionModel().getSelectedItem();
        if (selectedAdopter != null) {
            selectedAdopter.setAdoptionStatus("fail");
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Adopter Selected");
            alert.setContentText("Please select a Adopter in the table.");

            alert.showAndWait();
        }
    }

}
