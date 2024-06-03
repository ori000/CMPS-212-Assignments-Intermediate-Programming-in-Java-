import java.util.*;

public class Adder {
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int sum = 0;
        int lastUndo = 0;
        int lastRedo = 0;
        String Exit = "Exit"; Exit.equalsIgnoreCase(Exit);
        String Redo = "Redo"; Redo.equalsIgnoreCase(Redo);
        String Undo = "Undo"; Undo.equalsIgnoreCase(Undo);
        Stack<Integer> s = new Stack<>();
        System.out.println("Welcome: Insert Anything");
        String next = scan.nextLine();

        
        while(next!= Exit){
            try{
            System.out.println("Enter a number to be added | Undo | Redo | Exit : ");
            next = scan.nextLine();
            
        
        if(next.equalsIgnoreCase(Exit)){break;}
        else if(next.equalsIgnoreCase(Undo)){if(s.isEmpty())
                           System.out.println("Nothing to Undo.");
                           else {lastUndo = sum;
                                 sum -= s.pop();}}

        else if(next.equalsIgnoreCase(Redo)){if(s.size() < 2)
                                System.out.println("Nothing to Redo.");
                                else{lastRedo = sum;
                                     sum = lastUndo;
                                     s.push(sum - lastRedo);}}

        else if(!next.equalsIgnoreCase(Exit) && !next.equalsIgnoreCase(Undo) && !next.equalsIgnoreCase(Redo) && next.charAt(0) >= 48 && next.charAt(0) <= 57){//make sure that the input is not a string (48 and 57 depends on the ascii code for the integers)
         int ints = Integer.valueOf(next);
         sum += s.push(ints);}

        else System.out.println("Invalid Command.");
        System.out.println("The current value is: " + sum);
        }catch(Exception e){System.out.println("Invalid Command.");}
    }
        System.out.println("Thank you for trying out this machine!");
         scan.close();
    }
}
