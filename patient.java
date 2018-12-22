
public class patient 
{
	// Data
	//Object data;
	int priorityNum;
	int arrivalTime;
	String injury;
	String name;
	
	// Constructor
	public patient(int arrive, int priority, String inj, String name)
	{
		arrivalTime = arrive;
		priorityNum = priority;
		injury = inj;
		this.name = name;
		
	}
	
	// Returns the priority number
	public int getPriority(patient temp)
	{
		return temp.priorityNum;
	}
	
	// Overrides toString method
	public String toString()
	{
		return "Name: " + name +  "	 Arrival Time: " + arrivalTime + " 	Injury: " + injury  + "   Priority: " + priorityNum  /* + " \n"*/;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
