import java.util.*;

public class ShoppingCart {//RETURNS the total price of the items added to the list... it adds the orders to the itemorder list and takes care of overcounting (quantity)
    
    ArrayList<ItemOrder> orders;
    private boolean check; //checks if there's a discount or not

    public ShoppingCart(){ orders = new ArrayList<>();}

    public void add(ItemOrder order){ 
        for(int i =0;i<orders.size();i++)//replaces the item that was meant to be replaced depending on its quantity...
            if (orders.get(i).getItem().Name.equals(order.getItem().Name))
                orders.remove(i);
        orders.add(order);
    }

    public void setDiscount(boolean check) //sets the discountchecker to the referenced boolean value
    { this.check = check;}

    public double getTotal(){  //returns the total price of the item at all indices. If there's a discount, the total is multiplied by .9 and returned (90% discount)
        double total = 0;
        //adds item prices to get the total
        for(int index = 0; index < orders.size();index++)
        {total+=orders.get(index).getPrice();}

        if(check){ total = total *0.9; return total;}
        
        else
        return total;
    }

    
}
