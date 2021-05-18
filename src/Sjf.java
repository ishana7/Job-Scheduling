import java.util.*;
import java.io.*;

public class Sjf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
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
	    sc.close();

	}

}
