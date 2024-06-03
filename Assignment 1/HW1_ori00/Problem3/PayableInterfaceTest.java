public class PayableInterfaceTest {
    
    public static void main(String[] args) {


        //Polymorphic Method

        Payable[] p = {new Invoice("01234", "seat", 2, 375), new Invoice("56789", "tire", 4, 100), new SalariedEmployee("John", "Smith", "123-432-897-121", 800), new SalariedEmployee("Lisa","Barnes","898-231-435-654",1300)};

        for(Payable pp: p){

            if(pp instanceof Invoice){Invoice i = (Invoice) pp;
                                      System.out.println(i);}

            else if(pp instanceof SalariedEmployee){SalariedEmployee s = (SalariedEmployee) pp;
                                                    System.out.println(s);}
        }

        System.out.println("\n\n");



        //The following are other ways of doing it but I think that they are not required. I included them just in case you want multiple possible ways.
        


        Invoice in1 = new Invoice("01234", "seat", 2, 375);
        Invoice in2 = new Invoice("56789", "tire", 4, 79.95);

        SalariedEmployee se1 = new SalariedEmployee("John", "Smith", "123-432-897-121", 800);
        SalariedEmployee se2 = new SalariedEmployee("Lisa","Barnes","898-231-435-654",1300);
        
        System.out.println("\n\n");
        Payable[] payableArray = {in1,in2,se1,se2};

        for(Payable pa:payableArray){
            if(pa instanceof Invoice){ Invoice in = (Invoice) pa;
                                       System.out.println(in);}//System.out.println("payment due: $"+in.getPaymentAmount());}
            else if(pa instanceof SalariedEmployee){ SalariedEmployee se = (SalariedEmployee) pa;
                                                    System.out.println(se);}//System.out.println("payment due: $"+se.getPaymentAmount());}
        }
    }
}
