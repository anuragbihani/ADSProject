package ads_project;

public class PairingHeapNode {

	public PairingHeapNode(){
		child = null;
		left = null;
		right = null;
		node = new Node();
	}
	
	public Integer getFrequency(){
		return node.getFrequency();
	}
	
	public Integer getData() {
		return node.getData();
	}
	
	public void setData(Integer d) {
		node.setData(d);
	}
	
	
	public void setFrequency(Integer f){
		 node.setFrequency(f);
	}
	
	public PairingHeapNode getLeft(){
		return left;
	}
	
	
	public PairingHeapNode getRight(){
		return right;
	}
	
	public void setLeft(PairingHeapNode l){
		left = l;
	}
	
	public void setRight(PairingHeapNode r){
		right = r;
	}
	
	public PairingHeapNode getChild(){
		return child;
	}
	
	public void setChild(PairingHeapNode c){
		child = c;
	}
	
	public void setRightChild(PairingHeapNode r){
		node.setRightChild(r.getNode());
	}
	public void setLeftChild(PairingHeapNode l){
		node.setLeftChild(l.getNode());
	}
	public Node getRightChild(){
		return node.getRightChild();
	}
	public Node getLeftChild(){
		return node.getLeftChild();
	}
	
	
	public Node getNode(){
		return node;
	}
	
	
	private Node node;
	private PairingHeapNode child;
	private PairingHeapNode left;
	private PairingHeapNode right;

	
}
