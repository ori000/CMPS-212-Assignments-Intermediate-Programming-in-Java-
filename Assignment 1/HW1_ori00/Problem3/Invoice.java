public class Invoice implements Payable{
    
    private String partNumber;
    private String partDescription;
    private int quantity;
    private double pricePerItem;

    public Invoice(String partNumber, String partDescription, int quantity, double pricePerItem){

        this.partNumber = partNumber;
        this.partDescription = partDescription;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;

    }

    public void setPN(String PN){ this.partNumber = PN;}
    public String getPN(){ return partNumber;}

    public void setPD(String PD){ this.partDescription = PD;}
    public String getPD(){ return partDescription;}

    public void setQuantity(int Quantity){if(Quantity >= 0)this.quantity = Quantity; else this.quantity = 0;}
    public int getQuantity(){ return quantity;}

    public void setPricePerItem(double PPT){if(PPT >= 0) this.pricePerItem = PPT; else this.pricePerItem = 0;}
    public double getPricePerItem(){ return pricePerItem;}

    public double getPaymentAmount(){ return (double)(getQuantity() * getPricePerItem());}


    public String toString(){
        
        return String.format("invoice:\npart number: %s(%s)\nquantity: %d\nprice per item: %.1f\npayment due: $%.1f\n", getPN(), getPD(), getQuantity(), getPricePerItem(),getPaymentAmount());

    }
    
}
