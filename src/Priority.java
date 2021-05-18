import java.io.*;
import java.util.Scanner;

public class Priority {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of process: ");
		int n = sc.nextInt();
		Process []p = new Process[n];
		int temp;
		float avgwt=0,avgtt=0;
		for(int i = 0; i < n; i++)
		{
			p[i] = new Process();
			System.out.println("Enter process " + (i+1) + " Arrival time: ");
			p[i].at = sc.nextInt();
			System.out.println("Enter process " + (i+1) + " Burst time: ");
			p[i].bt = sc.nextInt();
			System.out.println("Enter process " + (i+1) + " Priority: ");
			p[i].pr = sc.nextInt();
			p[i].pid = i+1;
		}
		  
		//sorting according to arrival times
		for(int i = 0 ; i <n; i++)
		{
			for(int  j=0;  j < n-(i+1) ; j++)
			{
				if( p[j].at == p[j+1].at )
				{
					if( p[j].pr > p[j+1].pr )
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
						temp = p[j].pr;
						p[j].pr = p[j+1].pr;
						p[j+1].pr = temp;
					}
					
				}
				else if(p[j].at > p[j+1].at)
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
					temp = p[j].pr;
					p[j].pr = p[j+1].pr;
					p[j+1].pr = temp;
				}
			}
		}
		
		for(int i=0;i<n;i++)
		{
			if(i==0)
			{
				p[i].ct = p[i].at + p[i].bt;
				p[i].st = p[i].at;
				p[i].wt = 0;
				p[i].tt = p[i].bt + p[i].wt;
				System.out.print("P"+p[i].pid+"-->");
			}
			else
			{
				int a = p[i-1].ct;
				int [] l=new int[n-i];
				for(int  m=0,j=i;  j < n ; j++)
				{
					if(p[j].at<=p[i-1].ct)
					{
						l[m]=p[j].pr;
						m++;
					}
				}
				for(int m=0;m<l.length;m++)
				{
					if(l[m]==0)
					{
						l[m]=100;
					}
				}
				
				for(int  j=0;  j < n-i ; j++)
				{
					for(int  k=0;  k < n-i-(j+1) ; k++)
					{
						if(l[k]!=0)
						{
						if(l[k]>l[k+1])
						{
							temp = l[k];
							l[k] = l[k+1];
							l[k+1] = temp;
						}
						}
					}
				}
		
				for(int j=i;j<n;j++)
				{
					if(l[0]==p[j].pr)
					{
						temp = p[i].at;
						p[i].at = p[j].at;
						p[j].at = temp;
						temp = p[i].bt;
						p[i].bt = p[j].bt;
						p[j].bt = temp;
						temp = p[i].pid;
						p[i].pid = p[j].pid;
						p[j].pid = temp;
						temp = p[i].pr;
						p[i].pr = p[j].pr;
						p[j].pr = temp;
					}
				}
				System.out.print("P"+p[i].pid+"-->");
				p[i].st=a;
				p[i].wt=p[i].st-p[i].at;
				p[i].ct = p[i].st + p[i].bt;
				p[i].tt=p[i].bt+p[i].wt;
				
			}
		}										//Sorting acc priority and calculation
		
		for(int  i = 0 ; i < n; i++)
		{         
			avgwt += p[i].wt;               
			avgtt += p[i].tt ;              
		}
		System.out.print("END");
		System.out.println("\nPid  Arrival  Burst  Starttime   Complete    TT    WT");
		for(int  i = 0 ; i< n;  i++)
		{
			System.out.println(p[i].pid + "  \t " + p[i].at + "\t" + p[i].bt + "\t" + p[i].st + "\t" + p[i].ct + "\t" + p[i].tt + "\t"  + p[i].wt ) ;
		}
		sc.close();
		System.out.println("\nAverage waiting time: "+ (avgwt/n));    
		System.out.println("Average turnaround time:"+(avgtt/n));    

			
		}

	}
	
