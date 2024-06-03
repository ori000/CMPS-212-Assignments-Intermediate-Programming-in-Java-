//import java.util.ArrayList;
import java.util.Arrays;

//import java.util.*;

public class Problem2 {

    static int i = 0;
    static int j = i+1;
    //Worst Case: O(N) and that is being traversing the list N-1 times. An examples is having the target at the end of the last and having the values that sum up to it at directly before it where we increment j and i N times.
    //Best Case: O(1); By having the target as the second index and the value that sums up to it at the first index.
    public static boolean k_Sum(int[] array, int target){
        
        if(i >= array.length){i = 0; j = i + 1; return false;} //if there was not any 2 ints that sum up to the target then return false. this means that the list was traversed entirely and no ints were found

        else if(j >= array.length){i++;j = i+1; return k_Sum(array, target);} //if there were no ints that sum up with the first value to the target then repeat the process with the second value until we reach either the end (no solution) or 2 values that sum up to the target

        else if(array[i] + array[j] == target){i = 0; j = i + 1; return true;} //if there were values that sum up then re-initialize i and j to their original values and return true. this re-initialization is to allow us to call the method as many times as we want.

        else {j++; return k_Sum(array,target);} //if array[j] + array[i] don't sum up to the target then try array[j+1] with array[i] and repeat using the recursive call.
    }


    public static void main(String[] args) {
        
        int[] array = {1,2,3,5};
        System.out.println(k_Sum(array,0));
        System.out.println(k_Sum(array,5));
        System.out.println(k_Sum(array,1));
        System.out.println(k_Sum(array,7));
        System.out.println(k_Sum(array,2));

        int[] array2 = {1,6,4,9,3,2,0,5,10};
        Rearranging(array2, 3);
        System.out.println(Arrays.toString(array2));
       
        
    }

    //Worst case: O(N) and that is by traversing the list N times, this is because both or one of the last/first indices will be incrementing/decrementing until reaching the target. this incrementation is one at a time until we traverse the entire list ---> O(N)
    //Best case: O(1) and that is by having the target on either the first or the last index.
    public static void Rearranging(int[] array, int target){//original method, it calls the helper function.

       Rearrange(array, target, 0, array.length-1);
    }
    //Helper method so I can increment and decrement through indices without having them in the parameters of the original method
    public static void Rearrange(int[] array, int target, int first, int last){

        if(first >= last) return; //base case

        if(array[first] <= target && array[last] >= target){ Rearrange(array, target, first+1, last-1);} //Begin via the beginning and end of the list. If those indices are correctly sorted compared to the target then try the other indices by getting closer to the target.(first+1 and last-1)

        if(array[first] > target && array[last] <= target) //If both values at both indices are misplaced then switch them and then continue through the other values
        {
            int temp = array[first];
            array[first] = array[last];
            array[last] = temp;
            Rearrange(array, target, first+1, last-1);
        }

        if(array[first] <= target && array[last] <= target) //If the value after the target is misplaced then increment first (that is before the target) in order to swap last with it (recursive call until we reach the second if statement that swaps)
        {
            Rearrange(array, target, first+1, last);
        }

        if(array[first] > target && array[last] > target) // Same thing but for the value being misplaced before the target.
        {
           Rearrange(array, target, first, last-1);
        }

    }
}
