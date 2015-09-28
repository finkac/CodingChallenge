import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Method {
	private JSONArray inputArray;
	private Map<Long, Instance> instances;
	private Map<String, Assignee> assignees;
	
	/**
	 * Contructor for the methods
	 * Creates instance and assignee hashMaps so you don't have to run a for loop each time you execute those commands.
	 * @param input JSONArray of the file
	 * 
	 */
	public Method(JSONArray input){
		this.inputArray = input;
		this.instances = new HashMap<Long, Instance>();
		this.assignees = new HashMap<String, Assignee>();
		for(Object obj: this.inputArray){
			JSONObject jsonObj = (JSONObject) obj;
			
			//instances
			if(this.instances.containsKey((long)jsonObj.get("instanceId"))){
				this.instances.get((long)jsonObj.get("instanceId")).addTask((String) jsonObj.get("name"), (String) jsonObj.get("createDate"));
			} else {
				this.instances.put((long)jsonObj.get("instanceId"), new Instance((String) jsonObj.get("name"), (String) jsonObj.get("createDate")));
			}
			
			//Assignees
			if(this.assignees.containsKey((String)jsonObj.get("assignee"))){
				this.assignees.get((String)jsonObj.get("assignee")).addTask(((String)jsonObj.get("status")).equals("Closed"));
				
			} else {
				this.assignees.put((String)jsonObj.get("assignee"), new Assignee(((String)jsonObj.get("status")).equals("Closed")));
			}
			
				
		}
		
	}
	
	/**
	 * Returns the number of open and closed tasks at that time
	 * @param date String formatted YYYY--ddThh:mm:ssZ 
	 * @return Returns the number of open and closed tasks at that time
	 */
	public String getNumTasksAtDate(String date){
		int openNum = 0;
		int closedNum = 0;
		for(Object obj: this.inputArray){
			JSONObject jsonObj = (JSONObject) obj;
			if(((String)jsonObj.get("closeDate")) != null &&((String)jsonObj.get("closeDate")).compareTo(date) <= 0){
				closedNum++;
			}
			else if(((String)jsonObj.get("createDate")).compareTo(date) <= 0){
				openNum++;
			}
		}
		return "Closed: "+closedNum+" Open: "+openNum;
	}
	/**
	 * 
	 * @param start String formatted YYYY--ddThh:mm:ssZ Start date
	 * @param end String formatted YYYY--ddThh:mm:ssZ end date
	 * @return String listing the number projects were opened and the number that were closed in the time span
	 * There can be task that opened and closed with in that and and they are listed for both.
	 * 
	 * I had some ideas on how to remove the duplication between the two methods but I didn't have time to fully test and think through it.
	 */
	public String getNumTasksInRange(String start, String end){
		int openNum = 0;
		int closedNum = 0;
		for(Object obj: this.inputArray){
			JSONObject jsonObj = (JSONObject) obj;
			if(((String)jsonObj.get("createDate")).compareTo(start) >= 0 && ((String)jsonObj.get("createDate")).compareTo(end) < 0){
				openNum++;
			}
			if( ((String)jsonObj.get("closeDate")) != null && ((String)jsonObj.get("closeDate")).compareTo(start) >= 0 && ((String)jsonObj.get("closeDate")).compareTo(end) < 0){
				closedNum++;
			}
		}
		
		return "Closed: "+closedNum+" Open: "+openNum;
	}
	/**
	 * Takes in an instance id and retrieves and returns the name of the most recently created task.
	 * @param instanceId the id of instance
	 * @return the name of the most recently created task
	 */
	public String getMostRecentTask(long instanceId){
		if(this.instances.containsKey(instanceId) ){
			return "Most Recent Task Name " + this.instances.get(instanceId).recentTaskName();
		}
		else {
			return "There is not Instance with the id " + instanceId;
		}
	}
	/**
	 * Takes in an instance id and retrieves the number of tasks for that instance
	 * @param instanceId the id of instance
	 * @return Int number of tasks
	 */
	public String getNumTasks(long instanceId){
		if(this.instances.containsKey(instanceId) ){
			return "Total tasks: " + this.instances.get(instanceId).numTasks();
		}
		else {
			return "There is not Instance with the id " + instanceId;
		}
	}
	/**
	 * 
	 * @param assignee String assignee assigned to the task
	 * @return String of open and closed tasks
	 */
	public String getTasksForAssignee(String assignee){
		if(this.assignees.containsKey(assignee)){
			Assignee a = this.assignees.get(assignee);
			return "Closed: "+a.getClosedTasks()+" Open: "+a.getOpenTasks();
		} else {
			return "There are no tasks assigned to assignee " + assignee;
		}
	}
	
}
