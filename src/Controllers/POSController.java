package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import application.AddToCartItems;
import application.Items;
import application.NXTVMain;

//git add .
//git commit -m "message here"
//git push -u origin main


public class POSController{
    @FXML private TextField searchBar;
    @FXML private Button checkoutBTn;

    //LEFT ITEMS
    @FXML private TableColumn<Items, String> CategoriesCol;
    @FXML private TableColumn<Items, String> ClearancePriceCol;
    @FXML private TableColumn<Items, String> DescriptionCol;
    @FXML private TableColumn<Items, String> ItemBrandCol;
    @FXML private TableColumn<Items, String> ItemIDCol;
    @FXML private TableColumn<Items, String> ItemNameCol;
    @FXML private TableColumn<Items, String> QuantityCol;
    @FXML private TableColumn<Items, String> SRPCol;
    @FXML private TableColumn<Items, Void> ButtonCol; //another col for add to cart  buttons
    @FXML private TableView<Items> SearchItemTV; //name ng table view
    //END OF LEFT ITEMS
    
    //RIGHT ITEMS or Add to Cart Items
    @FXML private TableColumn<AddToCartItems, String> addToCartBrandCol;
    @FXML private TableColumn<AddToCartItems, String> addToCartNameCol;
    @FXML private TableColumn<AddToCartItems, String> addToCartDescCol;
    @FXML private TableColumn<AddToCartItems, String> addToCartQttyCol; //text field na sha
    @FXML private TableColumn<AddToCartItems, String> addToCartPriceCol;
    
    @FXML private TableView<AddToCartItems> cartListTV;
    //END OF RIGHT ITEMS
    
   public ObservableList<AddToCartItems> clickedItems = FXCollections.observableArrayList(); //list of add to carts


    public void initialize() {
       loadPOSItemList(""); //calls lang ung showDeault since nandon lahat ng need para sa initial na itsura ng POS
    }
    
    
    // changes itemList sa kaliwa into relevant na  itemList depennde sa search
	public void onTextFieldInputMethodTextChanged(KeyEvent event) {
		// Add your logic here
		String newText = searchBar.getText(); // Access the new input text
		System.out.println("Input method text changed: " + newText);
		loadPOSItemList(newText);
	}
    
    public ObservableList<Items> getList(String search) { //returns a list base dun sa query
        ObservableList<Items> tempList = FXCollections.observableArrayList();

        Items items;

        try {
        	PreparedStatement loadItem = NXTVMain.local.getConnection().prepareStatement(
        		    "SELECT ItemID, ItemBrand, ItemName, Categories, "
        		    + "DescriptionAndValues, SuggestedRetailPrice, ClearancePrice, Quantity "
        		    + "FROM Retail_Inventory_ALL WHERE BranchID = ? AND ("
        		    + "ItemID LIKE ? OR "
        		    + "ItemBrand LIKE ? OR "
        		    + "ItemName LIKE ? OR "
        		    + "Categories LIKE ? OR "
        		    + "DescriptionAndValues LIKE ?)");

        		loadItem.setString(1, NXTVMain.branchID);
        		loadItem.setString(2, "%" + search + "%");
        		loadItem.setString(3, "%" + search + "%");
        		loadItem.setString(4, "%" + search + "%");
        		loadItem.setString(5, "%" + search + "%");
        		loadItem.setString(6, "%" + search + "%");
            
            ResultSet type = loadItem.executeQuery();
            while (type.next()) {

            	DecimalFormat format = new DecimalFormat("0.00");
            	String twoDec = format.format(Double.valueOf(type.getString("SuggestedRetailPrice"))); //convert muna since bawal to gawin sa string, yari ka ke maam pag ginawa mo to
            	//System.out.println("Formatted " + twoDec);
            	
            	
                items = new Items(type.getString("ItemBrand"), type.getString("ItemName"), type.getString("Categories"), type.getString("DescriptionAndValues"),  String.valueOf(twoDec), type.getString("ClearancePrice"), type.getString("Quantity"));

               // System.out.println("ItemBrand: " + type.getString("ItemBrand"));
               // System.out.println("ItemName: " + type.getString("ItemName"));
               // System.out.println("SuggestedRetailPrice: " + type.getString("SuggestedRetailPrice"));
               // System.out.println("Quantity: " + type.getString("Quantity"));
               // System.out.println("Description: " + type.getString("DescriptionAndValues"));
               // System.out.println("Categories: " + type.getString("Categories"));
                //System.out.println("Clearance Price " + type.getString("ClearancePrice"));

                tempList.add(items);

            } //end of type.next
        }//end of try
        catch (Exception e){
            e.printStackTrace();
        }
        return tempList;
    }

