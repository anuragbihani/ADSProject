package ads_project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class PairingHeap {
	
	PairingHeap(){
		root = null;	
		frequencyTable = new HashMap();
		fifoQueue = new ArrayList();
		size = 0;
	}
	


		
	public PairingHeapNode meld(PairingHeapNode n){
		
		PairingHeapNode next;
		while(n != null){
			next=n.getRight();
			n.setRight(null);
			n.setLeft(null);
			fifoQueue.add(n);
			n = next;
		}
		
		while(fifoQueue.size()>1){
			PairingHeapNode n1 = fifoQueue.remove(0);
			PairingHeapNode n2 = fifoQueue.remove(0);
			//System.out.println(n1.getFrequency());
			//System.out.println(n2.getFrequency());
			if(n1.getFrequency() <= n2.getFrequency()){
				if(n1.getChild()==null){
					n1.setChild(n2);
					n2.setLeft(n1);
					fifoQueue.add(n1);
				}
				else{
					PairingHeapNode temp = n1.getChild();
					n1.setChild(n2);
					n2.setRight(temp);
					temp.setLeft(n2);
					n2.setLeft(n1);
					fifoQueue.add(n1);
				}		
			}
			else{
				if(n2.getChild()==null){
					n2.setChild(n1);
					n1.setLeft(n2);
					fifoQueue.add(n2);
				}
				else{
					PairingHeapNode temp = n2.getChild();
					n2.setChild(n1);
					n1.setRight(temp);
					temp.setLeft(n1);
					n1.setLeft(n2);
					fifoQueue.add(n2);
				}				
			}			
		}
		return fifoQueue.remove(0);
		
	}

	public void buildHeap(String inputFilePath){
		try {
			
			Scanner sc = new Scanner(new File(inputFilePath));
			    while (sc.hasNextInt()) {
			        int number = sc.nextInt();
			        //System.out.println(number);
			        frequencyTable.put(number, (frequencyTable.get(number) == null ? 1 :  frequencyTable.get(number)+1 )); 
			        
			    }
			    sc.close();
			    //System.out.println(frequencyTable);
			    for( Map.Entry<Integer, Integer> entry : frequencyTable.entrySet()) {
			    	PairingHeapNode number = new PairingHeapNode();
			    	number.setData(entry.getKey());
			    	number.setFrequency(entry.getValue());
			    	insert(number);		    	
			    }    
			} catch (Exception e) {
			e.printStackTrace();
			} 	
			
	}
	
	
	public PairingHeapNode extractMin(){
		size -= 1;
		PairingHeapNode min = root;
		PairingHeapNode child = root.getChild();
		if(child != null){
			root = meld(child);
		}
		else
			root=null;
		min.setChild(null);
		return min;
	}
	
	public void printHeap(PairingHeapNode n){
	    System.out.println(n.getFrequency());
	    while(n.getRight()!=null){
	    	n= n.getRight();
	    	System.out.println(n.getFrequency());
	    }
	    if(n.getChild()!=null){
	    	printHeap(n.getChild());
	    }
	}
	
	
	public void insert(PairingHeapNode n){
		size +=1;
		if(root == null){
			root = n;
		}
		else{
			if(root.getFrequency() <= n.getFrequency()){
				if(root.getChild()==null){
					root.setChild(n);	
					n.setLeft(root);
				}
				else{
					PairingHeapNode temp = root.getChild();
					root.setChild(n);
					n.setRight(temp);	
					temp.setLeft(n);
					n.setLeft(root);
				}		
			}
			else{
				n.setChild(root);
				root.setLeft(n);
				root = n;
			}
		}
	}
	
	public PairingHeapNode getRoot(){
		return root;
	}
	
	
	
	public Node huffmanTreeGenerate(){ //Returns the root of the Huffman Tree
		while(root.getChild() != null){
			insert(createNode(extractMin(), extractMin()));
		}
		return root.getNode();
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
	
	public PairingHeapNode createNode(PairingHeapNode n1, PairingHeapNode n2){
		PairingHeapNode res = new PairingHeapNode();
		res.setLeftChild(n1);
		res.setRightChild(n2);
		res.setFrequency(n1.getFrequency()+n2.getFrequency());
		
		return res;
	}
	
	public Integer getHeapsize(){
		return size;
	}
	
	
	
	private Integer size;
	private HashMap<Integer, Integer> frequencyTable;
	private ArrayList<PairingHeapNode> fifoQueue;
	private PairingHeapNode root;

}
