import javafx.scene.paint.Stop;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Stopwatch implements Runnable {
	private static int hours;
	private static int minutes;
	private static int seconds;

	private int runState = 0;
	private int zeroState = 0;

	private static int times = 0;

	private Thread thread;
	private Stopwatch runnable;
	private ScheduledExecutorService service;

	private static Stopwatch instance;

	private Stopwatch() {
		hours=0;
		minutes=0;
		seconds=0;
		runState=0;
		zeroState=0;
		times=0;
	}

	public static Stopwatch getInstance(){
		if(instance==null){
			instance=new Stopwatch();
		}
		return instance;
	}

	public static void deleteInstance(){
		instance=null;
	}

	private Stopwatch(String time) {
		String[] timeS = time.split("\\s");

		hours = Integer.parseInt(timeS[0]);
		minutes = Integer.parseInt(timeS[1]);
		seconds = Integer.parseInt(timeS[2]);
		times = Integer.parseInt(timeS[3]);
	}

	public int getRunState() {
		return runState;
	}

	public int getZeroSate() {
		return zeroState;
	}

	public String getTime() {
		String time = hours + " " + minutes+ " " +seconds+ " " + times;

		return time;
	}

	public void setStopwatch(String time) {
		String[] timeS = time.split("\\s");

		hours = Integer.parseInt(timeS[0]);
		minutes = Integer.parseInt(timeS[1]);
		seconds = Integer.parseInt(timeS[2]);
		times = Integer.parseInt(timeS[3]);
	}

	public void startStopwatch() {
		service = Executors.newSingleThreadScheduledExecutor();
		runnable = new Stopwatch(getTime());
		thread = new Thread(runnable);
		service.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.MILLISECONDS);
		runState = 1;
		//startUpdateTime(thread);
	}

	public String recordStopwatch() {
		String time = getTime();

		return time;
	}

	public void pauseStopwatch() {
		service.shutdown();
		runState = 0;
		if (getTime().equals("0 0 0 0")) {
			zeroState = 1;
		}
	}

	public void resetStopwatch() {
		setStopwatch("0 0 0 0");
		zeroState = 1;
	}

	public void updateTime() {
		times++;
		if (times == 100) {
			times = 0;
			seconds++;
		}
		if (seconds == 60) {
			seconds = 0;
			minutes++;
		}
		if (minutes == 60) {
			minutes = 0;
			hours++;
		}
	}

	//@Override
	public void run(){
		updateTime();
		//System.out.println(this.getTime());


	}

}
