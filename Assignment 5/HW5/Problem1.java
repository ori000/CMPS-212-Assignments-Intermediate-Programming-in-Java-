import java.util.*;

public class Problem1{
    //Part 1
    public static int binaryToDecimal(ArrayList<Integer> bin){

        String binStr = String.valueOf(bin);

        String n = binStr.replace(",","");
        n = n.replace("[","");
        n = n.replace("]","");
        n = n.replace(" ","");


        int sum = 0;

        for(int index = 0; index < n.length(); index++){
            sum += Integer.parseInt(n.substring(index,index+1))*Math.pow(2,index);
        }
        return sum;
    }
    //Part 2
    static ArrayList<Integer> list = new ArrayList<>();

    public static ArrayList<Integer> decimalToBinary(int value){

        if(value < 2){list.add(1); 
        final ArrayList<Integer> newList = new ArrayList<Integer>();
        removeAdd(list, newList);
        reverse(newList); 
        return newList;}

        else if(value % 2 == 0) list.add(0);

        else if(value % 2 == 1){list.add(1);}

        return decimalToBinary(value/2);
    }
    private static void removeAdd(ArrayList<Integer> list1, ArrayList<Integer> list2){
        if(list1.isEmpty()) return;
        else{list2.add(list1.remove(0));
            
            removeAdd(list1, list2);}
    }
    private static ArrayList<Integer> reverse(ArrayList<Integer> list){

            if(list.size() > 1){
            int temp = list.remove(0);
            reverse(list);
            list.add(temp);}
            return list;
    }
    //Part 3
    static int index = 0;
    static int count = 0;
    public static int numOnes(String list){

        if(list.length() == 0) 
        {int newCount = count;
        count = 0;
        index = 0; 
        return newCount;}

        else if(list.charAt(index) == '1'){count++;}

        return numOnes(list.substring(index+1,list.length()));
    }
    public static void main(String[] args) {
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(0);
        list.add(1);
        list.add(1);
        list.add(0);
        list.add(1);
        list.add(1);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);list2.add(1);list2.add(1);list2.add(1);list2.add(1);
        list2.add(1);list2.add(1);list2.add(1);list2.add(1);list2.add(1);

        System.out.println(binaryToDecimal(list));
        System.out.println(binaryToDecimal(list2));
        System.out.println(decimalToBinary(605));
        System.out.println(decimalToBinary(43));
        System.out.println(numOnes("10110101"));
        System.out.println(numOnes("1111001"));
    }
}