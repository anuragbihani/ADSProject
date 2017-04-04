package ads_project;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.ToIntFunction;

public class BinaryHeap {

	public BinaryHeap(){
		 heap = new ArrayList();
		 frequencyTable = new HashMap();
		 Code = "";
	}
	

	//Method to compute the index of the left child in the array.
	public Integer getLeftChildIdx(Integer index){
		return index*2+1 <= heap.size()-1 ?  index*2+1 : -1;
	}
	
	//Method to compute the index of the right child in the array.
	public Integer getRightChildIdx(Integer index){
		return index*2+2 <= heap.size()-1 ?  index*2+2 : -1;
	}
	
	//Method to compute the index of the parent in the array.
	public Integer getParentIdx(Integer index){
		if(index == 0)
			return 0;
		return (index-1)/2;
	}
	
	
	//Method to insert an element in the heap.
	public void insert(Node number){
		
		heap.add(number);
		Integer numberIndex = heap.size()-1;
		if(heap.size() == 1)
			return;
		Node parent = heap.get(getParentIdx(numberIndex));		
		while( number.getFrequency() < parent.getFrequency() && getParentIdx(numberIndex) >= 0 ){
			swapNodes(getParentIdx(numberIndex), numberIndex);
			numberIndex = getParentIdx(numberIndex);
			parent = heap.get(getParentIdx(numberIndex));
		}
	}
	
	//Method to remove the minimum element from heap and adjust heap accordingly.
	public Node extractMin(){
		if(heap.size()<1)
			return null ;
		Node min = heap.get(0);
		if(heap.size()-1==0){
			heap.remove(0);
			return min;
		}
		heap.set(0, heap.remove(heap.size()-1));
		minHeapify(0);
		return min;
		
	}
	
	//Method to get the index of the minimum child.
	public Integer getminChildIdx(Integer index){
		Integer leftChild = getLeftChildIdx(index);
		Integer rightChild = getRightChildIdx(index);
		Integer minChild;
		if ( leftChild != -1 && rightChild != -1){
			minChild = heap.get(leftChild).getFrequency() < heap.get(rightChild).getFrequency() ?   leftChild : rightChild;
		}
		else if (leftChild == -1 && rightChild == -1){
			minChild = -1;
		}
		else if(rightChild == -1){
			minChild = leftChild;
		}
		else 
			minChild = rightChild;
		
		return minChild;
		
	}
	

	//Method to maintain heap property after extractMin() operation.
	public void minHeapify(Integer numberIndex){
		
		Integer minChildIdx = getminChildIdx(numberIndex);
		if( minChildIdx == -1){
			return;
		}
		swapNodes(minChildIdx, numberIndex);
		minHeapify(minChildIdx);	
	
	}
	
	//Method to swap 2 nodes in the heap.
	public void swapNodes(Integer node1Idx, Integer node2Idx){
		Node temp = heap.get(node1Idx);
		heap.set(node1Idx, heap.get(node2Idx));
		heap.set(node2Idx, temp);
	}
	
	//Method to get the current size of the heap.
	public Integer getHeapsize(){
		return heap.size();
	}
	
	
	//Method used by TestHeaps.java to compute the Frequency Table and insert all the data.
	public void buildHeap(String inputFilePath ){
		try {
		
		Scanner sc = new Scanner(new File(inputFilePath));
		    while (sc.hasNextInt()) {
		        int number = sc.nextInt();
		        frequencyTable.put(number, (frequencyTable.get(number) == null ? 1 :  frequencyTable.get(number)+1 )); 
		        //System.out.println(number);
		    }
		    //System.out.println(frequencyTable);
		    for( Map.Entry<Integer, Integer> entry : frequencyTable.entrySet()) {
		    	Node number = new Node();
		    	number.setData(entry.getKey());
		    	number.setFrequency(entry.getValue());
		    	insert(number);
		    	
		    }    
		} catch (Exception e) {
		e.printStackTrace();
		} 	
	}
	
	
	//Method used by TestHeaps.java to generate the Huffman Tree.
	public Node generateHuffmanTree(){ //Returns the root of the Huffman Tree
		while(heap.size()!=1){
			insert(createNode(extractMin(), extractMin()));
			//System.out.println(extractMin().getFrequency());
			//System.out.println(extractMin().getFrequency());
			//System.out.println(heap.size());
			//printHeap();
		}
		return heap.get(0);
	}
	

	
	//Method used by TestHeaps.java to build merge two nodes into one and create a node for the Huffman Tree.
	public Node createNode(Node n1, Node n2){
		Node res = new Node();
		res.setLeftChild(n1);
		res.setRightChild(n2);
		res.setFrequency(n1.getFrequency()+n2.getFrequency());
		
		return res;
	}

	
	
	//Method used by TestHeaps.java to generate huffman codes from the huffman tree and print it to a file.
	public void generateHuffmanCode(Node root, String c, FileWriter w) throws IOException{
	
		
		if(root.getLeftChild() == null && root.getRightChild() == null){
			//System.out.println(root.getData()+" ==> "+c);
			
			w.write(root.getData()+" "+c+"\n");
		}
		else
		{
			String l = c+"0";
			String r = c+"1";
			
			generateHuffmanCode(root.getLeftChild(), l, w);
			
			generateHuffmanCode(root.getRightChild(), r, w);
		}
		
		
		/*if(root.getRightChild()!=null){
			Code += '1';
			generateHuffmanCode(root.getLeftChild());
		}
		System.out.println(root.getFrequency());
		System.out.println(Code);
		FileWriter writer = new FileWriter("C:/Users/bihani1/workspace/ADS/src/samples/codetable.txt");
		writer.write(Code);*/
		
		
	}
	

	private String Code;	
	private String inputFilePath;
	private List<Node> heap;
	private HashMap<Integer, Integer> frequencyTable;

}

