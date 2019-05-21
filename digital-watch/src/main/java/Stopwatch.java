public class Stopwatch extends Thread{
	private static int hours;
	private static int minutes;
	private static int seconds;
	
	private int runState =0;
	private int zeroState=0;
	
	private static int times = 0;
	
	private Thread thread;
	
	public Stopwatch() {

	}
	
	public Stopwatch(String time) {
		setStopwatch(time);
	}
	
	public void startUpdateTime(Thread timeThread) {
		timeThread.start();
	}
	
	public int getRunState() {
		return this.runState;
	}
	
	public int getZeroSate() {
		return this.zeroState;
	}
	
	public String getTime() {
		String time= Integer.toString(hours)+" "+Integer.toString(minutes)+" "+Integer.toString(seconds)+" "+Integer.toString(times);
		
		return time;
	}
	
	public void setStopwatch(String time) {
		String[] times = time.split("\\s");

		this.hours=Integer.parseInt(times[0]);
		this.minutes=Integer.parseInt(times[1]);
		this.seconds=Integer.parseInt(times[2]);
		this.times=Integer.parseInt(times[3]);
	}
	
	public void startStopwatch() {
		thread= new Stopwatch(this.getTime());
		this.runState=1;
		startUpdateTime(thread);
	}
	
	public String recordStopwatch() {
		String time = getTime();		
		
		return time;
	}
	
	public void pauseStopwatch() {
		thread.interrupt();
		this.runState=0;
		if(getTime().equals("0 0 0 0"))
			zeroState=1;
	}
	
	public void resetStopwatch() {
		setStopwatch("0 0 0 0");
		/*this.hours=0;
		this.minutes=0;
		this.seconds=0;
		this.times=0;
		*/
		zeroState=1;
	}
	
	public void updateTime() {
		this.times++;
		if(this.times==100) {
			this.times=0;
			this.seconds++;
		}
		if(this.seconds==60) {
			this.seconds=0;
			this.minutes++;
		}
		if(this.minutes==60) {
			this.minutes=0;
			this.hours++;
		}
	}
	
	public void run() {
		while(true) {
			try {
				updateTime();
				//if(this.times==0)
					System.out.println(this.getTime());
				Thread.sleep(10);
			}catch (InterruptedException e) {break;}
		}
		
	}
	
}