package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Controllers.MainFrameController;
import application.Login;
import application.NXTVMain;

public class LoginController {

    // Stage and Scene tf when will you learn to read?
    private Stage stage;
    private Scene scene;

    // FXML buttons and stuff

    //LoginPage
    @FXML private Button loginButton;
    @FXML private TextField userField;
    @FXML private TextField passField;
    @FXML private Text loginErrorText;

    // MainPage
    @FXML private Button YesButton;
    @FXML private Text QuestionText;
    @FXML private Text VoteCountText;


    // Log into the application using Login.java
    public void login(ActionEvent e) throws Exception{

        // when LoginButton gets clicked
        if (e.getSource()==loginButton) {
            System.out.println("TF USER " + userField.getText());
            System.out.println("TF PASS " + passField.getText());

            // localLog from NXTVMain gets updated with the details of the user that logged in
            NXTVMain.localLog = new Login(NXTVMain.local, NXTVMain.branchID , userField.getText(), passField.getText());


            // if localLog.getLoginError is not empty which means it has an error
            if (NXTVMain.localLog.getLoginError()!=null) {

                System.out.println("Login error.");
                // changes the errortext to the corresponding error and shows the error text.
                loginErrorText.setText(NXTVMain.localLog.getLoginError());
                loginErrorText.setVisible(true);
            }

            else {
                // we in boiiisss
                System.out.println("DITO YUNG MAIN FRAME");


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/MainFrame.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                NXTVMain.stage.setScene(scene);
               NXTVMain.stage.setResizable(true);
                NXTVMain.stage.setTitle("MAIN FRAME");
                NXTVMain.stage.show();

               // MainFrameController m = new MainFrameController();
                // kinukuha yung controller na associated dun sa fxml file na yon
                 //m = fxmlLoader.getController();
               // m.btnDashboardDefault(e); //pinasa ko ung event kase pagkaclick ng login button, dat naka load na den yyung dashboard kasi yyun ung default screen

            }

        }
    }



}
