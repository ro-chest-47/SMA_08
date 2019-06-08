public class TimeKeeping {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;

	private static TimeKeeping instance;

	private TimeKeeping() {
	}

	public static TimeKeeping getInstance(){
		if(instance==null){
			instance=new TimeKeeping();
		}
		return instance;
	}

	public static void deleteInstance(){
		instance=null;
	}



//	public static TimeKeeping getInstance() {
//		return LazyHolder.INSTANCE;
//	}
//
//	private static class LazyHolder{
//		private static final TimeKeeping INSTANCE = new TimeKeeping();
//	}

	
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
		String time= year+" "+month+" "+day+" "+hour+" "+minute+" "+second;
		
		return time;
	}

}
