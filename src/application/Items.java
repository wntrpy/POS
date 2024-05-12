package application;

import javafx.scene.control.Button;

public class Items {
    private String ItemID;
    private String ItemBrand;
    private String ItemName;
    private String Categories;
    private String Description;
    private String SRP;
    private String ClearancePrice;
    private String Quantity;
    private Button AddToCartBtn;

    public Items(String itemID, String itemBrand, String itemName, String categories, String description, String SRP, String clearancePrice, String quantity) {
        ItemID = itemID;
        ItemBrand = itemBrand;
        ItemName = itemName;
        Categories = categories;
        Description = description;
        this.SRP = SRP;
        ClearancePrice = clearancePrice;
        Quantity = quantity;
        this.AddToCartBtn = new Button("Add to Cart");

    }

    
    
    
    //GETTERS since pag walang getters dito, di ma reretrieve yung data from POS Controller
    //Need ni PropretyValueFactory ung getters ng kada Variables para ma akses 
    public Button getAddToCartBtn() {
        return AddToCartBtn;
    }

    public void setAddToCartBtn(Button addToCartBtn) {
        AddToCartBtn = addToCartBtn;
    }

    public String getItemID() {
        return ItemID;
    }

    public String getItemBrand() {
        return ItemBrand;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getCategories() {
        return Categories;
    }

    public String getDescription() {
        return Description;
    }

    public String getSRP() {
        return SRP;
    }

    public String getClearancePrice() {
        return ClearancePrice;
    }

    public String getQuantity() {
        return Quantity;
    }
}


