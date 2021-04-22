package src;




public class RedBlackTree<T extends Comparable<T>>
{
	public Node<T> Tree;
	
	public RedBlackTree()
	{
		Tree = null;
	}
	
	public void print()
	{
		System.out.print("[");
		inOrder(Tree);
		System.out.print("]");
		System.out.println();
		
	}
	
/*	private void preOrder(RedBlackTreeNode<T> node)
	{
		if(node != null)
		{
			if(node.isLeaf == false)
				System.out.print(node.toString());
			preOrder(node.LChild);
			preOrder(node.RChild);
		}
	}*/

	private void inOrder(Node<T> node)
	{
		if(node != null)
		{
			inOrder(node.LChild);
			if(node.isLeaf == false)
				System.out.print(node.order());
			inOrder(node.RChild);
		}
	}
	
	public Node<T> search(T value)
	{																			
		Node<T> p = Tree;
		Node<T> pre = null;
		while(true)
		{
			if(value.compareTo(p.nodeValue) == 0)
			{
				return p;
			}
			else if(value.compareTo(p.nodeValue) == -1)
			{
				pre = p;
				p = p.LChild;
				if(p.isLeaf == true)
					return pre;
			}
			else if(value.compareTo(p.nodeValue) == 1)
			{
				pre = p;
				p = p.RChild;
				if(p.isLeaf == true)
					return pre;
			}
		}
	}
	
	private void leftRotate(Node<T> node)
	{
		Node<T> p = node.RChild;
		if(node.Parent != null)
		{
			if(node.Parent.LChild == node)
			{
				node.Parent.LChild = p;
				p.Parent = node.Parent;
			}
			else
			{
				node.Parent.RChild = p;
				p.Parent = node.Parent;
			}
		}
		else
		{
			Tree = p;
			p.Parent = null;
		}
		node.RChild = node.RChild.LChild;
		p.LChild = node;
		
		node.RChild.Parent = node;		
		node.Parent = p;
	}

	private void rightRotate(Node<T> node)
	{
		Node<T> p = node.LChild;
		if(node.Parent != null)
		{
			if(node.Parent.LChild == node)
			{
				node.Parent.LChild = p;
				p.Parent = node.Parent;
			}
			else
			{
				node.Parent.RChild = p;
				p.Parent = node.Parent;
			}
		}
		else
		{
			Tree = p;
			p.Parent = null;
		}
		node.LChild = node.LChild.RChild;
		p.RChild = node;
		
		node.LChild.Parent = node;		
		node.Parent = p;
	}
	
	public void insert(T value)
	{
		if(Tree == null)		
		{
			Tree = new Node<T>(value, "BLACK");
			Tree.LChild = new Node<T>(Tree);
			Tree.RChild = new Node<T>(Tree);
		}
		else									
		{
			Node<T> node = null;
			node = search(value);
			if(value.compareTo(node.nodeValue) == 0)
			{
				System.out.println("the element is alredy exist! please try again");
				return;
			}
			else
			{
				Node<T> newNode = new Node<T>(value, "RED");
				newNode.LChild = new Node<T>(newNode);
				newNode.RChild = new Node<T>(newNode);
				newNode.Parent = node;
				if(value.compareTo(node.nodeValue) == -1)
				{
					node.LChild = newNode;
				}
				else
				{
					node.RChild = newNode;
				}
					
				insertAdjust(newNode);
			}
		}
	}
	
