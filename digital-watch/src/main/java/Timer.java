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

	private int times =0;

	private static class Buzzer extends Thread{
		private Toolkit toolkit = Toolkit.getDefaultToolkit();
		public void run() {
			for(int i =0;i<3; i++) {
				toolkit.beep();
				try {
					Thread.sleep(1000);
				}catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

		}

	}

//	private Timer() {
//		setTimer("0 0 0");
//	}
//
//	private Timer(String time) {
//		setTimer(time);
//	}
//
//	public static Timer getInstance() {
//		return LazyHolder.INSTANCE;
//	}
//
//	private static class LazyHolder{
//		private static final Timer INSTANCE = new Timer();
//	}
private static Timer instance;

	private Timer(){

		hours = 0;
		minutes=0;
		seconds=0;

		zeroState=1;
	}

	private Timer(String time){
		String[] timeS = time.split("\\s");

		hours=Integer.parseInt(timeS[0]);
		minutes=Integer.parseInt(timeS[1]);
		seconds=Integer.parseInt(timeS[2]);

		if(time.equals("0 0 0")) {
			zeroState=1;
		}
		else {zeroState=0;}
	}

	public static Timer getInstance(){
		if(instance==null){
			instance=new Timer();
		}
		return instance;
	}

	public static void deleteInstance(){
		instance=null;
	}


	/*
	public void startUpdateTime(Thread timeThread) {
		timeThread.start();
	}*/

	public int getRunState() {
		return runState;
	}

	public int getZeroState() {
		return zeroState;
	}

	public String getTime() {
		String time= hours +" "+minutes+" "+seconds;

		return time;
	}

	public void pauseTimer() {
		service.shutdown();
		//System.out.println(this.getTime());
		runState=0;
		if(getTime().equals("0 0 0")) {
			zeroState = 1;
		}
	}

	public void setTimer(String time) {
		String[] timeS = time.split("\\s");

		hours=Integer.parseInt(timeS[0]);
		minutes=Integer.parseInt(timeS[1]);
		seconds=Integer.parseInt(timeS[2]);

		if(time.equals("0 0 0")) {
			zeroState=1;
		}
		else {zeroState=0;}
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
		times++;
		if(times==10) {
			times=0;
			seconds--;
		}

		if(seconds<0) {
			seconds+=60;
			minutes--;
		}
		if(minutes<0) {
			minutes+=60;
			hours--;
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
