package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;

public class sceneSwitch {

    private String fxmlFile;
    Parent root;
    Scene scene;

    public void setFxmlFile(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }
    public void switchScenes() throws IOException {
        System.out.println("FXML: " + fxmlFile);
        root = FXMLLoader.load(getClass().getResource(fxmlFile));
        scene = new Scene(root);
        NXTVMain.stage.setScene(scene);
        NXTVMain.stage.show();
    }

}
