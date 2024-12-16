public class Item extends Receipt{
    private String itemNameC;
    private double theprice;
    private int thequantity;


    public Item(String itemName, double price, int quantity){
        itemNameC = itemName;
        thequantity = quantity;
        theprice = price;
    }

    public String getItemName(){
        return itemNameC;
    }
    public double getPrice(){
        return theprice;
    }
    public int GetQuantity() {
        return thequantity;
    }
    
}
