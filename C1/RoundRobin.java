import java.util.Arrays;
import java.util.Scanner;

public class RoundRobin {

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
		Arrays.sort(process,new SortByArrival()); //sort according to arrival time

		System.out.println("Enter Quantum Time: ");
		int quantum=sc.nextInt();
		
		double avgWT=0,avgTAT=0;
		int time=0;
		System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT\tPR");
		System.out.println("============================================================================================");
		while(true)
		{
			boolean done=true;
			for(int i=0;i<numProcess;i++)
			{
				if(process[i].remBT>0 && process[i].AT<=time)
				{
					done=false;
					
					if(process[i].remBT>quantum) //Ajun time remaining aahe :v
					{
						time=time+quantum;
						process[i].remBT=process[i].remBT-quantum;
						System.out.println(i+" TIME "+time);
						
					}
					else //process will finish execution
					{
						
						time+=process[i].remBT;
						System.out.println(i+" TIME "+time);

						process[i].remBT=0;
						process[i].CT=time;
						process[i].TAT=process[i].CT-process[i].AT;
						process[i].WT=process[i].TAT-process[i].BT;
						avgWT+=process[i].WT;
						avgTAT+=process[i].TAT;
						process[i].display();
					}
				}
				/*else //no process is arrived currently
				{
					time++;
					
				}*/
				
			}
			if(done==true) //done executing all processes
			{
				break;
			}

		}


	}

}
/*
Enter Number of Processes:
4
P(1):Enter Arrival time & Burst time
0 8
P(2):Enter Arrival time & Burst time
1 4
P(3):Enter Arrival time & Burst time
2 9
P(4):Enter Arrival time & Burst time
3 5
Enter Quantum Time: 
4


PRNo	BT	AT	CT	TAT	WT	PR
============================================================================================
0 TIME 4
1 TIME 8
P2	4	1	8	7	3	0
2 TIME 12
3 TIME 16
0 TIME 20
P1	8	0	20	20	12	0
2 TIME 24
3 TIME 25
P4	5	3	25	22	17	0
2 TIME 26
P3	9	2	26	24	15	0

