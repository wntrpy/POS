package Controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.NXTVMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MemberController implements Initializable{

	   @FXML private GridPane MemberListGrid;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int col = 0;
		int row = 0;
		
		try {
			PreparedStatement loadMembers = NXTVMain.local.getConnection()
                    .prepareStatement("select * from member");
			
			ResultSet type = loadMembers.executeQuery();
			
			FXMLLoader itemLoader;
			 while (type.next()) {
				 
				 System.out.println(String.format("%d %s %s%n", type.getInt("MemberID"), type.getString("MemberName"), type.getString("Birthdate")));
				 
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
	   
	   

	
}
