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

	private int times =0;

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
		//setTime("2010 1 1 0 0");
		year=2010;
		month=1;
		day=1;
		hour=0;
		minute=0;
		second=0;

		setMonthMap(year);
	}

	private TimeDB(String time) {
		String[] timeS = time.split("\\s");

		year=Integer.parseInt(timeS[0]);
		month=Integer.parseInt(timeS[1]);
		day=Integer.parseInt(timeS[2]);
		hour=Integer.parseInt(timeS[3]);
		minute=Integer.parseInt(timeS[4]);
		second=0;

		setMonthMap(year);
	}

	public static TimeDB getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder{
		private static final TimeDB INSTANCE= new TimeDB();
	}

	public void setTime(String time) {

		String[] timeS = time.split("\\s");

		year=Integer.parseInt(timeS[0]);
		month=Integer.parseInt(timeS[1]);
		day=Integer.parseInt(timeS[2]);
		hour=Integer.parseInt(timeS[3]);
		minute=Integer.parseInt(timeS[4]);
		second=0;

		setMonthMap(year);

	}

	public void setMonthMap(int year) {
		//그레고리력 규칙 참고
		if(year%4==0) {
			if(year%100!=0) {
				monthMap.put(2, 29);
			}
			else {
				if(year%400==0) {
					monthMap.put(2, 29);
				}
				else {monthMap.put(2, 28);}
			}
		}
		else {monthMap.put(2, 28);}
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
		String time= year+" "+month+" "+day+" "+hour+" "+minute+" "+second;

		return time;
	}

	public HashMap<Integer, Integer> getMonthMap(){
		return monthMap;
	}

	//	@Override
	public void updateTime() {
		times++;
		if(times==100) {
			times=0;
			second++;
		}
		if(second==60) {
			second=0;
			minute++;
		}
		if(minute==60) {
			minute=0;
			hour++;
		}
		if(hour==24) {
			hour=0;
			day++;
		}

		if(day > monthMap.get(month)) {
			day= 1;
			month++;
		}
		if(month > 12) {
			month=1;
			year++;
			setMonthMap(year);
		}
		if(year > 2100) {
			year= 2100;
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

