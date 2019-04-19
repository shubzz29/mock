import java.util.ArrayList;
import java.util.Scanner;
public class LRUandOPT 
{
	String ref="";
	int capacity=0;
	int pageHits=0,pageFaults=0;
	
	public static void main(String [] args) throws Exception
	{
		LRUandOPT obj = new LRUandOPT();
		obj.input();
		
	}
	public void input() throws Exception
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter refrence String ");
		 ref = sc.nextLine();
		System.out.println("Enter Frame size");
		 capacity = sc.nextInt();
		char frame[] = new char[capacity];
		System.out.println("Choose page replacement algo ");
		System.out.println("1 : For LRU ");
		System.out.println("2 : For OPT ");
		int choice = sc.nextInt();
		
		if(choice==1) {
			LRU();
		}
		else {
			OPT();
		}
	sc.close();
	}
	public  void LRU()
	{
		ArrayList<Character> al= new ArrayList<>(capacity);//frame
		for(int i=0;i<ref.length();i++)
		{
			System.out.println("iteration "+i+" "+al);
			Character temp = ref.charAt(i);
			if(al.contains(temp))
			{
				
				pageHits++;
				al.remove(temp);
				al.add(temp);
			}
			else
			{
				pageFaults++;
				if(al.size()<capacity)
				{					
					al.add(temp);
				}
				else
				{
					
					al.remove(0);
					al.add((Character)temp);
				}
			}
		}
		System.out.println("no of page falults = "+pageFaults);
		System.out.println("no of page hits = "+pageHits);
	}

	
	public  int predict(ArrayList<Character> al,int ch ,int i)
	{
		int index=ref.length()+1;
		
		for(int j=i+1;j<ref.length();j++)
		{
			Character temp = ref.charAt(j);
			if(ch==temp && j<index)
				index = j;
		}
		
		return index;
		
	}
	public  void OPT()
	{
		ArrayList<Character> al = new ArrayList<Character>(capacity);
		for(int i=0;i<ref.length();i++)
		{
			System.out.println("iteration "+i+" "+al);
			Character temp = ref.charAt(i);
			if(al.contains(temp))
				pageHits++;
			else
			{
				pageFaults++;
				if(al.size()<capacity)
				{					
					al.add(temp);
				}
				else
				{
					int optimal[] = new int[al.size()];
					for(int j=0;j<al.size();j++)
					{
						optimal[j] = predict(al,al.get(j),i);
					}
					int max = optimal[0];
					int index =0;
					for(int k=0;k<optimal.length;k++)
					{
						if(max<optimal[k])
						{
							max = optimal[k];
							index = k;
						}
						 
					}
					System.out.println(index);
					al.remove(index);
					al.add(temp);
				}
			}
		}
		System.out.println("No of page Hits "+pageHits);
		System.out.println("No of page Faults "+pageFaults);
	}

}
