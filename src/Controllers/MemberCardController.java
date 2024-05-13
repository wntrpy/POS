package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MemberCardController {

    @FXML private Label MemberIDLbl;

    @FXML private Label FullNameLbl;

    @FXML private Label BirthdateLbl;

    @FXML private ImageView MemberPic;
    
    private int MemberID;
    private String MemberName;
    private String Birthdate;
    
	
    public void setData(int memberID, String memberName, String birthDate) {
    	MemberID = memberID;
    	MemberName = memberName;
    	Birthdate = birthDate;
    	
    	//Image image = new Image(getClass().getResourceAsStream("reblon.png"));
    	//MemberPic.setImage(image); //optional pero mag add ng mem pic column sa MEMBER table ASHHASHHASHAS
    	MemberIDLbl.setText(String.valueOf(MemberID)); //convert since u can't put sum int in a label
    	FullNameLbl.setText(MemberName);
    	BirthdateLbl.setText(Birthdate);
    	
    }
}
