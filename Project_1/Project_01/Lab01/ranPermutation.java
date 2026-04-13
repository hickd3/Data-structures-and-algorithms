/**
 * Dean Hickman
 * Lab01
 * This class employs the Fisher-Yates method for random shuffling
 */
 import java.util.Random;
 import java.util.ArrayList;


 public class ranPermutation{
    public static void main(String[] args){
        ArrayList<Integer>arrRan = new ArrayList<Integer>();
        Random random = new Random();
        for(int i = 1; i <= 10; i++){
            arrRan.add(i);
        }
        for(int i = arrRan.size() - 1; i > 0; i--){
        Integer j = random.nextInt(i + 1);
        Integer k = arrRan.get(i);
        arrRan.set(i, arrRan.get(j));
        arrRan.set(j, k);
     } 
     System.out.println(arrRan);   
    }
}