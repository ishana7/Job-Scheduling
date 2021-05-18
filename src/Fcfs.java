import java.io.*;
import java.util.Scanner;

public class Fcfs {
	

	public static void main(String[] args) {
	  
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of process: ");
		int n = sc.nextInt();
		Process []p = new Process[n];
		
		int temp;
		float avgwt=0,avgta=0;
 
		for(int i = 0; i < n; i++)
		{
			p[i] = new Process();
			System.out.println("Enter process " + (i+1) + " Arrival time: ");
			p[i].at = sc.nextInt();
			System.out.println("Enter process " + (i+1) + " Burst time: ");
			p[i].bt = sc.nextInt();
			p[i].pid = i+1;
		}
 
		//sorting according to arrival times
		for(int i = 0 ; i <n; i++)
		{
			for(int  j=0;  j < n-(i+1) ; j++)
			{
				if( p[j].at > p[j+1].at )
				{
					temp = p[j].at;
					p[j].at = p[j+1].at;
					p[j+1].at = temp;
					temp = p[j].bt;
					p[j].bt = p[j+1].bt;
					p[j+1].bt = temp;
					temp = p[j].pid;
					p[j].pid = p[j+1].pid;
					p[j+1].pid = temp;
				}
			}
		}
		
		// finding completion times
		for(int  i = 0 ; i < n; i++)
		{
			if( i == 0)
			{	
				p[i].ct = p[i].at + p[i].bt;
			}
			else
			{
				if( p[i].at > p[i-1].ct)
				{
					p[i].ct = p[i].at + p[i].bt;
				}
				else
					p[i].ct = p[i-1].ct + p[i].bt;
			}
			p[i].tt = p[i].ct - p[i].at ;          
			p[i].wt = p[i].tt - p[i].bt ;          
			avgwt += p[i].wt ;              
			avgta += p[i].tt ;               
		}
		
		System.out.println("\nPid  Arrival  Burst  Complete TT WT");
		for(int  i = 0 ; i< n;  i++)
		{
			System.out.println(p[i].pid + "  \t " + p[i].at + "\t" + p[i].bt + "\t" + p[i].ct + "\t" + p[i].tt + "\t"  + p[i].wt ) ;
		}
		sc.close();
		System.out.println("\nAverage waiting time: "+ (avgwt/n));    
		System.out.println("Average turnaround time:"+(avgta/n));    


	}

}
