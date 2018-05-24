package sa.group2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sa.group2.model.Adopter;
import sa.group2.model.Pet;
import sa.group2.view.AdoptionForm;
import sa.group2.view.ManageGUI;
import sa.group2.view.PetEditDialog;
import sa.group2.view.PetOverview;

import java.io.IOException;

public class MainApp extends Application {
    private ObservableList<Pet> petList = FXCollections.observableArrayList();
    private ObservableList<Adopter> adopterList = FXCollections.observableArrayList();

    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Pet Adoption System");

        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/MainApp.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);


            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
        petList.add(new Pet("P001", "Hans"));
        petList.add(new Pet("P002", "Ruth"));

        adopterList.add(new Adopter("A001", "Mark"));
    }

    public ObservableList<Pet> getPetList() {
        return petList;
    }
    public ObservableList<Adopter> getAdopterList() {
        return adopterList;
    }

    @FXML
    public void showPetOverview() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PetOverview.fxml"));
            SplitPane page = (SplitPane) loader.load();

            // Create the dialog Stage.
            Stage petStage = new Stage();
            petStage.setTitle("Pet Overview");
            petStage.initModality(Modality.WINDOW_MODAL);
            petStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            petStage.setScene(scene);

            // Set the pet into the controller.
            PetOverview controller = loader.getController();
            controller.setMainApp(this);

            // Show the dialog and wait until the user closes it
            petStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showManageGUI() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ManageGUI.fxml"));
            HBox page = (HBox) loader.load();

            // Create the dialog Stage.
            Stage manageStage = new Stage();
            manageStage.setTitle("Manage GUI");
            manageStage.initModality(Modality.WINDOW_MODAL);
            manageStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            manageStage.setScene(scene);

            // Set the controller.
            ManageGUI controller = loader.getController();
            controller.setMainApp(this);

            // Show the dialog and wait until the user closes it
            manageStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showAdoptionForm(Pet pet) {
        Adopter adopter = new Adopter();
        adopterList.add(adopter);

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdoptionForm.fxml"));
            SplitPane page = (SplitPane) loader.load();

            // Create the dialog Stage.
            Stage formStage = new Stage();
            formStage.setTitle("Adoption Form");
            formStage.initModality(Modality.WINDOW_MODAL);
            formStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            formStage.setScene(scene);

            // Set the adpter and the pet into the controller.
            AdoptionForm controller = loader.getController();
            controller.setDialogStage(formStage);
            controller.setAdopter(adopter);
            controller.setPet(pet);

            // Show the dialog and wait until the user closes it
            formStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showPetEditDialog(Pet pet) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PetEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PetEditDialog controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPet(pet);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
