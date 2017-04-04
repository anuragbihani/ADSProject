package ads_project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FourwayHeap {

	public FourwayHeap(){
		 heap = new ArrayList();
		 heap.add(null);
		 heap.add(null);
		 heap.add(null);
		 frequencyTable = new HashMap();
		 Code = "";
	}
	
	
	public Node huffmanTreeGenerate(){ //Returns the root of the Huffman Tree
		while(heap.size()!=4){
			insert(createNode(extractMin(), extractMin()));
		}
		return heap.get(3).getNode();
	}
	
	
	public Integer getHeapsize(){
		return heap.size();
	}
	
	public void generateHuffmanCode(Node root, String c, FileWriter w) throws IOException{
	
		
		if(root.getLeftChild() == null && root.getRightChild() == null){
			//System.out.println(root.getData()+" ==> "+c);
			
			w.write(root.getData()+" ==> "+c+"\n");
		}
		else
		{
			String l = c+"0";
			String r = c+"1";
			
			generateHuffmanCode(root.getLeftChild(), l, w);
			
			generateHuffmanCode(root.getRightChild(), r, w);
		}
		
		
	}
	
	public FourwayHeapNode createNode(FourwayHeapNode n1, FourwayHeapNode n2){
		FourwayHeapNode res = new FourwayHeapNode();
		res.setLeftChild(n1);
		res.setRightChild(n2);
		res.setFrequency(n1.getFrequency()+n2.getFrequency());
		
		return res;
	}
	
	public FourwayHeapNode extractMin(){
		if(heap.size()<4)
			return null;
		FourwayHeapNode min = heap.get(3);
		if(heap.size()-1==3){
			heap.remove(3);
			return min;
		}
		heap.set(3, heap.remove(heap.size()-1));
		minHeapify(3);
		return min;
		
	}
	
	public void buildHeap(String inputFilePath){
		try {
		
		Scanner sc = new Scanner(new File(inputFilePath));
		    while (sc.hasNextInt()) {
		        int number = sc.nextInt();
		        frequencyTable.put(number, (frequencyTable.get(number) == null ? 1 :  frequencyTable.get(number)+1 )); 
		        //System.out.println(number);
		    }
		    //System.out.println(frequencyTable);
		    for( Map.Entry<Integer, Integer> entry : frequencyTable.entrySet()) {
		    	FourwayHeapNode number = new FourwayHeapNode();
		    	number.setData(entry.getKey());
		    	number.setFrequency(entry.getValue());
		    	//System.out.println(number.getFrequency());
		    	insert(number);
		    	
		    }    
		} catch (Exception e) {
		e.printStackTrace();
		} 	
	}
	
	
	public void insert(FourwayHeapNode number){
		
		heap.add(number);
		Integer numberIndex = heap.size()-1;
		if(heap.size() == 4)
			return;
		FourwayHeapNode parent = heap.get(getParentIdx(numberIndex));
		//System.out.println("NumberIdx"+numberIndex);
		//System.out.println("ParentIdx"+getParentIdx(numberIndex));
		//System.out.println(number.getFrequency());
		//System.out.println(parent.getFrequency());
		while( number.getFrequency() < parent.getFrequency() && getParentIdx(numberIndex) >= 3 ){
			swapNodes(getParentIdx(numberIndex), numberIndex);
			numberIndex = getParentIdx(numberIndex);
			parent = heap.get(getParentIdx(numberIndex));
		}
	}
	
	
	public void minHeapify(Integer numberIndex){
		Integer minChildIdx = getminChildIdx(numberIndex);
		if( minChildIdx == -1){
			return;
		}
		swapNodes(minChildIdx, numberIndex);
		minHeapify(minChildIdx);
	
	}
	
	
	public void swapNodes(Integer node1Idx, Integer node2Idx){
		FourwayHeapNode temp = heap.get(node1Idx);
		heap.set(node1Idx, heap.get(node2Idx));
		heap.set(node2Idx, temp);
	}
	
	
	
	
	
	public Integer getChild1Idx(Integer idx){
		return 4*idx+1-9 < heap.size()-1 ? 4*idx+1-9 : -1;
	}
	
	public Integer getChild2Idx(Integer idx){
		return 4*idx+2-9 < heap.size()-1 ? 4*idx+2-9 : -1;
	}
	
	public Integer getChild3Idx(Integer idx){
		return 4*idx+3-9 < heap.size()-1 ? 4*idx+3-9 : -1;
	}
	
	public Integer getChild4Idx(Integer idx){
		return 4*idx+4-9 < heap.size()-1 ? 4*idx+4-9 : -1;
	}
	
	public Integer getParentIdx(Integer index){
		if(index == 3)
			return 3;
		return (index)/4 + 2;
	}
	
	public void printHeap(){
	    for (FourwayHeapNode n: heap)
	    	if(n != null)
	    		System.out.print(n.getFrequency());
	    System.out.println('\n');
	}
	
	private Integer getminChildIdx(Integer numberIndex){
		Integer child1 = getChild1Idx(numberIndex);
		Integer child2 = getChild2Idx(numberIndex);
		Integer child3 = getChild3Idx(numberIndex);
		Integer child4 = getChild4Idx(numberIndex);
		Integer minChild12, minChild34 ,minChild;
		if ( child1 != -1 && child2 != -1){
			minChild12 = heap.get(child1).getFrequency() < heap.get(child2).getFrequency() ?   child1 : child2;
		}
		else if (child1 == -1 && child2 == -1){
			minChild12 = -1;
		}
		else if(child2 == -1){
			minChild12 = child1;
		}
		else 
			minChild12 = child2;
		
		
		if ( child3 != -1 && child4 != -1){
			minChild34 = heap.get(child3).getFrequency() <  heap.get(child4).getFrequency()?  child3 : child4;
		}
		else if (child3 == -1 && child4 == -1){
			minChild34 = -1;
		}
		else if (child4 == -1){
			minChild34 = child3;
		}
		else 
			minChild34 = child4;
		

		if ( minChild12 != -1 && minChild34 != -1){
			minChild = heap.get(minChild12).getFrequency() < heap.get(minChild34).getFrequency() ?  minChild12 : minChild34;
		}
		else if (minChild12 == -1 && minChild34 == -1){
			minChild = -1;
		}
		else if (minChild34 == -1){
			minChild = minChild12;
		}
		else 
			minChild = minChild34;
		
		
		return minChild;
		
	}
	
	
	private String Code;	
	private String inputFilePath;
	private List<FourwayHeapNode> heap;
	private HashMap<Integer, Integer> frequencyTable;

}
