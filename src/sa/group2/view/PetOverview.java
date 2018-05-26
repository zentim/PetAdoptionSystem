package sa.group2.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sa.group2.MainApp;
import sa.group2.model.Pet;
import sa.group2.util.DateUtil;

import java.io.File;


public class PetOverview {
    @FXML
    private TableView<Pet> petTable;
    @FXML
    private TableColumn<Pet, String> pidColumn;
    @FXML
    private TableColumn<Pet, String> nameColumn;

    @FXML
    private ImageView petImageView;
    @FXML
    private Label pidLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label breedLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label petRankLabel;

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
            pidLabel.setText(pet.getPid());
            nameLabel.setText(pet.getName());
            breedLabel.setText(pet.getBreed());
            birthdayLabel.setText(DateUtil.format(pet.getBirthday()));
            petRankLabel.setText(pet.getPetRankDescription());
            File file = new File("src/sa/group2/resources/" + pet.getPid() + ".png");
            Image image = new Image(file.toURI().toString());
            petImageView.setImage(image);
        } else {
            // pet is null, remove all the text.
            pidLabel.setText("");
            nameLabel.setText("");
            breedLabel.setText("");
            birthdayLabel.setText("");
            petRankLabel.setText("");
        }
    }

    /**
     * Called when the user clicks the adopt button. Opens a dialog to fill in adopter's
     * information for adopting the selected pet.
     */
    @FXML
    private void handleAdoptPet() {
        Pet selectedPet = petTable.getSelectionModel().getSelectedItem();
        if (selectedPet != null) {
            boolean okClicked = mainApp.showAdoptionForm(selectedPet);
            if (okClicked) {
                showPetDetails(selectedPet);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
