public class HourlyEmployee extends Employee{

    private double wage, hours;

    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber,double wage, double hours) {
        super(firstName, lastName, socialSecurityNumber);
        this.wage = wage;
        this.hours = hours;
    }

    public void setWage(double wage){if(wage >= 0)this.wage = wage; else this.wage = 0;}
    public double getWage(){ return wage;}

    public void setHours(double hours){ if(hours >= 0 && hours <= 168)this.hours = hours; else this.hours = 0;}
    public double getHours(){ return hours;}

    public double earnings(){ if(hours <= 40){
        
        return wage * hours; }
        
        else 
        
        return 40*wage + 1.5*wage*(hours-40);}

    public String toString(){

        return String.format("Hourly employee: %s\t%s\nSocial Security Number: %S\nHourly wage: %.1f;\thours worked: %.1f\nearned: $%.1f\n", getFN(),getLN(),getSSN(),getWage(),getHours(),earnings());

    }

    
}
   
