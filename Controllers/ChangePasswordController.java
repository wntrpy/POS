package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChangePasswordController {
    @FXML private Button changePassBtn;
    @FXML private TextField confirmPasswordTF;
    @FXML private TextField newPasswordTF;
    @FXML private TextField reEnterNewPasswordTF;

    @FXML private Label errorLabelEmptyPass;
    @FXML private Label errorLabelPasswordDoesntMatch;

    private void onButtonAction(ActionEvent e){
        if(e.getSource() == changePassBtn){
    errorLabelEmptyPass.setVisible(checkIfTFAreEmpty());
    errorLabelPasswordDoesntMatch.setVisible(isPasswordTheSame());
        }
    }

    public boolean checkIfTFAreEmpty(){
        try{
            if(confirmPasswordTF.getText().isEmpty() || newPasswordTF.getText().isEmpty() || reEnterNewPasswordTF.getText().isEmpty()){
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPasswordTheSame(){

        return false;
    }

    public boolean IsPasswordValid(){
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,20}$";
        if(newPasswordTF.getText().matches(passwordPattern)){
            System.out.println("Valid password");
        }
        return false;
    }

}
//check muna si confirm password(check if empty and check if same yung password na inenter
//type new password at sa re-enter new, check if empty and check if same
//pag satisfy all conditions, mag update na sa database