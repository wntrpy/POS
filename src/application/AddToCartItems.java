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


	public TextField getQuantityTextField() {
	    return quantityTextField;
	}

	
	public void setQuantityTextField(TextField quantityTextField) {
		this.quantityTextField = quantityTextField;
	}

	public void setNewQtty(int newQtty) {
	    System.out.println("New Quantity " + newQtty);
	    this.quantity = String.valueOf(newQtty); //updates yung value ni quantity para updated din ung ni rereturn ni getterrrr
	    quantityTextField.setText(String.valueOf(newQtty)); //set sa quantityTextField yung updated quantity
	}

	
	
	

    public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
    
    public String getQuantity(){
    	return quantity;
    }


	public String name;
    public String brand;
    public String itemDescription;
    public String category;
    public String price;
    public TextField quantityTextField;
    private String quantity; 
 


	public AddToCartItems(String name, String brand, String itemDescription, String category, String price, String quantity) {
        this.name = name;
        this.brand = brand;
        this.itemDescription = itemDescription;
        this.category = category;
        this.price = price;
        this.quantityTextField = new TextField(quantity); //gawa ng text field kada instance ng item, na ang laman ng text is yung QUANTITY
        this.quantity = quantity;
        
    }

    //para lang pag pinrint ko ung list e nde address ung ma prprint
    @Override
    public String toString() {
        return "Name: " + name + ", Brand: " + brand + ", Description: " + itemDescription + " Category: " + category + " Price: " + price + " Quantity: " + quantity;
    }
}