	private void insertAdjust(Node<T> newNode)
	{
		Node<T> parentNode = newNode.Parent;
		if(parentNode.getColor().equals("BLACK"))		
		{
			return;
		}
		else		
		{																		
			Node<T> grandNode = newNode.Parent.Parent;																			
				if(grandNode.LChild.getColor().equals("RED") && 	grandNode.RChild.getColor().equals("RED"))	
				{
					grandNode.LChild.setColor("BLACK");
					grandNode.RChild.setColor("BLACK");
					if(grandNode.Parent == null)
						return;
					else
					{
						grandNode.setColor("RED");
						insertAdjust(grandNode);
					}
				}
				else	
				{
					if(grandNode.LChild.LChild == newNode)
					{
						grandNode.setColor("RED");
						parentNode.setColor("BLACK");
						rightRotate(grandNode);
						
					}
					else if(grandNode.LChild.RChild == newNode)
					{
						leftRotate(parentNode);
						grandNode.setColor("RED");
						newNode.setColor("BLACK");			
						rightRotate(grandNode);
					}
					else if(grandNode.RChild.RChild == newNode)
					{
						grandNode.setColor("RED");
						parentNode.setColor("BLACK");
						leftRotate(grandNode);
					}
					else if(grandNode.RChild.LChild == newNode)
					{
						rightRotate(parentNode);
						grandNode.setColor("RED");
						newNode.setColor("BLACK");
						leftRotate(grandNode);
					}
				}
		}
	}
	
	public void delete(T value)
	{
		Node<T> dNode = null;
		dNode = search(value);
		if(dNode.nodeValue.compareTo(value) != 0)
		{
			System.out.println("the element doesn't exist! please try again");
			return;
		}
		else
		{
			if(dNode.LChild.isLeaf == false && dNode.RChild.isLeaf == false)	
			{
				T sValue = successor(dNode);
				delete(successor(dNode));				
				dNode.nodeValue = sValue;
			}
			else		
			{
				if(dNode.getColor().equals("RED"))		
				{
					if(dNode.Parent.LChild == dNode)
					{
						if(dNode.LChild.isLeaf == false)
						{
							dNode.Parent.LChild = dNode.LChild;
							dNode.LChild.Parent = dNode.Parent;
						}
						else if(dNode.RChild.isLeaf == false)
						{
							dNode.Parent.LChild = dNode.RChild;
							dNode.RChild.Parent = dNode.Parent;
						}
						else	
						{
							dNode.Parent.LChild = dNode.LChild;
						}
						dNode.LChild = dNode.RChild = dNode.Parent = null;
					}
					else		
					{
						if(dNode.LChild.isLeaf == false)
						{
							dNode.Parent.RChild = dNode.LChild;
							dNode.LChild.Parent = dNode.Parent;
							
						}
						else if(dNode.RChild.isLeaf == false)
						{
							dNode.Parent.RChild = dNode.RChild;
							dNode.RChild.Parent = dNode.Parent;
						}
						else	
						{
							dNode.Parent.RChild = dNode.LChild;
						}
						dNode.LChild = dNode.RChild = dNode.Parent = null;
					}
				}
				else		
				{
					if(Tree == dNode)		
					{
						if(dNode.LChild.isLeaf == false)
						{
							dNode.LChild.setColor("BLACK");
							Tree = dNode.LChild;
							dNode.LChild.Parent = null;
							return;
						}
						Tree = null;		
						return;
					}
					if(dNode.LChild.isLeaf == false)	
					{
						if(dNode.Parent.LChild == dNode)
						{
							dNode.Parent.LChild = dNode.LChild;
							dNode.LChild.Parent = dNode.Parent;
						}
						else
						{
							dNode.Parent.RChild = dNode.LChild;
							dNode.LChild.Parent = dNode.Parent;
						}
						dNode.LChild.setColor("BLACK");
						dNode.LChild = dNode.RChild = dNode.Parent = null;
					}
					else if(dNode.RChild.isLeaf == false)	
					{
						if(dNode.Parent.LChild == dNode)
						{
							dNode.Parent.LChild = dNode.RChild;
							dNode.RChild.Parent = dNode.Parent;
						}
						else
						{
							dNode.Parent.RChild = dNode.RChild;
							dNode.RChild.Parent = dNode.Parent;
						}
						dNode.RChild.setColor("BLACK");
						dNode.LChild = dNode.RChild = dNode.Parent = null;
					}
					else		
					{
						if(dNode.Parent.LChild == dNode)
						{
							dNode.Parent.LChild = dNode.LChild;
							deleteBlackLeafAdjust(dNode.Parent, "LEFT");		
						}
						else
						{
							dNode.Parent.RChild = dNode.LChild;
							deleteBlackLeafAdjust(dNode.Parent, "RIGHT");		
						}
						
						dNode.LChild = dNode.RChild = dNode.Parent = null;
					}
				}
			}
		}
		System.gc();
	}

