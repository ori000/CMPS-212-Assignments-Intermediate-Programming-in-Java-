import java.text.*;

public class Item {//class having info of the Item. sets its price and quantity using constructors

    private double price, bulkPrice;
    public String Name;
    private int quantity, bulk; //bulk is the bulkQuantity

    NumberFormat nf = NumberFormat.getCurrencyInstance();
    
    public Item(String name, double price)
    {
        try{ //if the price is valid, then this constructor sets the name and price...
            if(price >= 0)
            { this.price = price; this.Name = name;}
            else throw new IllegalArgumentException();
        }catch(Exception IIlegalArgumentException)
        {System.out.println("The price can't be negative.");}
    }

    public Item(String name, double price, int bulk, double bp)
    {
        try{ //if the numbers are valid(non-negative) then set the referenced values to the class variables....
            if(price >= 0 && bp >= 0 && bulk >= 0 && quantity >= 0)
            { this.price = price; this.Name = name; this.bulkPrice = bp;this.bulk = bulk;}
            else throw new IllegalArgumentException();
        }catch(Exception IIlegalArgumentException)
        {System.out.println("Numbers can't be negative.");}
    }

    public double priceFor(int quantity)
    {int times = 0;
        try{//if there's a bulk then increment the local variable times so we know how many times we will use the bulkPrice...also, to avoid over counting, decrement the quantity number by the bulk number...
            while(quantity >= bulk && bulk!=0){ times++;quantity-=bulk;}// bulk!=0 thing is to check whether there's a bulk.. if there is none then Java skips this step and just multiplies the quantity with the price...
            if(quantity < 0) throw new IllegalArgumentException();
        }catch(Exception IIlegalArgumentException)
        {System.out.println("The quantity can't be negative.");}
        return bulkPrice*times+quantity * price;//if there is no bulk then just return the quantity*price since bulkPrice*times is 0 and it doesn't affect the quantity and price overall...
    }

    public String toString()
    { String priceTXT = nf.format(price);
      String bulkPRtxt =nf.format(bulkPrice);
        if(bulk > 0) //if there's a bulk, then its quantity and value is added/shown on the "menu" of the frame. else, only the name and price are shown.
        return String.format("Item: %s, Price: %s, (%d for %s)", Name, priceTXT, bulk, bulkPRtxt);
        return String.format("Item: %s, Price: %s", Name, priceTXT);
    }
}
