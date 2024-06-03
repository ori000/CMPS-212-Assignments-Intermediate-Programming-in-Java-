import java.util.*;

public class Catalog {//contains info of the catalogue or the LIST that contains the items... it adds them to the list and returns their size....
    
    
    private String catalogue; //catalogue name
    ArrayList<Item> list; //item list 

    public Catalog(String name){ //Catalog constructor, sets its naem and creates a new list of Items..
        this.catalogue = name;
        list = new ArrayList<>();
    }
    public void add(Item item){ list.add(list.size(),item);} //adds items at the end of the list

    public int size(){ return list.size();} //returns the size of the list

    //returns the item at the specified index and throws an exception if the index is invalid
    public Item get(int index) throws IndexOutOfBoundsException{ //or use the try catch method such as the one commented below
        /*Item itemInList = list.get(index);
        try{return itemInList;}//if index is invalid then JAVA automatically launches the catch statement
        catch(IndexOutOfBoundsException e){System.out.println("Invalid index.");}
        return itemInList;} //gets an item at a specific index if it is valid*/
        return list.get(index);}

    public String getName(){ return catalogue;} //returns catalogue name
}
