package ads_project;

public class FourwayHeapNode {


	public FourwayHeapNode(){
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

	public void setRightChild(FourwayHeapNode r){
		node.setRightChild(r.getNode());
	}
	public void setLeftChild(FourwayHeapNode l){
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
	
}
