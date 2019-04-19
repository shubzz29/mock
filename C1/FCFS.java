import java.util.Arrays;
import java.util.Scanner;

public class FCFS {

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

		//Sorting processes according to Arrival Time //No need if you take AT=0 or in ascending order
		Arrays.sort(process,new SortByArrival());

		int sum=0;
		double avgWT=0,avgTAT=0;
		System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT");
		System.out.println("============================================================================================");
		for(int i=0;i<numProcess;i++)
		{
			sum=process[i].CT=sum+process[i].BT;
			process[i].TAT=process[i].CT-process[i].AT;
			process[i].WT=process[i].TAT-process[i].BT;

			avgWT=avgWT+process[i].WT;
			avgTAT=avgTAT+process[i].TAT;

			process[i].display();
		}
		avgTAT=(double)avgTAT/numProcess;
		avgWT=(double)avgWT/numProcess;
		System.out.println("Average Waiting Time"+avgWT);
		System.out.println("Average TAT="+avgTAT);
	}

}
/*
Enter Number of Processes:
5
P(1):Enter Arrival time & Burst time
0 4
P(2):Enter Arrival time & Burst time
1 3
P(3):Enter Arrival time & Burst time
2 1
P(4):Enter Arrival time & Burst time
3 2
P(5):Enter Arrival time & Burst time
4 5


PRNo	BT	AT	CT	TAT	WT
============================================================================================
P1	4	0	4	4	0	0
P2	3	1	7	6	3	0
P3	1	2	8	6	5	0
P4	2	3	10	7	5	0
P5	5	4	15	11	6	0
Average Waiting Time3.8
Average TAT=6.8

