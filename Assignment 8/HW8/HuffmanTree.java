import java.util.*;
//import javax.imageio.IIOException;
import java.io.*;

//NOTE: The BinaryCode.txt stores the binary representation of the compressed file. I tried to print it on the terminal and it worked but it was so messy (especially when printing the hamlet binary code) so I wrote it in that file.

public class HuffmanTree {

    private HuffmanNode root;
    private PriorityQueue<HuffmanNode> PQ = new PriorityQueue<>();
    private Map<Character, Integer> map = new HashMap<>();
    
    public HuffmanTree(Map<Character, Integer> counts) throws IllegalArgumentException{
       if(counts == null) throw new IllegalArgumentException();
    //initialize the main map to counts
       this.map = counts;
    //Add the entries in the map to the PQ as nodes
       for (var entry: map.entrySet()) {
        PQ.add(new HuffmanNode(entry.getKey(), entry.getValue()));
    }
    //When we reach the last element in the PQ, it will be the root having the frequency of all others (Unifying the trees into one). 
    //Each created node is basically the summation as a parent of 2 children as exemplified in the powerpoint.
    //assign the left node to be the value of front of PQ and the same thing for the right to be the value of the new front of PQ 
    //traversal is left then right such that the tree is sorted
    while (PQ.size() != 1){
        HuffmanNode left = PQ.poll();
        HuffmanNode right = PQ.poll();

        int count = left.getFreq() + right.getFreq();
        PQ.add(new HuffmanNode(left, right, null, count));//build up the tree by assigning left and right nodes but with no character as such nodes are internal nodes (with summation and not leaf nodes with characters)
        } root = PQ.peek(); //after we reach only one node then let the root be the front of the PQ
    }
    //Create the encoding (binary representation for each char/elem) by referencing the instantiated Map using the helper function explained next.
    public Map<Character, String> createEncodings(){ 
        Map<Character, String> Map = new HashMap<>();
        createEncodings(root,Map.get(root.getElem()), Map);
        return Map;
    }
    //If we reach a leaf, then assign it the value if it exists, else assign 1.
    //Then traverse the tree putting zeroes when going to the left and ones to the right.
    private void createEncodings(HuffmanNode node, String Binaryvalue, Map<Character, String> map)throws IllegalArgumentException{
        if(map == null) throw new IllegalArgumentException();
        if(Binaryvalue == null) Binaryvalue = ""; //initialize the value to "" at the very beginning of the traversal.
        if(node == null) return;
        else if(IsLeaf(node))
        map.put(node.getElem(), Binaryvalue);//if we reach a leaf node then assign the String binary value of the node as the summed up value during traversal(ex: 0110)

        createEncodings(node.left, Binaryvalue + "0", map); //traverse the left and assign 0 as a binary representation for the node
        createEncodings(node.right, Binaryvalue + "1", map);//traverse the right and assign 1 as a binary representation for the node
    }
    //Checks if the node is a leaf such that it is a character to be read and not a summation as explained in powerpoint.
    private boolean IsLeaf(HuffmanNode node)throws IllegalArgumentException{
        if(node == null) throw new IllegalArgumentException();
        else if(node.right == null && node.left == null) return true;
        else return false;
    }
    //compresses the data into the input file and writes the Binary data/code to the BinaryCode.txt file (done by me).
    //As long as there are more elements to read in the imput, down cast to a char (key) and get the value at that key using the referenced mapreturned by the createEncodings() method;
    public void compress(InputStream input, BitOutputStream output) throws IOException, IllegalArgumentException { 
       if(input == null || output == null) throw new IOException();
       FileWriter fileWriter = new FileWriter("BinaryCode.txt");
       Map<Character, String> Map = createEncodings();
       while(input.available() != 0){
           Character Ch = (char) input.read();
           fileWriter.write(Map.get(Ch));//writes the binary code into BinaryCode.txt
           output.writeBits(Map.get(Ch));//writes the compressed data into output
       }   fileWriter.close();
    }
    //original decompress function, throws an exception if any of the input/output are null. Then as long as there exists elements in the input file to be read then recursively
    //then recursively call the decompress helper function, which traverses the tree until it finds the leaf nodes to write the characters to the output file
    //since the characters are found on the leafs. Internal nodes contain the summation of the leafs and characters....This is explained in the powerpoint.
    public void decompress(BitInputStream input, OutputStream output) throws IOException{ 
        HuffmanNode current = root;
        if(input == null || output == null) throw new IOException();
        while(input.hasNextBit()){
            decompress(input, output, current);}
    }
    //as long as the node that we are referencing is not null then write to the output file if we reached a leaf (writes a character), else traverse to the right
    //if the element/data of the node is 1 and to the left if 0
    private void decompress(BitInputStream input, OutputStream output, HuffmanNode node) throws IOException{
        if(node != null){
        if(IsLeaf(node))
        output.write(node.getElem());
        else if(input.readBit() == 1)
        decompress(input, output, node.right);
        else decompress(input, output, node.left);}
    }
}
