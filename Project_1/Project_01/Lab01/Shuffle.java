/**
 * Author: Dean Hickman
 * 
 * Lab 01: Develop an algorithm that shuffles the elements of an array to put them into a random order.
 */
import java.util.ArrayList;
import java.util.Random;


public class Shuffle {
    public static void main(String[] args){
        ArrayList<Integer> arr0 = new ArrayList<Integer>();
        Random ran = new Random();
        for (int i= 0; i < 10; i++){
            int val = ran.nextInt(100);
            arr0.add(val);
            //System.out.println(val);
            Integer x = arr0.get(i);
           System.out.println(x);
        }
        ArrayList<Integer> arr1=  arr0;
        ArrayList<Integer> arr2 = arr0;
        System.out.println("arr0 == arr1: " + (arr0 == arr1) + "\narr1 == arr2: " + (arr1 == arr2) + "\narr2 == arr0: " + (arr2 == arr0));
        System.out.println("arr0.equals(arr1): " + (arr0.equals(arr1)) + "\narr1.equals(arr2): " + (arr1.equals(arr2)) + "\narr2.equals(arr0): " + (arr2.equals(arr0)));
        for (int i = 0; i < 10; i++){
            int val = ran.nextInt(arr0.size());
            Integer x = arr0.remove(val);
            System.out.print(x + " ");
            for (int j = 0; j < arr0.size(); j++){
                System.out.print(arr0.get(j) + " ");
            }
            System.out.println();
        }
    }
}
