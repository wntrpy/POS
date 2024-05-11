package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import static javafx.application.Application.launch;

public class NXTVMain extends Application{
// all static variables since this is the Main program that launches everything

    public static final String branchID = "MainPlaridel";
    public static final ConnectDB local = new ConnectDB("PC-1\\SQLEXPRESS","NXTVapeLounge","sa","12345");
    public static Login localLog;

    public static Stage stage;

    // launches the program
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("java version: "+System.getProperty("java.version"));
        System.out.println("javafx.version: " + System.getProperty("javafx.version"));

        Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginPage.fxml"));
        stage = primaryStage;

        try {
            // Load image from resources
            Image logo = new Image(getClass().getResourceAsStream("/Images/nxtVapeLogo.png"));
            primaryStage.getIcons().add(logo);
        } catch (Exception e) {
            System.out.println("Problem with image");
            e.printStackTrace();
        }

        primaryStage.setTitle("NXT Vape Lounge");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }



}
//may binago ka sa xmlns ng loginpage, ung version balikan mo nalang
