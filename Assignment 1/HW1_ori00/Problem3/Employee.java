public abstract class Employee implements Payable{
    
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;

    public Employee(String firstName, String lastName, String socialSecurityNumber){

        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;

    }

    public void setFN(String fn){ this.firstName = fn;}
    public String getFN(){ return firstName;}

    public void setLN(String ln){ this.lastName = ln;}
    public String getLN(){ return lastName;}

    public void setSSN(String SSN){ this.socialSecurityNumber = SSN;}
    public String getSSN(){ return socialSecurityNumber;}

    public String toString(){

        return String.format("Employee: %S\t%S\nSocial Security Number: %S", getFN(), getLN(), getSSN());

    }

   // public abstract double earnings();
   public abstract double getPaymentAmount();
}
