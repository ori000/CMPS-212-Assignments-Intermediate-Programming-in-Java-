public class SalariedEmployee extends Employee{

    private double weeklySalary;
    
    public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, double weeklySalary) {
        super(firstName, lastName, socialSecurityNumber);
        this.weeklySalary = weeklySalary;
    }

    public void setWeeklySalary(double WeeklySalary){if(WeeklySalary >= 0) this.weeklySalary = WeeklySalary; else this.weeklySalary = 0;}
    public double getWeeklySalary(){ return weeklySalary;}

    //public double earnings(){ return weeklySalary;}

   

    @Override
    public double getPaymentAmount() {
       
        return weeklySalary;
    }

    public String toString(){
        return String.format("Salaried-Employee: %s  %s\nSocial Security Number: %S\nweekly salary: %.1f\npayment due: $%.1f\n", getFN(),getLN(),getSSN(),getWeeklySalary(),getPaymentAmount());

    }

    
    
}
