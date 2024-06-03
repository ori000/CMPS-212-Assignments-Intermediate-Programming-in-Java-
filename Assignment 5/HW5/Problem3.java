import java.util.*;

public class Problem3 {
    
    public static int product(ArrayList<Integer> list){
        Iterator<Integer> iter = list.iterator();
        int prod = 1;
        while(iter.hasNext())
        {prod *= iter.next();}
        return prod;
    }

    public static Queue<Integer> mirrorHalves(Queue<Integer> queue) throws Exception{

        if (queue == null || queue.size() % 2 != 0) {throw new Exception("Invalid size.");} 
            
            Stack<Integer> s = new Stack<Integer>(); 
            int size = queue.size(); 
            
            for (int index = 0; index < size / 2; index++) { 
            int value = queue.remove(); 
            s.push(value); 
            queue.add(value);} 

            while (!s.isEmpty()) {queue.add(s.pop());} 
            
            for (int index = 0; index < size / 2; index++) { 
            int value = queue.remove(); 
            s.push(value); 
            queue.add(value);}

            while (!s.isEmpty()) {queue.add(s.pop());}
            return queue;
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(product(list));

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(10);
        queue.add(50);
        queue.add(19);
        queue.add(54);
        queue.add(30);
        queue.add(67);
        System.out.println(mirrorHalves(queue));
    }

}
