// A class to store the useful attributes of an Assignee
public class Assignee {
	private long openTasks;
	private long closedTasks;
	public Assignee(boolean isClosed){
		this.openTasks = 0;
		this.closedTasks = 0;
		this.addTask(isClosed);
		
	}
	public void addTask(boolean isClosed){
		if (isClosed){
			this.closedTasks++;
		}
		else{
			this.openTasks++;
		}
	}
	public long getOpenTasks(){
		return this.openTasks;
	}
	public long getClosedTasks(){
		return this.closedTasks;
	}

}