    public void loadPOSItemList(String search){
    	
    	
        ObservableList<Items> list = getList(search); //gets lang ung list :)

        //create cells sa table columnsss
        //retrieves values sa Items Class based sa Variablesss
        ItemBrandCol.setCellValueFactory(new PropertyValueFactory<>("ItemBrand"));
        ItemNameCol.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        CategoriesCol.setCellValueFactory(new PropertyValueFactory<>("Categories"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        SRPCol.setCellValueFactory(new PropertyValueFactory<>("SRP"));
        ClearancePriceCol.setCellValueFactory(new PropertyValueFactory<>("ClearancePrice"));
        QuantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        ButtonCol.setCellFactory(param -> new TableCell<>() {
            private final Button addToCartButton = new Button("Add to Cart"); //create btn

            {
                addToCartButton.setOnAction(event -> { //sets yung action ng btn
                 
                	//kinukuha ung selected item sa tablew view 
                    Items selectedItem = getTableView().getItems().get(getIndex());
                    
                    System.out.println("ITEM OBJ: " +  selectedItem);
                    //check if nasa cart na ung item
                    	//if nandon na increment ng isa sa qtty
                    	//if wala add lang sa cart
                    
                    
                    
                    
                    if (selectedItem != null) {
                    	//get lang po ung details nung item na na add to cart 
                    	String name = selectedItem.getItemName();
                        String brand = selectedItem.getItemBrand();
                        String description = selectedItem.getDescription();
                        String category = selectedItem.getCategories();
                        String price = selectedItem.getSRP();
                        String quantity = selectedItem.getQuantity();

                        //create lang po ng instance ng add to cart items na istore den sa list .|.
                        AddToCartItems addItem = new AddToCartItems(name, brand, description, category, price, quantity);
                        
                        System.out.println("STATUS: " + isItemInCart(addItem)); //pasa lang ung instance ng ma check

                        if (isItemInCart(addItem)) { //if true
                            for (AddToCartItems item : clickedItems) {
                                if (item.getName().equals(addItem.getName()) && item.getBrand().equals(addItem.getBrand())) {
                                	System.out.println("LATEST QTTY " + item.getLatestQtty());
                                	int updateCount = item.getLatestQtty() + 1;
                                	addItem.setNewQtty(String.valueOf(updateCount));
                                	
                                   // item.incrementQuantity();
                                    break;
                                }
                            }
                        }
                        else { //dito if wala sa list, ede i aad sa Cart TV
                            clickedItems.add(addItem);
                        }

                        add(); //method na nag print ng laman ng list everytime na ma click ung add to cart mwehehehe
                        
                       //clickedItems.clear(); shits clear the list baka lang kelanganin
                    }

                });
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) { //check if empty yung cells
                    setGraphic(null); //set ung button to null ewan putangina
                } else {
                    setGraphic(addToCartButton); //kapag nde empty dat may text na add to cart ata ewwan putangina
                }
            }
        });

        SearchItemTV.setItems(list);
    }
    
    
    private boolean isItemInCart(AddToCartItems newItem) {
        for (AddToCartItems item : clickedItems) { //need nandito si descript kase base sa records natin, same halos lahat si brand at name, sa desc lang nagka differ
            if (item.getName().equals(newItem.getName()) && item.getBrand().equals(newItem.getBrand()) && item.getItemDescription().equals(newItem.getItemDescription())) {//pang check if them name and brand match ngga
                return true; //true if equal or nandon na bitch
            }
        }
        return false; // false if wala pa ngga
    }


   

@FXML
    private void onButtonAction(ActionEvent e) throws IOException {
    
    if (e.getSource() == checkoutBTn) {
        System.out.println("CHECKOUT CLICKED");

        add(); //print lang po yung final records ng list
    }
    
    
}


	public void add() { //prints ung lamang ng list + nag add ng records dun sa Cart Table View
		for (int i = 0; i < clickedItems.size(); i++) {
			System.out.print(clickedItems.get(i) + "\n");
        
			addToCartBrandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
			addToCartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
			addToCartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
			addToCartDescCol.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        
			
			addToCartQttyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			
			
			
			cartListTV.setItems(clickedItems);
    }
}//create ng 2 methods na prang ganto? isa for summary isa for adding sa Cart TV? ewan


} //end of POS Controller

//scrollable receipt
//scrollable items


//summary = after ng cheeckout btn
//membership 1st
//discount sa cashier 2nd


//remove ItemID /
//2 decimal sa SRP /
//pag pinindot na ung add to cart, dat di na lumabas ulit sa right table view, intstead, mag add lang ng qtty
//text field si quantity sa right TV
//add ng new col sa right, which is for delete button
//di pde mag exceed qtty ni right sa left





