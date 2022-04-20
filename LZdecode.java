// Stefenie Pickston 1506427
// Holly Smallwood 1505405

import java.io.*;
import java.util.*;

public class LZdecode {

    public static Scanner sc = new Scanner(System.in); // reads bytes from the input river
    public static List<Integer> phraseNumberArray = new ArrayList<Integer>(); // array of phrase numbers
    public static List<Integer> mismatchArray = new ArrayList<Integer>(); // array of mismatch characters
    public static List<Integer> outputArray = new ArrayList<Integer>();
    public static Boolean flag = false; // flag to break out of recursive routine

    public static void main(String[] args) {
        try {
            // File f = new File("out.txt");
            // sc = new Scanner(f); // for debugging

            phraseNumberArray.add(0); // setup array
            mismatchArray.add(0);

            while(sc.hasNextLine()){ // while reading
                String line = sc.nextLine(); // get the line
                String[] extracted = line.split(" "); // split and put in an array

                if(extracted.length == 2){ // if the array isn't empty
                    phraseNumberArray.add(Integer.parseInt(extracted[0])); // put phrase number in arraylist
                    mismatchArray.add(Integer.parseInt(extracted[1])); // put mismatch char in arraylist
                }
                else if(!extracted[0].isEmpty()){ // if the array has length of 1 and isnt empty strings
                    phraseNumberArray.add(Integer.parseInt(extracted[0])); // put phrase number in arraylist
                    mismatchArray.add(32);//Integer.valueOf(' ')); // mismatch char is newline put in arraylist
                }  
            }

            for(int i = 1; i < phraseNumberArray.size(); i++){
                flag = false; // reset the flag
                outputArray = new ArrayList<Integer>(); // reset output array
                recprint(i);
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // recursively gathers the strings needed from the arrays
    // prints out when we reach a null string
    public static void recprint(int index){
        if(!flag){ // if not the null item
            if(index == 0){ // if we reached the null item
                flag = true; // set flag
                for (Integer integer : outputArray) { // print out gathered output
                    int temp = integer; // use primitive int for char casting
                    System.out.print((char)temp); 
                }
            }
            else{
                outputArray.add(0,mismatchArray.get(index)); // insert to front of output
                index = phraseNumberArray.get(index); // update phrase num
            }
            recprint(index);
        }
    }
}