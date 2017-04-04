package ads_project;

public class Node{

	public Node(){
		LeftChild = null;
		RightChild = null;
		frequency = 0;
		data = 0;
	}
	
	public Integer getFrequency(){
		return frequency;
	}
	
	public Integer getData() {
		return data;
	}
	
	public void setData(Integer d) {
		data = d;
	}	

	
	public void setFrequency(Integer f){
		 frequency = f;
	}
	
	public Node getLeftChild(){
		return LeftChild;
	}
	
	
	
	public Node getRightChild(){
		return RightChild;
	}
	
	public void setLeftChild(Node l){
		LeftChild = l;
	}
	
	public void setRightChild(Node r){
		RightChild = r;
	}
	
	
	private Node LeftChild;
	private Node RightChild;
	private Integer frequency;
	private Integer data;
}
