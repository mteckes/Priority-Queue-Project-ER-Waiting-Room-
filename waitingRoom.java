public class waitingRoom 
{

	// Randomly generates a priority number 1-20
	public int genPriority()
	{
		int tempPriorityNum = (int)(Math.random()*20+1);
		return tempPriorityNum;
	}

	// Randomly generates an arrival time
	public int genArrivalTime()
	{
		int tempArrivalTime = (int)(Math.random()*60);
		return tempArrivalTime;
	}

	// Randomly selects an injury
	public String genInjury(int priNum)
	{
		int temp = (int)(Math.random()*5 +1);
		if(priNum < 5)
		{
			if(temp == 1 || temp == 5)
			{
				return "Heart Attack";
			}
			if(temp == 2)
			{
				return "Stroke";
			}
			if(temp == 3)
			{
				return "Braint Injury";
			}
			if(temp == 4)
			{
				return "Massive Bleading";
			}
		}
		if(priNum >=5 && priNum < 12)
		{
			if(temp == 1 || temp == 5)
			{
				return "Serious Fracture";
			}
			if(temp == 2)
			{
				return "Concussion";
			}
			if(temp == 3)
			{
				return "Appendicitis";
			}
			if(temp == 4)
			{
				return "Serious illnes";
			}
		}
		if(priNum >=12 && priNum < 21)
		{
			if(temp == 1)
			{
				return "Heat Exhaustion";
			}
			if(temp == 2)
			{
				return "Simple Cut";
			}
			if(temp == 3)
			{
				return "Something stuck in your body";
			}
			if(temp == 4)
			{
				return "Simple Fracture";
			}
			if(temp == 5)
			{
				return "Flu/Fever";
			}
		}
		else
			return "Non Serious Injury";
		return "";

	}

	// Calls methods to randomly generate the arrival time,
	// priority, and the injury. It then binds them together
	// to create a patient;
	public patient bindPatient(String tempName)
	{
		int tempArrival = genArrivalTime();
		int tempPriority = genPriority();
		String tempInjury = genInjury(tempPriority);
		patient tempPatient = new patient(tempArrival, tempPriority, tempInjury, tempName);
		return tempPatient;
	}

	// This method will sort the patients by arrival time
	public static void bubbleSort(patient[]tempPatients)
	{
		for(int i = 0; i < tempPatients.length; i++)
		{
			for(int j = 0; j < tempPatients.length - 1 - i; j++)
			{
				if(tempPatients[j].arrivalTime > tempPatients[j+1].arrivalTime)
				{
					patient temp = tempPatients[j];
					tempPatients[j] = tempPatients[j+1];
					tempPatients[j+1] = temp;
				}
			}
		}


	}

	public waitingRoom()
	{
		boolean inCare = false;
		int releaseTime = 0;
		patient[] listOfPatients = new patient[15];
		HeapQueue waitingList = new HeapQueue();
		for(int i = 0; i<15; i++)
		{
			String tempName = "Patient" + i;
			listOfPatients[i] = bindPatient(tempName);

		}
		bubbleSort(listOfPatients);
		
		System.out.println("The list of Patients sorted by arrival time:");
		for(int i = 0; i<15; i++)
		{
			//String tempName = "Patient" + i;
			//listOfPatients[i] = bindPatient();
			System.out.println(listOfPatients[i]);

		}
		
		System.out.println("\n \n");
		int i = 0;
		int time = 0;
		while(time < 60)
		{
			System.out.println("The time is: " + time);
			
			// controls the patient inlet to be seen. Swaps the
			// boolean inCare to false meaning there is no patients 
			// being seen
			if(releaseTime == time)
			{
				inCare = false;
			}
			
			for(; i < 15; i++)
			{
				if(listOfPatients[i].arrivalTime == time)
				{
					waitingList.add(listOfPatients[i]);
					//listOfPatients[i] = null;
				}
				if(listOfPatients[i].arrivalTime > time)
				{
					break;
				}
			}

			if(waitingList.getSize() != 0)
			{
				patient temp = waitingList.peek();
				if(temp.arrivalTime <= time && inCare == false)
				{
					inCare = true;
					System.out.println("Time patient is being served is: " + time);
					System.out.println("Seeing Patient: " + temp);
					waitingList.remove();
					releaseTime = time + 2;
				}
			}
			

			time++;
		}
		System.out.println("End of Time! - Program ends");
	}
}
