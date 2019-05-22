import java.awt.Toolkit;

public class Timer extends Thread {
	private static int hours;
	private static int minutes;
	private static int seconds;
	
	private static int runState =0;
	private static int zeroState=0;
	
	private Thread thread;
	
	private int time =0;
	
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
	
	public void startUpdateTime(Thread timeThread) {
		timeThread.start();
	}
	
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
		thread.interrupt();
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
		thread = new Timer(this.getTime());
		this.runState=1;
		startUpdateTime(thread);
	}
	
	public void resetTimer() {
		setTimer("0 0 0");
		zeroState=1;
	}
	
	public void buzzTimer() {
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		toolkit.beep();
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
		}
		
	}
	
	public void run() {
		while(true) {
			try {
				if(this.time==0)
					System.out.println(this.getTime());
				
				updateTime();
				
				if(this.zeroState==1)
					break;
					
				Thread.sleep(100);
			}catch (InterruptedException e) {break;}
		}
		
	}
	
}
