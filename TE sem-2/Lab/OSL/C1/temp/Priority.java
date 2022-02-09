import java.util.*;

public class Priority{

	

	public static void main(String args[]){
		Scanner sc;
		sc = new Scanner(System.in);

		System.out.println("Enter the Number of Processes: ");
		int numProcess = sc.nextInt();
		Process []process = new Process[numProcess];

		for(int i=0;i<numProcess;i++){
			System.out.println("P("+(i+1)+"): Enter Burst time & priority");
			int at = 0;
			int bt = sc.nextInt();
			int priority = sc.nextInt();

			process[i] = new Process("P"+(i+1),bt,at,priority);
		}

		Arrays.sort(process,new SortByPriority());
		int sum=0;
		double avgWT=0,avgTAT=0;
		System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT\tPR");
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
		System.out.println("Average Waiting Time: "+avgWT);
		System.out.println("Average TAT: "+avgTAT);

	}
}