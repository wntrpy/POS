package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.NXTVMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MemberController implements Initializable{
    @FXML private Button AddBtn;
    @FXML private Button DeleteBtn;
	@FXML private GridPane MemberListGrid;
	
    private BorderPane memberBorderPane;
    
    public MemberController(BorderPane borderPane){
    	this.memberBorderPane = borderPane;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int col = 0;
		int row = 0;
		
		try { //same shit sa scroll pane na nag loload ka ng item node fucker with sum new shit like row and col, bukas na ulet bounce
			PreparedStatement loadMembers = NXTVMain.local.getConnection()
                    .prepareStatement("select * from member");
			
			ResultSet type = loadMembers.executeQuery();
			
			FXMLLoader itemLoader;
			 while (type.next()) {
	                itemLoader = new FXMLLoader(getClass().getResource("/FXML/MemberCard.fxml"));
	                VBox memberNode = itemLoader.load();
	                
	                MemberCardController mem = itemLoader.getController();
	                mem.setData(type.getInt("MemberID"), type.getString("MemberName"), type.getString("Birthdate"));
	                
	                		if(col == 4) {
	                			col = 0;
	                			row++;
	                		}
	                		MemberListGrid.add(memberNode, col++, row);
	                		GridPane.setMargin(memberNode, new Insets(50));
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void handleButtonAction(ActionEvent e) {
				if(e.getSource()==AddBtn) {
					System.out.println("ADD BTN MEMBER");
					//setCenterScenes("/FXML/MemberAddForm.fxml");
				}
	}
	
	
	/*@Override //overrides yung setCenterScenes method sa mainframe controller
	  public void setCenterScenes(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent centerContent = loader.load();
            memberBorderPane.setCenter(centerContent);
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	*/
	   
	   

	
}
