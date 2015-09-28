import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Main {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		
		try {
			JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("data/task-2.json"));
			Method m = new Method(jsonArray);
			System.out.println(m.getMostRecentTask(680));
			System.out.println(m.getNumTasks(680));
			System.out.println(m.getTasksForAssignee("dbailie"));
			
			System.out.println(m.getNumTasksAtDate("2015-02-10T22:24:56Z"));
			System.out.println(m.getNumTasksAtDate("2015-02-10T22:24:55Z"));
			System.out.println(m.getNumTasksAtDate("2015-02-23T00:32:38Z"));
			System.out.println(m.getNumTasksAtDate("2015-02-23T00:32:37Z"));
			System.out.println(m.getNumTasksInRange("2015-02-10T22:24:56Z","2015-02-23T00:32:38Z"));
			

		} catch (FileNotFoundException e){
			System.out.println("File Not found at location");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
