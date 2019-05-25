import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeDB extends Thread{
	private static int year;
	private static int month;
	private static int day;
	private static int hour;
	private static int minute;
	private static int second;

	private Thread thread;
	private TimeDB runnable;
	private ScheduledExecutorService service;

	private int time =0;

	private HashMap<Integer, Integer> monthMap = new HashMap<>();

	private TimeDB() {
        /*monthMap.put(1, 31);
        monthMap.put(2, 28);
        monthMap.put(3, 31);
        monthMap.put(4, 30);
        monthMap.put(5, 31);
        monthMap.put(6, 30);
        monthMap.put(7, 31);
        monthMap.put(8, 31);
        monthMap.put(9, 30);
        monthMap.put(10, 31);
        monthMap.put(11, 30);
        monthMap.put(12, 31);*/
		setTime("2010 1 1 0 0");
	}

	private TimeDB(String time) {
		setTime(time);
	}

	public static TimeDB getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder{
		private static final TimeDB INSTANCE= new TimeDB();
	}

	public void setTime(String time) {

		String[] times = time.split("\\s");

		this.year=Integer.parseInt(times[0]);
		this.month=Integer.parseInt(times[1]);
		this.day=Integer.parseInt(times[2]);
		this.hour=Integer.parseInt(times[3]);
		this.minute=Integer.parseInt(times[4]);
		this.second=0;

		setMonthMap(this.year);

	}

	public void setMonthMap(int year) {
		//그레고리력 규칙 참고
		if(year%4==0) {
			if(year%100!=0)
				monthMap.put(2, 29);
			else {
				if(year%400==0)
					monthMap.put(2, 29);
				else monthMap.put(2, 28);
			}
		}
		else monthMap.put(2, 28);
		monthMap.put(1, 31);

		monthMap.put(3, 31);
		monthMap.put(4, 30);
		monthMap.put(5, 31);
		monthMap.put(6, 30);
		monthMap.put(7, 31);
		monthMap.put(8, 31);
		monthMap.put(9, 30);
		monthMap.put(10, 31);
		monthMap.put(11, 30);
		monthMap.put(12, 31);
	}

	public String getTime() {
		String time= Integer.toString(year)+" "+Integer.toString(month)+" "+Integer.toString(day)+" "+Integer.toString(hour)+" "+Integer.toString(minute)+" "+Integer.toString(second);

		return time;
	}

	public HashMap<Integer, Integer> getMonthMap(){
		return this.monthMap;
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

		if(this.day > monthMap.get(this.month)) {
			this.day= 1;
			this.month++;
		}
		if(this.month > 12) {
			this.month=1;
			this.year++;
			setMonthMap(this.year);
		}
		if(this.year > 2100) {
			this.year= 2100;
			thread.interrupt();
		}

	}

	public void startUpdateTime() {
		service = Executors.newSingleThreadScheduledExecutor();
		runnable = new TimeDB(getTime());
		thread = new Thread(runnable);
		service.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.MILLISECONDS);
		//thread.start();
	}

	public void pauseTimeDB(){ service.shutdown(); }

	public void run(){
		updateTime();
		//System.out.println(this.getTime());


	}


}

