public class PayrollSystemTest {
    
    public static void main(String[] args) {
        
        //Method1 This is through variables

        SalariedEmployee SE = new SalariedEmployee("Jason", "Johann", "21630001", 1100.0);
        HourlyEmployee HE = new HourlyEmployee("Osama", "Iskandarani", "26456120", 10, 43);

        System.out.println(SE);
        //System.out.println("earned: $"+SE.earnings());

        System.out.println(HE);
        //System.out.println("earned: $" + HE.earnings());

        System.out.println("\n\n");

     
          



        //Method2, also a polymorphic way
        Employee[] eA = {new SalariedEmployee("Jason", "Johann", "21630001", 1100.0), new HourlyEmployee("Osama", "Iskandarani", "26456120", 16.75, 40)};

        for(Employee e: eA){ if(e instanceof SalariedEmployee){SalariedEmployee s = (SalariedEmployee) e;
                                                               System.out.println(s);}

                             else if(e instanceof HourlyEmployee){HourlyEmployee h = (HourlyEmployee) e;
                                                                  System.out.println(h);}
    }

    System.out.println("\n\n");

    //Method3 this is also a polymorphic way

        Employee[] empArr = {HE, SE};
        for(Employee emp: empArr){

            if(emp instanceof SalariedEmployee){ SalariedEmployee se = (SalariedEmployee) emp;
                                                 System.out.println(se);}
                                                 //System.out.println("earned: $"+se.earnings());}
            else if(emp instanceof HourlyEmployee){HourlyEmployee he = (HourlyEmployee) emp;
                                                   System.out.println(he);}
                                                   //System.out.println("earned: $"+he.earnings());}
        }

        

    }
}
