
public class TimeDB extends Thread{
	private int year;
	private int month;
	private int day;
	private String dayOfWeek[];
	private int hour;
	private int minute;
	private int second;
	
	private Thread thread;
	
//	private TRO timeThread;
	
	private int time =0;
	
	public TimeDB() {
		setTime("2010 01 01 00 00");
	}
	
	public TimeDB(String time) {
		setTime(time);
	}
	
	public void setTime(String time) {

		String[] times = time.split("\\s");
		
		this.year=Integer.parseInt(times[0]);
		this.month=Integer.parseInt(times[1]);
		this.day=Integer.parseInt(times[2]);
		this.hour=Integer.parseInt(times[3]);
		this.minute=Integer.parseInt(times[4]);
		this.second=0;
	}
	
	public String getTime() {
		String time= Integer.toString(year)+" "+Integer.toString(month)+" "+Integer.toString(day)+" "+Integer.toString(hour)+" "+Integer.toString(minute)+" "+Integer.toString(second);
		
		return time;
	}
	
//	@Override
	public void updateTime() {
		this.time++;
		if(this.time==100) {
			this.time=0;
			this.second++;
		}
		if(this.second==60) {
			this.second=0;
			this.minute++;
		}
		if(this.minute==60) {
			this.minute=0;
			this.hour++;
		}
		if(this.hour==24) {
			this.hour=0;
			this.day++;
		}
		//
		
	}
	
	public void startUpdateTime() {
		thread = new TimeDB(this.getTime());
		thread.start();
	}
	
	public void pauseTimeDB() {
		thread.interrupt();
	}
	
	public void run() {
		while(true) {
			try {
				updateTime();
				if(this.time==0)
					System.out.println(this.getTime());
				Thread.sleep(10);
			}catch (InterruptedException e) {break;}
		}
		
	}
	
	
}