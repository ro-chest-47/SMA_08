import java.awt.Toolkit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Timer extends Thread {
	private static int hours;
	private static int minutes;
	private static int seconds;

	private static int runState =0;
	private static int zeroState=0;

	private Thread thread;
	private Timer runnable;
	private ScheduledExecutorService service;

	private int time =0;

	private class Buzzer extends Thread{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		public void run() {
			for(int i =0;i<3; i++) {
				toolkit.beep();
				try {
					Thread.sleep(1000);
				}catch (InterruptedException e) {}
			}

		}

	}

	private Timer() {
		setTimer("0 0 0");
	}

	private Timer(String time) {
		setTimer(time);
	}

	public static Timer getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder{
		private static final Timer INSTANCE = new Timer();
	}
/*
	public void startUpdateTime(Thread timeThread) {
		timeThread.start();
	}*/

	public int getRunState() {
		return this.runState;
	}

	public int getZeroState() {
		return this.zeroState;
	}

	public String getTime() {
		String time= Integer.toString(hours)+" "+Integer.toString(minutes)+" "+Integer.toString(seconds);

		return time;
	}

	public void pauseTimer() {
		service.shutdown();
		//System.out.println(this.getTime());
		this.runState=0;
		if(this.getTime().equals("0 0 0"))
			zeroState=1;
	}

	public void setTimer(String time) {
		String[] times = time.split("\\s");

		this.hours=Integer.parseInt(times[0]);
		this.minutes=Integer.parseInt(times[1]);
		this.seconds=Integer.parseInt(times[2]);

		if(time.equals("0 0 0")) {
			zeroState=1;
		}
		else zeroState=0;
	}

	public void startTimer() {
		service = Executors.newSingleThreadScheduledExecutor();
		runnable = new Timer(getTime());
		thread = new Thread(runnable);
		service.scheduleAtFixedRate(runnable, 0, 100, TimeUnit.MILLISECONDS);
		this.runState=1;
		//startUpdateTime(thread);
	}

	public void resetTimer() {
		setTimer("0 0 0");
		zeroState=1;
	}

	public void buzzTimer() {
		Buzzer buzzer = new Buzzer();

		buzzer.start();
	}

	public void updateTime() {
		this.time++;
		if(this.time==10) {
			this.time=0;
			this.seconds--;
		}

		if(seconds<0) {
			this.seconds+=60;
			this.minutes--;
		}
		if(minutes<0) {
			this.minutes+=60;
			this.hours--;
		}
		if(hours<0) {
			setTimer("0 0 0");
			buzzTimer();
			pauseTimer();
		}

	}

	public void run(){
		updateTime();
		//System.out.println(this.getTime());


	}

}
