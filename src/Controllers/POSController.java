package Controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import application.AddToCartItems;
import application.Items;
import application.NXTVMain;
import application.sceneSwitch;


//git push -u origin main


public class POSController implements Initializable {
    @FXML private TextField searchBar;
    @FXML private Button searchBtn;
    @FXML private Button checkoutBTn;


    @FXML private TableColumn<Items, String> CategoriesCol;
    @FXML private TableColumn<Items, String> ClearancePriceCol;
    @FXML private TableColumn<Items, String> DescriptionCol;
    @FXML private TableColumn<Items, String> ItemBrandCol;
    @FXML private TableColumn<Items, String> ItemIDCol = new TableColumn<>("ItemID");
    @FXML private TableColumn<Items, String> ItemNameCol;
    @FXML private TableColumn<Items, String> QuantityCol;
    @FXML private TableColumn<Items, String> SRPCol;
    @FXML private TableColumn<?, ?> ButtonCol;


    @FXML private TableView<Items> SearchItemTV;



   public static List<AddToCartItems> listOfItems;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       showDefault();
    }

    public ObservableList<Items> getList(){
        ObservableList<Items> tempList = FXCollections.observableArrayList();

        Items items;

        try {
            PreparedStatement loadItem = NXTVMain.local.getConnection()
                    .prepareStatement(
                            "SELECT ItemID, ItemBrand, ItemName, Categories, "
                                    + "DescriptionAndValues, SuggestedRetailPrice, ClearancePrice, Quantity "
                                    + "FROM Retail_Inventory_ALL ");

           // loadItem.setString(1, NXTVMain.branchID);

            ResultSet type = loadItem.executeQuery();
            // FXMLLoader itemLoader = new FXMLLoader(getClass().getResource("/GUIfxml/ItemNode.fxml"));
            while (type.next()) {

                items = new Items(type.getString("ItemID"), type.getString("ItemBrand"), type.getString("ItemName"), type.getString("Categories"), type.getString("DescriptionAndValues"),  type.getString("SuggestedRetailPrice"), type.getString("ClearancePrice"), type.getString("Quantity"));

                System.out.println("\nItemID: " + type.getString("ItemID"));
                System.out.println("ItemBrand: " + type.getString("ItemBrand"));
                System.out.println("ItemName: " + type.getString("ItemName"));
                System.out.println("SuggestedRetailPrice: " + type.getString("SuggestedRetailPrice"));
                System.out.println("Quantity: " + type.getString("Quantity"));
                System.out.println("Description: " + type.getString("DescriptionAndValues"));
                System.out.println("Categories: " + type.getString("Categories"));
                System.out.println("Clearance Price " + type.getString("ClearancePrice"));

                tempList.add(items);

            } //end of type.next
        }//end of try
        catch (Exception e){
            e.printStackTrace();
        }

        return tempList;
    }


    public void showDefault(){
        ObservableList<Items> list = getList();

        ItemIDCol.setCellValueFactory(new PropertyValueFactory<>("ItemID"));
        ItemBrandCol.setCellValueFactory(new PropertyValueFactory<>("ItemBrand"));
        ItemNameCol.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        CategoriesCol.setCellValueFactory(new PropertyValueFactory<>("Categories"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        SRPCol.setCellValueFactory(new PropertyValueFactory<>("SRP"));
        ClearancePriceCol.setCellValueFactory(new PropertyValueFactory<>("ClearancePrice"));
        QuantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        ButtonCol.setCellValueFactory(new PropertyValueFactory<>("AddToCartBtn"));


        SearchItemTV.setItems(list);
    }


@FXML
    private void onButtonAction(ActionEvent e) throws IOException {
    if (e.getSource() == searchBtn) {
        System.out.println("SEARCH!");
    } else if (e.getSource() == checkoutBTn) {
        System.out.println("CHECKOUT CLICKED");

        add(); //finalized


    }
}

    public static void add(){
        listOfItems = new ArrayList<>(ItemController.clickedItems); //kinukuha lang ung list sa itemController
        // ItemController.clickedItems.clear();

        for(int i =0; i < listOfItems.size(); i++){
            System.out.print(listOfItems.get(i) + "\n");
        }

    }


}


//scrollable receipt
//scrollable items


//summary = after ng cheeckout btn
//membership 1st
//discount sa cashier 2nd