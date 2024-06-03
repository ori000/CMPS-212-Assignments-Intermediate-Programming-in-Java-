import java.util.*;

public class ItemOrder {//mainly used for returning the item price and the item itself

    private Item item; 
    private int quantity;
    
    ArrayList<Item> list = new ArrayList<>();

    public ItemOrder(Item item, int quantity)//sets the values of the item and quantity to the order..
    { this.item = item; this.quantity = quantity;}

    public double getPrice(){ return item.priceFor(quantity);} //returns price of the item depending on the quantity 

    public Item getItem(){ return item;} //returns the item as that of the ItemOrder argument in the parameter (referenced)
}
