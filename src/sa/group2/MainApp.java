package sa.group2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sa.group2.model.Adopter;
import sa.group2.model.Pet;
import sa.group2.view.AdoptionFormController;
import sa.group2.view.PetOverviewController;

import java.io.IOException;

public class MainApp extends Application {
    private ObservableList<Pet> petData = FXCollections.observableArrayList();
    private ObservableList<Adopter> adopterData = FXCollections.observableArrayList();

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        this.primaryStage = primaryStage;
//        this.primaryStage.setTitle("Pet Adoption System");
//
//        Parent root = FXMLLoader.load(getClass().getResource("view/PetOverview.fxml"));
//        primaryStage.setScene(new Scene(root));
//
//        primaryStage.show();
//    }

    private Stage primaryStage;
    private SplitPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Pet Adoption System");

        initRootLayout();

        primaryStage.show();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/PetOverview.fxml"));
            rootLayout = (SplitPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            PetOverviewController controller = loader.getController();
            controller.setMainApp(this);

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
        petData.add(new Pet("pid001", "Hans"));
        petData.add(new Pet("pid002", "Ruth"));

        adopterData.add(new Adopter("tim", "zentim@gmail.com"));
    }

    public ObservableList<Pet> getPetData() {
        return petData;
    }
    public ObservableList<Adopter> getAdopterData() {
        return adopterData;
    }


    public boolean showAdoptionForm(Pet pet) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdoptionForm.fxml"));
            SplitPane page = (SplitPane) loader.load();
//            System.out.println("test");

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Adoption Form");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the pet into the controller.
            AdoptionFormController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdopter(getAdopterData().get(0));
            controller.setPet(pet);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
