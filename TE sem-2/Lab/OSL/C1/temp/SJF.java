import java.util.*;

class SJF{
	private Scanner sc;
	public void execute(){
		sc = new Scanner(System.in);
		System.out.println("Enter the no of Processes:");
		int numProcess = sc.nextInt();
		Process process[] = new Process[numProcess];

		for(int i=0;i<numProcess;i++){
			System.out.println("P"+(i+1)+" Enter Arrival time :");
			int arrival_time = sc.nextInt();
			System.out.println("P"+(i+1)+" Enter Burst time :");
			int burst_time = sc.nextInt();

			process[i] = new Process("P"+(i+1),burst_time,arrival_time);
		}

		int min = Integer.MAX_VALUE;
		int count = 0, shortest = 0;
		int time = 0;
		int sum = 0;
		double avg_wait_time = 0, avg_turn_around_time = 0;
		
	}

}