import java.util.*;

class FCFS {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of Processes: ");
		int n = sc.nextInt();
		Process []process = new Process[n];

		for(int i=0;i<n;i++){
			System.out.print("Arrival time of P"+(i+1)+" :");
			int arrival_time = sc.nextInt();
			System.out.print("Burst time of P"+(i+1)+" :");
			int burst_time = sc.nextInt();
			process[i] = new Process("P"+(i+1),burst_time,arrival_time);
		}
		
		Arrays.sort(process,new SortByArrival());

		int sum=0;
		double avg_wait_time=0,avg_turn_around_time= 0;
		System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT");
		System.out.println("==================================================");
		for(int i=0;i<n;i++)
		{
			sum=process[i].CT = sum + process[i].BT;
			process[i].TAT = process[i].CT - process[i].AT;
			process[i].WT = process[i].TAT - process[i].BT;

			avg_wait_time = avg_wait_time + process[i].WT;
			avg_turn_around_time = avg_turn_around_time + process[i].TAT;

			process[i].display();
		}
		avg_wait_time = (double)avg_wait_time/n;
		avg_turn_around_time = (double)avg_turn_around_time/n;
		System.out.println("Average Waiting Time " + avg_wait_time);
		System.out.println("Average Turn around Time " + avg_turn_around_time);
	}
}