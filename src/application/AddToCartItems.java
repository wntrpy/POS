package application;

public class AddToCartItems {

    public String name;
    public String brand;
    public String itemDescription;
    public String category;
    public String price;
    public String quantity;

    public AddToCartItems(String name, String brand, String itemDescription, String category, String price, String quantity) {
        this.name = name;
        this.brand = brand;
        this.itemDescription = itemDescription;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    //para lang pag pinrint ko ung list e nde address ung ma prprint
    @Override
    public String toString() {
        return "Name: " + name + ", Brand: " + brand + ", Description: " + itemDescription + " Category: " + category + " Price: " + price + " Quantity: " + quantity;
    }
}
