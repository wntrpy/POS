package application;

import javafx.scene.control.TextField;

public class AddToCartItems {

	//again need ng GETTERS para sa PropretyValueFactory 
    public String getName() {
		return name;
	}


	public String getBrand() {
		return brand;
	}


	public String getItemDescription() {
		return itemDescription;
	}

	public String getCategory() {
		return category;
	}


	public String getPrice() {
		return price;
	}


	public TextField getQuantity() {
		return quantity;
	}
	
	public void setQuantity(TextField quantity) {
		this.quantity = quantity;
	}

	public void setNewQtty(String newQtty) {
	System.out.println("New Quantity " + newQtty);
		quantity.setText(newQtty);
		
	}
	
	public int getLatestQtty() { //may problem dito kase di na kukuha ung updated na new quantity
		return Integer.valueOf(quantity.getText());
	}

	public String name;
    public String brand;
    public String itemDescription;
    public String category;
    public String price;
    public TextField quantity;
 

    public AddToCartItems(String name, String brand, String itemDescription, String category, String price, String quantity) {
        this.name = name;
        this.brand = brand;
        this.itemDescription = itemDescription;
        this.category = category;
        this.price = price;
        this.quantity = new TextField(quantity);
        
    }

    //para lang pag pinrint ko ung list e nde address ung ma prprint
    @Override
    public String toString() {
        return "Name: " + name + ", Brand: " + brand + ", Description: " + itemDescription + " Category: " + category + " Price: " + price + " Quantity: " + quantity;
    }
}
