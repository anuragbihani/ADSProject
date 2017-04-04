package ads_project;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class encoder {

	public static void main(String[] args) {
		System.out.println("Encoding..");
		// TODO Auto-generated method stub
		_inputFile = args[0];
		long startTime = System.nanoTime();
		try {
			encode();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		System.out.println("Encoding Time using BinaryHeap: "+duration + " ms");
		System.out.println("Done");

	}
	
	public static void encode() throws NumberFormatException, IOException{
			BinaryHeap bh = new BinaryHeap();
			BufferedOutputStream encodedFile = new BufferedOutputStream(new FileOutputStream("C:/Users/bihani1/workspace/ADS/src/encoded.bin"));
			FileWriter codeTableFile = new FileWriter("C:/Users/bihani1/workspace/ADS/src/code_table.txt");
			HashMap<Integer, String> codetTable = new HashMap<>();
			HashMap <Integer, Integer> frequencyTable = buildFrequncyTable(_inputFile);
			for( Map.Entry<Integer, Integer> entry : frequencyTable.entrySet()) {
			    	Node number = new Node();
			    	number.setData(entry.getKey());
			    	number.setFrequency(entry.getValue());
			    	bh.insert(number);
			}
		
			Node root = generateHuffmanTree(bh);
			generateHuffmanCode(root,"",codetTable, codeTableFile);
			BufferedReader br = new BufferedReader(new FileReader(_inputFile));
			String line;
			StringBuilder encodedFileString = new StringBuilder();
		    while ((line = br.readLine())!= null) {
		    	if(!line.isEmpty() && line !=null){
		    		int number = Integer.parseInt(line);
		    		if(codetTable.containsKey(number)){
			        	String code = codetTable.get(number);
			        	encodedFileString.append(code);
			        } 
		    	}
		        	
		    }

		    byte[] barray = new byte[encodedFileString.length()/8];
            for(int i = 0; i < encodedFileString.length()/8; i++){
                barray[i] = (byte) Short.parseShort(encodedFileString.substring(8*i,8*(i+1)),2);
            }
          
            
            
            OutputStream output = null;
            output = encodedFile;
            for(byte b : barray){
            	output.write(b);
            }
            
            br.close();
            output.close();  
		    codeTableFile.close();
		    encodedFile.close();
		
			
			    			
	}
	
	
	//Method to generate the Frequency Table.
	private static HashMap <Integer, Integer> buildFrequncyTable(String inputFilePath) throws NumberFormatException, IOException{
		
		HashMap <Integer, Integer> frequencyTable = new HashMap<>();
		
		BufferedReader br = new BufferedReader(new FileReader(_inputFile));
		String line;
	    while ((line = br.readLine())!= null) {
	    	if(!line.isEmpty() && line !=null){
	    		int number = Integer.parseInt(line);
	    		if(frequencyTable.containsKey(number)){
	    			frequencyTable.put(number, frequencyTable.get(number)+1);
	    		}
	    		else
	    			frequencyTable.put(number, 1);
	    	}
	        	
	    }
	    br.close();
	    return frequencyTable;
	}
	
	private static Node generateHuffmanTree(BinaryHeap bh){ //Returns the root of the Huffman Tree
		while(bh.getHeapsize()!=1){
			bh.insert(createHuffmanNode(bh.extractMin(), bh.extractMin()));
		}
		return bh.extractMin();
	}
	
	private static Node createHuffmanNode(Node n1, Node n2){
		Node res = new Node();
		res.setLeftChild(n1);
		res.setRightChild(n2);
		res.setFrequency(n1.getFrequency()+n2.getFrequency());
		
		return res;
	}
	
	private static void generateHuffmanCode(Node root, String c, HashMap<Integer, String> codeTable, FileWriter codeTableFile) throws IOException{
	
		
		if(root.getLeftChild() == null && root.getRightChild() == null){
			codeTable.put(root.getData(), c);
			codeTableFile.write(root.getData()+" "+c+"\n");
		}
		else
		{
			String l = c+"0";
			String r = c+"1";
			generateHuffmanCode(root.getLeftChild(), l, codeTable, codeTableFile );			
			generateHuffmanCode(root.getRightChild(), r, codeTable, codeTableFile);
		}
		
		
	}
	
	

	private static String _inputFile;
}
