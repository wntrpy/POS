package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ReportsController implements Initializable{
	@FXML private BorderPane borderPane;
	@FXML Button tHistory;
	@FXML Button fastSelling;
	@FXML Button critItem;
	@FXML Button salesTotal;
	@FXML Button netSales;
	
	@FXML
	public void setCenterScenes(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent centerContent = loader.load();
            borderPane.setCenter(centerContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@FXML
	void showTransactHistory(ActionEvent e) {
		System.out.println("History");
		setCenterScenes("/FXML/Reports/TransactionHistory.fxml");
	}
	@FXML
	void showFastSelling(ActionEvent e) {
		System.out.println("Fast Selling");
		setCenterScenes("/FXML/Reports/FastSelling.fxml");
	}
	@FXML
	void showCritItem(ActionEvent e) {
		System.out.println("Critical Items");
		setCenterScenes("/FXML/Reports/CriticalItems.fxml");
	}
	@FXML
	void showSalesTotal(ActionEvent e) {
		System.out.println("Sales Total");
		setCenterScenes("/FXML/Reports/Sales Total.fxml");
	}
	@FXML
	void showNetSales(ActionEvent e) {
		System.out.println("Net Sales");
		setCenterScenes("/FXML/Reports/Net Sales.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setCenterScenes("/FXML/Reports/TransactionHistory.fxml");
	}
}
