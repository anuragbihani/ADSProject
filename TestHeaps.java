package ads_project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;



public class TestHeaps {
	public static void main(String[] args) throws IOException {
		
	
		
		long startTime = System.nanoTime();
		for(int i=0 ;i< 10; i++){
			FourwayHeap fh = new FourwayHeap();
			fh.buildHeap("C:/Users/bihani1/workspace/ADS/src/sample_input_large.txt");
			Node root = fh.huffmanTreeGenerate();
			FileWriter w = new FileWriter("C:/Users/bihani1/workspace/ADS/src/4way_heap_code_table.txt");
			fh.generateHuffmanCode(root,"",w);
			w.close();
		}
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/10000000;
		System.out.println("4wayCacheHeap: "+duration + " ms");
		
		
/*		
		long startTime = System.nanoTime();
		for(int i=0 ;i< 10; i++){		
			BinaryHeap bh = new BinaryHeap();
			bh.buildHeap("C:/Users/bihani1/workspace/ADS/src/sample_input_large.txt");
			Node root = bh.generateHuffmanTree();
			FileWriter w = new FileWriter("C:/Users/bihani1/workspace/ADS/src/binary_heap_code_table.txt");
			bh.generateHuffmanCode(root,"",w);
			w.close();
			
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/10000000;
		System.out.println("BinaryHeap: "+duration + " ms");*/
	
		
		
/*		long startTime = System.nanoTime();
		for(int i=0 ;i< 1; i++){
			PairingHeap ph  = new PairingHeap();
			ph.buildHeap("C:/Users/bihani1/workspace/ADS/src/sample_input_large.txt");
			System.out.println("Building done");
			Node root = ph.huffmanTreeGenerate();
			System.out.println("Huffman tree generation done");
			FileWriter w = new FileWriter("C:/Users/bihani1/workspace/ADS/src/pairing_heap_code_table.txt");
			ph.generateHuffmanCode(root,"",w);
			w.close();
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		System.out.println("PairingHeap: "+duration + " ms");
		*/
		
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
	
	
	
	
	private static Node generateHuffmanTreeUsingBinaryHeap(BinaryHeap bh){ //Returns the root of the Huffman Tree
		while(bh.getHeapsize()!=1){
			bh.insert(createBHHuffmanNode(bh.extractMin(), bh.extractMin()));
		}
		return bh.extractMin();
	}
	
	
	
	private static Node generateHuffmanTreeUsingPairingHeap(PairingHeap ph){ //Returns the root of the Huffman Tree
		while(ph.getHeapsize()!=1){
			ph.insert(createPHHuffmanNode(ph.extractMin(), ph.extractMin()));
		}
		return ph.extractMin().getNode();
	}
	
	
	
	private static Node generateHuffmanTreeUsingFourwayHeap(FourwayHeap fh){ //Returns the root of the Huffman Tree
		while(fh.getHeapsize()!=4){
			fh.insert(createFHHuffmanNode(fh.extractMin(), fh.extractMin()));
		}
		return fh.extractMin().getNode();
	}
	
	
	
	private static Node createBHHuffmanNode(Node n1, Node n2){
		Node res = new Node();
		res.setLeftChild(n1);
		res.setRightChild(n2);
		res.setFrequency(n1.getFrequency()+n2.getFrequency());
		
		return res;
	}
	
	private static PairingHeapNode createPHHuffmanNode(PairingHeapNode n1, PairingHeapNode n2){
		PairingHeapNode res = new PairingHeapNode();
		res.setLeftChild(n1);
		res.setRightChild(n2);
		res.setFrequency(n1.getFrequency()+n2.getFrequency());
		
		return res;
	}
	
	public static FourwayHeapNode createFHHuffmanNode(FourwayHeapNode n1, FourwayHeapNode n2){
		FourwayHeapNode res = new FourwayHeapNode();
		res.setLeftChild(n1);
		res.setRightChild(n2);
		res.setFrequency(n1.getFrequency()+n2.getFrequency());
		
		return res;
	}
	
	
	
	private static String _inputFile;

}