	private void deleteBlackLeafAdjust(Node<T> node, String side)
	{
		if(node.LChild.getColor().equals("RED"))	
		{
			node.setColor("RED");
			node.LChild.setColor("BLACK");
			rightRotate(node);
			deleteBlackLeafAdjust(node, side);		
		}
		else if(node.RChild.getColor().equals("RED"))		
		{
			node.setColor("RED");
			node.RChild.setColor("BLACK");
			leftRotate(node);
			deleteBlackLeafAdjust(node, side);
		}
		else	
		{
			if(side.equals("LEFT"))		
			{
				if(node.RChild.RChild.getColor().equals("RED"))		
				{
					node.RChild.RChild.setColor("BLACK");
					if(node.getColor().equals("RED"))	
					{
						node.setColor("BLACK");
						node.RChild.setColor("RED");
					}
					leftRotate(node);
					return;
				}
				else if(node.RChild.LChild.getColor().equals("RED"))	
				{
					node.RChild.setColor("RED");
					node.RChild.LChild.setColor("BLACK");
					rightRotate(node.RChild);					
					
					node.RChild.RChild.setColor("BLACK");
					if(node.getColor().equals("RED"))	
					{
						node.setColor("BLACK");
						node.RChild.setColor("RED");
					}
					leftRotate(node);
					return;
				}
				else		
				{
					if(node.getColor().equals("RED"))		
					{
						node.setColor("BLACK");
						node.RChild.setColor("RED");
						return;
					}
					else		
					{
						node.RChild.setColor("RED");
						if(node.Parent == null)		
							return;
						else
						{
							if(node.Parent.LChild == node)
								deleteBlackLeafAdjust(node.Parent, "LEFT");
							else
								deleteBlackLeafAdjust(node.Parent, "RIGHT");
						}
					}
				}
			}
			else		
			{
				if(node.LChild.LChild.getColor().equals("RED"))		
				{
					node.LChild.LChild.setColor("BLACK");
					if(node.getColor().equals("RED"))
					{
						node.setColor("BLACK");
						node.LChild.setColor("RED");
					}
					rightRotate(node);
					return;
					
				}
				else if(node.LChild.RChild.getColor().equals("RED"))	
				{
					node.LChild.setColor("RED");
					node.LChild.RChild.setColor("BLACK");
					leftRotate(node.LChild);
					
					node.LChild.LChild.setColor("BLACK");
					if(node.getColor().equals("RED"))	
					{
						node.setColor("BLACK");
						node.LChild.setColor("RED");
					}
					rightRotate(node);
					return;
				}
				else		
				{
					if(node.getColor().equals("RED"))
					{
						node.setColor("BLACK");
						node.LChild.setColor("RED");
						return;
					}
					else	
					{
						node.LChild.setColor("RED");
						if(node.Parent == null)
							return;
						else
						{
							if(node.Parent.LChild == node)
								deleteBlackLeafAdjust(node.Parent, "LEFT");
							else
								deleteBlackLeafAdjust(node.Parent, "RIGHT");
						}
					}
				}
			}
		}
		
	}

	private T successor(Node<T> dNode)
	{
		Node<T> p = null;
		p = dNode.RChild;
		while(p.LChild.isLeaf == false)
		{
			p = p.LChild;
		}
		return p.nodeValue;
	}
}
