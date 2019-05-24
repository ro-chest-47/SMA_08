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

	private Stopwatch() {

	}

	private Stopwatch(String time) {
		setStopwatch(time);
	}

	public static Stopwatch getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final Stopwatch INSTANCE = new Stopwatch();
	}

	/*    public void startUpdateTime(Thread timeThread) {
            timeThread.start();
        }
    */
	public int getRunState() {
		return this.runState;
	}

	public int getZeroSate() {
		return this.zeroState;
	}

	public String getTime() {
		String time = Integer.toString(this.hours) + " " + Integer.toString(this.minutes) + " " + Integer.toString(this.seconds) + " " + Integer.toString(this.times);

		return time;
	}

	public void setStopwatch(String time) {
		String[] times = time.split("\\s");

		this.hours = Integer.parseInt(times[0]);
		this.minutes = Integer.parseInt(times[1]);
		this.seconds = Integer.parseInt(times[2]);
		this.times = Integer.parseInt(times[3]);
	}

	public void startStopwatch() {
		service = Executors.newSingleThreadScheduledExecutor();
		runnable = new Stopwatch(getTime());
		thread = new Thread(runnable);
		service.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.MILLISECONDS);
		this.runState = 1;
		//startUpdateTime(thread);
	}

	public String recordStopwatch() {
		String time = getTime();

		return time;
	}

	public void pauseStopwatch() {
		service.shutdown();
		this.runState = 0;
		if (getTime().equals("0 0 0 0"))
			zeroState = 1;
	}

	public void resetStopwatch() {
		setStopwatch("0 0 0 0");
		zeroState = 1;
	}

	public void updateTime() {
		this.times++;
		if (this.times == 100) {
			this.times = 0;
			this.seconds++;
		}
		if (this.seconds == 60) {
			this.seconds = 0;
			this.minutes++;
		}
		if (this.minutes == 60) {
			this.minutes = 0;
			this.hours++;
		}
	}

	//@Override
	public void run(){
		updateTime();
		//System.out.println(this.getTime());


	}

}
