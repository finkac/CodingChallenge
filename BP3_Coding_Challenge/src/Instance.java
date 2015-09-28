
public class Instance {
	private String mostRecentTaskName;
	private String mostRecentTaskDate;
	private long numtasks;
	public Instance(String name, String date){
		this.mostRecentTaskName = name;
		this.mostRecentTaskDate = date;
		this.numtasks = 1;
		
		
	}
	public void addTask(String name, String date){
		this.numtasks++;
		if(this.mostRecentTaskDate.compareTo(date)<0){
			this.mostRecentTaskDate = date;
			this.mostRecentTaskName = name;
		}
	}
	public long numTasks(){
		return this.numtasks;
	}
	public String recentTaskName(){
		return this.mostRecentTaskName;
	}

}
