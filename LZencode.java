// Stefenie Pickston 1506427
// Holly Smallwood 1505405

import java.io.*;

public class LZencode{

    public static DataInputStream dis = new DataInputStream(System.in); //reads bytes from the input river

    public static void main(String[] args) {
        try {
            // for debugging
            //dis = new DataInputStream(new FileInputStream("ha.txt"));

            int count = 0;
            int c; // the int that we read from the file

            TrieNode root = new TrieNode('\0', 0, null); // make an empty root
            TrieNode pointOne = root, pointTwo = pointOne; // pointer TrieNodes

            while ((c = dis.read()) != -1) { // while we havent finished reading the file

                pointTwo = pointOne.search((char)c); // find the character

                if(pointTwo == null){ // if pointTwo is null we cant find it
                    count++; // increment the phrase number
                    pointOne.add((char)c, count); // add the new phrase in
                    System.out.println(pointOne.i + " " + c); // print format to standard output
                    pointOne = root; // reset pointer
                }
                else
                    pointOne = pointTwo; // otherwise increment pointer
            }

            System.out.println(pointTwo.i + " " + 0); // print last one format to standard output

            dis.close(); // close the reader
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    // a combination of a trie and a linked list, where each Node 
    // references the start of a new list of Nodes
    public static class TrieNode{
        int i;
        char c;
        TrieNode parent, child, sibling;

        public TrieNode(char ch, int in, TrieNode tn){ // constructor
            c = ch;
            i = in;
            parent = tn;
        }

        // finds the trienode that has the character we are looking for
        // returns null if it cant find it
        // this is so we can keep the place in the trie as needed
        public TrieNode search(char m){
            if(child == null) // if there is no child
                return null;
            else{
                TrieNode kid = child; // make temp pointer
                while(kid != null){ // increment through the children
                    if(kid.c == m) // if we find it return it
                        return kid;
                    kid = kid.sibling; // otherwise keep looking in the list
                }
                return null; // null if we cant find it
            }
        }

        // adds the char and phrase number to the end of a linked list
        // does not need to worry about which depth of the trie it is in as
        // the pointer system in the search method deals with the current depth
        public void add(char m, int count){
            if(child == null) // if there is no child
                child = new TrieNode(m, count, this); // add it
            else{
                TrieNode kid = child; // temp pointer
                while(kid.sibling != null) // if its not null try go to the next linked item
                    kid = kid.sibling;
                kid.sibling = new TrieNode(m, count, this); // add it to the end of the list
            }
        }
    }
}
