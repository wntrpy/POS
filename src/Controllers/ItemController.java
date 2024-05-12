package Controllers;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import application.AddToCartItems;

import java.util.ArrayList;
import java.util.List;

public class ItemController { //wag muna to pansinin since ito yung controller ng Item node :(
    // POS ItemNode
    @FXML private Button addToCart;
    @FXML private ImageView itemPicture;

    @FXML private Text itemBrand;
    @FXML private Text itemName;
    @FXML private Text price;
    @FXML private Text quantity;
    @FXML private Text description;
    @FXML private Text category;

    private String name;
    private String brand;
    private String itemDescription;
    private String Category;
    public String itemPrice;

    public String itemQuantity;

    public static List<AddToCartItems> clickedItems = new ArrayList<>();


    public void setData (String itemBrand, String itemName, String price, String quantity, String description, String category) {
    name = itemName;
    brand = itemBrand;
    itemDescription = description;
    Category = category;
    itemPrice = price;
    itemQuantity = quantity;

        this.itemBrand.setText(itemBrand);
        this.itemName.setText(itemName);
        this.price.setText( "Price: Php " + price);
        this.quantity.setText("Quantity: " + quantity);
        this.description.setText(description);
        this.category.setText(category);
        //this.itemPicture.setImage(itemPicture);
    }

    @FXML
    private void onButtonAction(ActionEvent e){

        if(e.getSource() == addToCart){
            System.out.println("ADD TO CART");
            /*System.out.println("CLICKED");
            System.out.println("Item Name " + name);
            System.out.println("Item Brand " + brand);
            System.out.println("Item Description " + itemDescription);
            System.out.println("Item Category " + Category);
            System.out.println("Item Price " + itemPrice);
            System.out.println("Item Quantity " + itemQuantity);*/

            AddToCartItems addItem = new AddToCartItems(name, brand, itemDescription, Category, itemPrice, itemQuantity);
            clickedItems.add(addItem);

           // POSController.add();//prints ung laman everytime na pindotin si add to cart
        }

    }

}
