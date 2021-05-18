import java.util.*;
import java.io.*;
public class ProcSched {
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int ans;
	    
	    do {
	    System.out.println("Choose Algorithm For Scheduling:\n1.FCFS\n2.SJF\n3.Priority\n4.Round Robin");
	    System.out.print("\nEnter your choice:\t");
	    int ch=sc.nextInt();
	    switch(ch)
	    {
	    case 1:
	    {
	    	System.out.println("FCFS Algorithm");
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

	    	break;
	    }//FCFS END
	    //---------------------------------------------------------------------------------------
	    case 2:
	    {
	    	System.out.println("SJF Algorithm");
	    	System.out.println("Enter the number of process: ");
			int n = sc.nextInt();
			Process []p = new Process[n];
			int f[] = new int[n];
			int k[] = new int[n];
			int temp,st=0,tot=0;
			float avgwt=0,avgtt=0;
	 
			for(int i = 0; i < n; i++)
			{
				p[i] = new Process();
				System.out.println("Enter process " + (i+1) + " Arrival time: ");
				p[i].at = sc.nextInt();
				System.out.println("Enter process " + (i+1) + " Burst time: ");
				p[i].bt = sc.nextInt();
				p[i].pid = i+1;
				k[i]= p[i].bt;
				f[i]=0;
			}
			
			while(true)
		    {
		    	int min=99,c=n;
		    	if (tot==n)
		    	{	System.out.println("END");
		    		break;}
		    	
		    	for (int i=0;i<n;i++)
		    	{
		    		if ((p[i].at<=st) && (f[i]==0) && (p[i].bt<min))
		    		{	
		    			min=p[i].bt;
		    			c=i;
		    		}
		    	}
		    	System.out.print("P"+p[c].pid+"-->");
		    	if (c==n)
		    		st++;
		    	else
		    	{
		    		p[c].bt--;
		    		st++;
		    		if (p[c].bt==0)
		    		{
		    			p[c].ct= st;
		    			f[c]=1;
		    			tot++;
		    		}
		    	}
		    }
		    
		    for(int i=0;i<n;i++)
		    {
		    	p[i].tt = p[i].ct - p[i].at;
		    	p[i].wt = p[i].tt - k[i];
		    	avgwt+= p[i].wt;
		    	avgtt+= p[i].tt;
		    }
		    
		    System.out.println("Pid  Arrival  Burst  Complete Waiting Turn");
		    for(int i=0;i<n;i++)
		    {
		    	System.out.println(p[i].pid +"\t"+ p[i].at+"\t"+ k[i] +"\t"+ p[i].ct +"\t"+ p[i].wt+"\t"+ p[i].tt );
		    }
		    
		    System.out.println("\nAverage Waiting Time is "+ (float)(avgwt/n));
		    System.out.println("Average Turn around Time is "+ (float)(avgtt/n));
		   
	    	break;
	    }//SJF END
	    //-------------------------------------------------------------------------------------------
	    case 3:
	    {
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
			}										
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

	    	break;
	    }
	    case 4:
	    {
	    	System.out.println("Round Robin Algorithm");
			System.out.println ("Enter the Number of Process:");
			int n= sc.nextInt();
	    	System.out.println("Enter Time Quantum:");
	    	int a=sc.nextInt();
			Process []p=new Process[n];
			float avgwt=0,avgtt=0;	
			int f[] = new int[n];  
			int k[]= new int[n]; 
			
		    int i,temp, st=0, tot=0;
		    int time=0,q_count;
		    
		    q_count=1;
		    int j,flag=0,count=0,p_process=0;

		    for (i=0;i<n;i++)
		    {
		    	p[i]=new Process();
		    	p[i].pid= i+1;
		    	System.out.println ("Enter process " +(i+1)+ " Arrival time and Burst Time:");
		    	p[i].at= sc.nextInt();
		    	p[i].bt= sc.nextInt();
		    	f[i]= 0;
		    }
	    	avgwt=0;avgtt=0;

	    	for(i = 0 ; i <n; i++)
			{
				for( j=0;  j < n-(i+1) ; j++)
				{
					if( p[j].at > p[j+1].at )
					{
						temp = p[j].at;
						p[j].at = p[j+1].at;
						p[j+1].at = temp;
						temp = p[j].bt;
						p[j].bt= p[j+1].bt;
						p[j+1].bt = temp;
						temp = p[j].pid;
						p[j].pid =p[j+1].pid;
						p[j+1].pid = temp;
					}
				}
			}
		    
		    st=p[0].at+p[0].bt;
		    for(i=1;i<n;i++)
		    {
		    	if(p[i].at>st)
		    		st=p[i].at;
		    	st+=p[i].bt;
		    }
		    
		    for(i=0;i<n;i++)
		    {
		    	k[i]=p[i].bt;
		    }
		    
		    time=p[0].at;
		    j=0;
		    q.add(j++);
		    while(time<=st)
		    {
		    	if(flag==1||q_count!=0)
		    	{
		    		if(flag==0 && count==0)
		    		{
		    			p_process=q.remove();
		    			count=0;
		    			flag=1;
		    		}
		    		k[p_process]--;
		    		 //System.out.println("process completed "+k[p_process]+" "+ time);
		    		System.out.print("P"+(p_process+1)+"-->");
		    		if(k[p_process]==0)
		    		{  //System.out.println("P"+(p_process+1)+"-->");
		    			time++;
		    			count=0;
		    			p[p_process].ct=time;
		    			flag=0;
		    			q_count--;
		    			
		    			while(j<n && p[j].at<=time)
		    			{ 
		    				q_count++;
		    				q.add(j++);
		    			}
		    			continue;
		    		}
		    		count++;
		    		if(count==a)
		    		{
		    			count=0;
		    			time++;
		    			while(j<n && p[j].at<=time)
		    			{
		    				q_count++;
		    				q.add(j++);
		    			}
		    			q.add(p_process);
		    			flag=0;
		    		}
		    		else
		    		{
		    			time++;
		    			
		    			while(j<n && p[j].at<=time)
		    			{  
		    				q_count++;
		    				q.add(j++);
		    			}
		    		}
		    		
		    	}
		    	else
		    	{
		    		time++;
		    		while(j<n && p[j].at<=time  )
	    			{
	    				q_count++;
	    				q.add(j++);
	    			}
		    	}
		    	
		    }
		    
		    for(i=0;i<n;i++)
		    {
		    	p[i].tt=p[i].ct-p[i].at;
		    	p[i].wt=p[i].tt-p[i].bt;
		    	avgwt+= p[i].wt;
		    	avgtt+= p[i].tt;
		    }
		    System.out.print("END");
		    System.out.println("\nPid  Arrival  Burst  Complete Waiting Turn");
		    for(i=0;i<n;i++)
		    {
		    	System.out.println(p[i].pid +"\t"+ p[i].at+"\t"+ p[i].bt +"\t"+ p[i].ct +"\t"+ p[i].wt+"\t"+ p[i].tt );
		    }
		    
		    System.out.println("\nAverage Waiting Time is "+ (float)(avgwt/n));
		    System.out.println("Average Turn around Time is "+ (float)(avgtt/n));

	    	break;
	    }//RR END
	    //==-------------------------------------------------------------------------------------
	    default:
	    	System.out.println("Invalid Choice!!!");
	    }
	    
	    System.out.print("\n\nDo You want to try another algorithm,\n For the same Process?(if Yes Press 1):\t");
	    ans=sc.nextInt();
	    }while(ans==1);
	    sc.close();
	    System.out.println("####END####");
	}
	

}
