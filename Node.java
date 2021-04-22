package src;



public class Node<T extends Comparable<T>>
{
	
	public T nodeValue;
	public Node<T> LChild;
	public Node<T> RChild;
	public Node<T> Parent;
	private String color;
	public boolean isLeaf;
	public Node(Node<T> Parent)	
	{
		nodeValue = null;
		LChild = RChild  = null;
		this.Parent = Parent;
		color = "BLACK";
		isLeaf = true;
	}
	public Node(T nodeValue ,String color)			
	{
		super();
		this.nodeValue = nodeValue;
		this.color = color;
		LChild = RChild = Parent = null;
		isLeaf = false;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public void setColor(String color)
	{
		if(color == "BLACK" || color == "RED")
			this.color = color;
		else
			System.out.println("input color error!");
			return;
	}
	
	public String order()
	{
		return  nodeValue + ", ";
	}
}
