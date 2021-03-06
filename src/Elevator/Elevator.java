package Elevator;

import Schedualer.Scheduler;
import Floor.RequestElevatorEvent;

/**
 * 
 * @author 
 * @version 02/06/2021
 */
public class Elevator implements Runnable {
	
	private Scheduler scheduler;
	
	public Elevator(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	@Override
	public void run() {
		System.out.println("Starting floor elevator");
		while(true) {
			RequestElevatorEvent job = scheduler.getJob();
			System.out.println(Thread.currentThread() + " is serving job " + job.toString());
			scheduler.sendElevatorInfo(new ElevatorInfo(job.getTime(), job.getCurrentfloornumber(), job.getDirection(), job.getDestinationfloornumber()));
		}
	}

}
