package ads_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;


public class decoder {

	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Decoding..");
		_encodedFile = args[0];
		_codeTable = args[1];
		long startTime = System.nanoTime();
		decode();
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		System.out.println("BinaryHeap: "+duration + " ms");
		System.out.println("Decoding done");
	}
	
	private static Node generateHuffmanTree(String codeTablePath) throws NumberFormatException, IOException{ //Returns the root of the Huffman Tree
		System.out.println("Genearting Tree..");
		BufferedReader br = new BufferedReader(new FileReader(codeTablePath));
		String line;
		Node root = new Node();
		Node traverser;
	    while ((line = br.readLine())!= null) {
	    	if(!line.isEmpty() && line !=null){
	    		Integer number = Integer.parseInt(line.split(" ")[0]);
	    		String code = line.split(" ")[1];
	    		traverser = root;
	    		for(int i=0; i< code.length()-1;i++){
	    			if(code.charAt(i) == '0'){
	    				if(traverser.getLeftChild() == null){
	    					traverser.setLeftChild(new Node());	    				
	    				}
	    				traverser = traverser.getLeftChild();	    					
	    			}
	    			else if(code.charAt(i) == '1'){
	    				if(traverser.getRightChild() == null){
	    					traverser.setRightChild(new Node());	    				
	    				}
	    				traverser = traverser.getRightChild();	
	    			}    			
	    		}
	    		if(code.charAt(code.length()-1) == '0'){
	    			traverser.setLeftChild(new Node());	
	    			traverser = traverser.getLeftChild();
	    			traverser.setData(number);
	    		}
	    		else{
	    			traverser.setRightChild(new Node());
	    			traverser = traverser.getRightChild();
	    			traverser.setData(number);
	    		}
	    		}
	    }
	    System.out.println("Tree.. done");
	    return root;
	        	
	}
	
private static void printHuffmanTree(Node root) {
		
		if(root.getLeftChild() == null && root.getRightChild() == null){
			System.out.println(root.getData());;
		}
		else
		{
			printHuffmanTree(root.getLeftChild());			
			printHuffmanTree(root.getRightChild());
		}
		
	}

public static String getEncodedString(String encodedPath) throws IOException{
    byte[] byteArray;
    StringBuilder outputStr=new StringBuilder();
    byteArray=Files.readAllBytes(new File(encodedPath).toPath());
    for(byte b: byteArray){
        outputStr.append(Integer.toBinaryString(b & 255 | 256).substring(1));
    }
    return outputStr.toString();
}
	
	
private static void decode() throws IOException{
    //read a bit at a time and traverse the tree until you fall off.
	Node root = generateHuffmanTree(_codeTable);
	String encodedFileString = getEncodedString(_encodedFile);
    PrintWriter w;
    try {
        w = new PrintWriter("C:/Users/bihani1/workspace/ADS/src/decoded.txt");
        Node traverser = root;

        
        for(int i = 0; i<encodedFileString.length() ; i++ ){
  

                if( encodedFileString.charAt(i) == '0' ){
                    //go to left
                    if (traverser.getLeftChild() == null){
                        //fallen off the tree. Print to file                    
                            w.print(traverser.getData()+"\n");
                            traverser = root.getLeftChild();                        
                    }
                    else{
                        traverser = traverser.getLeftChild();

                    }
                } 
                else{
                    //go to right of tree
                    if (traverser.getRightChild() == null){
                        //fallen off the tree. Print to file
                            w.print(traverser.getData()+"\n");
                            traverser = root.getRightChild();                                     
                    }
                    else{
                        traverser = traverser.getRightChild();
                    }
                }
            }
        w.print(traverser.getData());
        w.close();
    } catch (IOException ex) {
       
    }
    
}


	private static String _codeTable;
	private static String _encodedFile;

}
