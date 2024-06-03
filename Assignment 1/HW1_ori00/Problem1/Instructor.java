public class Instructor {
    
private String lastName; // Last name 
private String firstName; // First name
private String officeNumber; // Office number

public Instructor(String lname, String fname, String office){
    this.lastName = lname;
    this.firstName = fname;
    this.officeNumber = office;
}

public Instructor(Instructor object2){
    this.lastName = object2.getLN();
    this.firstName = object2.getFN();
    this.officeNumber = object2.getON();
} //copy constructor

public void set(String lname, String fname, String office){
    this.lastName = lname;
    this.firstName = fname;
    this.officeNumber = office;
} //setter

public String getLN(){ return lastName;}
public String getFN(){ return firstName;}
public String getON(){ return officeNumber;}

public String toString(){
    return String.format("%S %S %S", getLN(), getFN(), getON());
}

//The following toString() method is redundant but we can use it.

/*public String toString(){
    return String.format("Last Name: %S\tFirst Name: %S\tOffice Number: %S", getLN(), getFN(), getON());
}*/
}
