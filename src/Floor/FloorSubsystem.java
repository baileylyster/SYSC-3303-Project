package Floor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Schedualer.Scheduler;

/**
 * A class to handle the input from a file listing all the floors the elevator(s) have to visit
 * @author 
 * @version 02/06/2021
 *
 */
public class FloorSubsystem implements Runnable{

    List<RequestElevatorEvent> floorjobs;
    Scheduler schedular;

    public FloorSubsystem(Scheduler schedular) {
    	this.schedular = schedular;
    	this.floorjobs = new ArrayList<>();
        parse();
    }

    @Override
    public void run() {
    	System.out.println("Starting floor run");
        for(RequestElevatorEvent job: floorjobs) {
        	schedular.requestElevator(job);
        	System.out.println(Thread.currentThread() + "has noticed that the job " + schedular.getElevatorInfo().toString());
        }
    }

    public void parse() {
        File simulation = new File("./src/input.txt");
        try {
            Scanner sc = new Scanner(simulation);
            while(sc.hasNextLine()) {
            	floorjobs.add(new RequestElevatorEvent(sc.nextLine()));
            }
            sc.close();
            }
        catch (FileNotFoundException ex) {
            System.out.println("Error: File not found.");
        }
    }

}