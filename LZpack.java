// Stefenie Pickston 1506427
// Holly Smallwood 1505405

import java.io.*;
import java.util.*;
import java.lang.Math;

public class LZpack {

    public static Scanner sc = new Scanner(System.in);
    public static List<Byte> outputArray = new ArrayList<Byte>();
    public static BufferedOutputStream outputStream = new BufferedOutputStream(System.out);

    public static void main(String[] args) {
        try {
            // File f = new File("asdf.txt");
            // sc = new Scanner(f); // for debugging

            int k = 1, prev = 0;// counts up each line to calculate log
            long input = 0L, ref = 255L;

            while (sc.hasNextLine()) {
                byte logOfKO = (byte) Math.ceil(Math.log(k) / Math.log(2)); // calculates log2 of k and rounds up
                k++; // increment line

                String line = sc.nextLine(); // get the line
                String[] extracted = line.split(" "); // split it

                int phrase = Integer.parseInt(extracted[0]); // phrase num

                input = input << logOfKO; // make room for phrase num
                input = input ^ phrase; // insert phrasenum

                if (extracted.length == 2) { // if there is a mismatch char
                    int mismatch = Integer.parseInt(extracted[1]); // mismatch character
                    input = input << 8; // make room for the mismatch char
                    input = input ^ mismatch; // insert mismatch
                }

                int total = prev + logOfKO + 8; // total number of currently used bits
                prev = total % 8; // update the number of lefctover bits

                // shift right, and, convert to byte and output
                // remainder is stored in input
                if (total > 8) {
                    int totaltemp = total;
                    for (int i = 0; i < Math.floor(total / 8); i++) { // for each group of 8 in total
                        long temp1 = input >> (totaltemp - 8); // copy of orig shift over logofk - 8*i
                        long temp2 = temp1 & ref; // AND with 255
                        byte b = (byte) temp2; // convert to byte
                        outputArray.add(b); // add to byte array
                        totaltemp -= 8;
                    }
                } else {
                    byte b = (byte) input;
                    outputArray.add(b); // add to byte array
                }

                long tmp = ref >> (8 - prev); // create the mask
                input = input & tmp; // keep last prev amount of bits
            }

            byte finalb = (byte) (input << (8 - prev)); // add final one to array
            outputArray.add(finalb); // add to byte array

            for (Byte b : outputArray) { // print everything out
                byte B = b; // convert to primitive
                outputStream.write(B); // i think this should print out binary data?
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}