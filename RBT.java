package src;

import java.util.Scanner;

public class RBT
{

	public static void main(String[] args)
	{
		RedBlackTree<Integer> RBtree = new RedBlackTree<>();
		GUI d = new GUI(RBtree);
		Scanner sc = new Scanner(System.in);
		String input = null;
		while(true)
		{
			if(RBtree.Tree == null)
			{
				System.out.println("input 'i' to insert red black tree ");
				input = sc.nextLine();
			}
			
			if(input.equals("i"))
			{
				while(true)
				{
					System.out.println("1- Enter integer element to insert ");
					System.out.println("2- Enter 'd' to delete element ");
					System.out.println("3- Enter 'clr' to delete whole tree ");
					System.out.println("4- Enter 'p' to print the orderd tree");
					input = sc.nextLine();
					
					if(input.equals("d")) {
						try
						{
							System.out.println("Enter integer element to delete");
							input = sc.nextLine();
							int temp = Integer.parseInt(input);
							RBtree.delete(temp);
							d.repaint();
						}catch(NumberFormatException e){
							System.out.println("input type error! please try again");
						}catch(NullPointerException e) {
							System.out.println("the element doesn't exist! please try again");
						}
					}
					
					else if(input.equals("clr"))
					{
						RBtree.Tree = null;
						d.repaint();
						break;
					}
					
					else if (input.equals("p")) {
						if (RBtree.Tree == null)
							System.out.println("the tree is empty please insert an element");
						else
							RBtree.print();
					}
					
					else
					{
						try
						{
							int temp = Integer.parseInt(input);
							RBtree.insert(temp);
							d.repaint();
						}catch(NumberFormatException e)
						{
							System.out.println("input type error! please try again");
						}
					}
				}
			}
			
			else
				System.out.println("input error! please try again");
			
		}
	}
}
