package sa.group2.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sa.group2.MainApp;
import sa.group2.model.Adopter;
import sa.group2.model.Pet;
import sa.group2.util.DateUtil;

public class ManageGUI {
    @FXML
    private TableView<Pet> petTable;
    @FXML
    private TableColumn<Pet, String> pidColumn;
    @FXML
    private TableColumn<Pet, String> nameColumn;

    @FXML
    private Label pidLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label breedLabel;
    @FXML
    private Label birthdayLabel;

    @FXML
    private TableView<Adopter> adopterTable;
    @FXML
    private TableColumn<Adopter, String> adopterIdColumn;
    @FXML
    private TableColumn<Adopter, String> adopterNameColumn;

    @FXML
    private Label adopterIdLabel;
    @FXML
    private Label adopterNameLabel;

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
                cellData -> cellData.getValue().idProperty());
        adopterNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());

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
            pidLabel.setText(pet.getPid());
            nameLabel.setText(pet.getName());
            breedLabel.setText(pet.getBreed());
            birthdayLabel.setText(DateUtil.format(pet.getBirthday()));
        } else {
            // pet is null, remove all the text.
            pidLabel.setText("");
            nameLabel.setText("");
            breedLabel.setText("");

            birthdayLabel.setText("");
        }
    }

    private void showAdopterDetails(Adopter adopter) {
        if (adopter != null) {
            // Fill the labels with info from the adopter object.
            adopterIdLabel.setText(adopter.getId());
            adopterNameLabel.setText(adopter.getName());

        } else {
            // adopter is null, remove all the text.
            adopterIdLabel.setText("");
            adopterNameLabel.setText("");
        }
    }
}
