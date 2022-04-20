// Stefenie Pickston 1506427
// Holly Smallwood 1505405

import java.io.*;
import java.util.*;
import java.lang.Math;

public class LZunpack {

    public static DataInputStream dis = new DataInputStream(System.in); //reads bytes from the input river
    public static List<Integer> phraseNumberArray = new ArrayList<Integer>(); // array of phrase numbers
    public static List<Integer> mismatchArray = new ArrayList<Integer>(); // array of mismatch characters
    public static void main(String[] args) {
        try {
            // dis = new DataInputStream(new FileInputStream("t.txt"));

            int k = 1, i, leftover = 0;
            // int inputbuff = 0, mask = 255, bigmask = 2147483647;
            long inputbuff = 0L, mask = 255L, bigmask = 9223372036854775807L;
            byte b = 0;

            while (dis.available() > 0) { // while input not empty
                byte logOfKO = (byte) Math.ceil(Math.log(k) / Math.log(2)); // calculates log2 of k and rounds up
                k++; // increment line

                int tempaaa = (int) Math.ceil((8 + (double) logOfKO - (double) leftover)/8);

                for(i = 0; i < tempaaa; i++){// calculate how many bytes to read in : 8 + logofK bits -> ceiling
                    inputbuff = inputbuff << 8; //shift along for the new bytes
                    if(dis.available() > 0)
                        b = dis.readByte(); // read in a byte for each buff
                    long bb = b & mask;
                    inputbuff = inputbuff ^ bb;
                }

                int total = 8 * i + leftover; // total amount of bits used in the long

                long outputp = (long) (inputbuff >> (total - logOfKO)); // shift over >> by (total - logk) bits
                long tempmask1 = (long) bigmask >> (63 - logOfKO);
                outputp = outputp & tempmask1;
                phraseNumberArray.add((int) outputp);// mask and convert the first logofk amount of bits, and output to array

                long outputm = (long) (inputbuff >> (total - logOfKO - 8)); // shift by >> (total - logk - 8) bits
                outputm = outputm & mask; // mask and convert the next 8 bits, and output to array - and with 255 after shifting
                mismatchArray.add((int) outputm);

                // keep the old unused bits and discard used ones
                leftover = total - 8 - logOfKO;
                long tempmask = bigmask >> (63 - leftover);
                inputbuff = inputbuff & tempmask;
            }

            dis.close();
            print(); // print out everything we have collected

        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    public static void print(){
        for(int i = 0; i < phraseNumberArray.size(); i++){
            System.out.println(phraseNumberArray.get(i) + " " + mismatchArray.get(i)); // print i of each array
        }
    }
}