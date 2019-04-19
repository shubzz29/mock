import java.util.Arrays;
import java.util.Scanner;

import javax.swing.text.html.MinimalHTMLWriter;

public class SJF {

	public static void main(String args[])
	{
		Scanner sc;
		sc = new Scanner(System.in);

		//--------FCFS
		System.out.println("Enter Number of Processes:");
		int numProcess=sc.nextInt();
		Process []process=new Process[numProcess];

		//Accept Input
		for(int i=0;i<numProcess;i++)
		{
			System.out.println("P("+(i+1)+"):Enter Arrival time & Burst time");
			int at=sc.nextInt();
			int bt=sc.nextInt();
			//System.out.println("P("+(i+1)+"):Enter Arrival time");

			process[i]=new Process("P"+(i+1), bt, at);
		}

		int min=Integer.MAX_VALUE;
		int count=0,shortest=0;
		int time=0;
		int sum=0;
		double avgWT=0,avgTAT=0;
		boolean check=false;
		System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT");
		System.out.println("============================================================================================");
		while(count<numProcess)
		{
		    //	check=false;//remove this if given wrong i=output
			//find shortest process till time
			for(int i=0;i<numProcess;i++)
			{
				
				if(process[i].AT<=time &&(process[i].remBT<min && process[i].remBT>0))
				{
					shortest=i;
					min=process[i].remBT;
					check=true;
				}
			}
				if(check==false) //No process is present currently
				{
					time++;
					continue;
				}
				process[shortest].remBT--;
				min=process[shortest].remBT;
				
				if(min==0) //process completes its execution
				{
					min=Integer.MAX_VALUE;
					count++;
					sum=time+1;
					process[shortest].CT=sum;
					process[shortest].TAT=process[shortest].CT-process[shortest].AT;
					process[shortest].WT=process[shortest].TAT-process[shortest].BT;
					//if(process[shortest].WT<0)
					//	process[shortest].WT=0; 
					avgWT=avgWT+process[shortest].WT;
					avgTAT=avgTAT+process[shortest].TAT;

					process[shortest].display();
				}
				time++;
				
			
		}
		
		avgTAT=(double)avgTAT/numProcess;
		avgWT=(double)avgWT/numProcess;
		System.out.println("Average Waiting Time"+avgWT);
		System.out.println("Average TAT="+avgTAT);
	}
}
/*
Enter Number of Processes:
6
P(1):Enter Arrival time & Burst time
0 7 
P(2):Enter Arrival time & Burst time
1 5
P(3):Enter Arrival time & Burst time
2 3
P(4):Enter Arrival time & Burst time
3 1
P(5):Enter Arrival time & Burst time
4 2 
P(6):Enter Arrival time & Burst time
5 1


PRNo	BT	AT	CT	TAT	WT
============================================================================================
P4	1	3	4	1	0	0
P3	3	2	6	4	1	0
P6	1	5	7	2	1	0
P5	2	4	9	5	3	0
P2	5	1	13	12	7	0
P1	7	0	19	19	12	0
Average Waiting Time4.0
Average TAT=7.166666666666667
*/
