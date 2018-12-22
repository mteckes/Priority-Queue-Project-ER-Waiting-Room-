
public class HeapQueue
{

	// This method is the default constructor
	public HeapQueue()
	{
		queue = new patient[capacity];
		this.size = 0;
		isEmpty = true;
	} // HeapQueue()

	// This method adds an element to the queue
	public void add(patient obj)
	{

		// If the queue is empty, insert element at the 
		// top of the queue
		if(size == 0)
		{
			queue[0] = obj;
			size++;
			isEmpty = false;
		}

		// If size is greater than the queue, error
		else if(size > 15)
		{
			System.out.println("The queue is full, cannot add");
		}

		else 
		{
			// If elements are already in the queue, then 
			// at at the first free location. ( AKA size )
			queue[size] = obj;
			size++;
			// Call swap to move element into the correct position
			// based on value
			swapUp(size-1);

		}

	} // add()


	// This method removes the the first element
	// and replaces it it with the last element int
	// the queue. It calls swapDown to find the next
	// highest priority to replace it
	public patient remove()
	{
		if(size == 0)
		{
			System.out.println("Error....Underflow!");
			return null;

		}
		else
		{
			patient temp = queue[0];
			queue[0] = queue[size-1]; // put the last element in the top of the queue
			queue[size-1] = null; 	  // make the last elements space null
			size--; 				  // Decrement the size of the queue
			swapDown(); 			  // calls swapDown() to put the elements back in order
			return temp;
		}
	} // remove()

	// This method just allows to peek at the top of the 
	// queue. Just returns the top item. It does not remove.
	public patient peek()
	{
		// If the queue is empty report the error
		if(size ==0)
		{
			System.out.println("Error...Underflow!  Nothing to Peek()");
			return null;
		}
		// Else, return the top item (head)
		else
		{
			return queue[0];
		}
	} // peek()


	// Moves elements to their correct position from the
	// bottom up
	public void swapUp(int temp)
	{

		int child = temp;
		// Temp value to find the parent of a child to check and 
		// swap with
		int parent = (child -1)/2;


		// loop to walk the trees and swap
		while(parent >= 0 && queue[parent].priorityNum > queue[child].priorityNum)
		{
			// if the child element has a higher priority than its parent,
			// then it will swap with the parent

			patient tempPatient = queue[child]; // Temp patient to hold the child's data
			queue[child] = queue[parent];		// Swap the child's information with the parent
			queue[parent] = tempPatient;		// Replace the parents location with the child's data from the tempPatient
			child = parent;						// Update the child's index to the parents index
			parent = (parent-1)/2;				// Update the parent's new location

		}

	} // swapUp()

	// Moves elements down to their correct position
	// based on their priority
	public void swapDown()
	{
		int parent = 0;
		while(queue[(2*parent)+ 1] != null && queue[(2*parent)+ 2] != null )
		{
			
			// If parent has a lesser priority than the left child, and the right child is null, swap left
			if(queue[parent].priorityNum > queue[(2*parent)+1].priorityNum && queue[(2*parent)+2] == null )
			{
				parent = swapLeft((2*parent+1), (2*parent +2), parent);
			}
			
			// If the parent has a lesser priority than the right child, and the left child is null, swap right
			if(queue[parent].priorityNum > queue[(2*parent)+2].priorityNum && queue[(2*parent)+1] == null )
			{
				parent = swapRight((2*parent+1), (2*parent +2), parent);
			}
			
			// If the parent has an equal priority as the right child, and the left child is null
			if(queue[parent].priorityNum == queue[(2*parent)+2].priorityNum && queue[(2*parent)+1] == null )
			{
				// If the parent's arrival time is later than the right child's arrival time, swap right
				if(queue[parent].arrivalTime > queue[(2*parent)+2].arrivalTime)
				{
					parent = swapRight((2*parent+1), (2*parent +2), parent);
				}
			}
			
			// If the parent has an equal priority as the left child, and the right child is null
			if(queue[parent].priorityNum == queue[(2*parent)+1].priorityNum && queue[(2*parent)+2] == null )
			{
				// If the parent's arrival time is later than the left child's, swap left
				if(queue[parent].arrivalTime > queue[(2*parent)+1].arrivalTime)
				{
					parent = swapLeft((2*parent+1), (2*parent +2), parent);
				}
			}
			
			// If parent has a lesser or equal priority as the left child or parent has a lesser or equal priority as the right child
			if(queue[parent].priorityNum >= queue[(2*parent)+ 1].priorityNum || queue[parent].priorityNum >= queue[(2*parent)+ 2].priorityNum)
			{
				// If left child has higher priority than right child, swap left
				if(queue[(2*parent)+ 1].priorityNum  < queue[(2*parent)+ 2].priorityNum)
				{
					
					parent = swapLeft((2*parent+1), (2*parent +2), parent);
					
				}
				
				// If right child has a higher priority than the left child, swap right
				else if(queue[(2*parent)+ 2].priorityNum  < queue[(2*parent)+ 1].priorityNum)
				{
					
					parent = swapRight((2*parent+1), (2*parent +2), parent);
					
				}
				
				// If left and right children have the same priority
				else if(queue[parent].priorityNum > queue[(2*parent)+ 2].priorityNum && queue[(2*parent)+ 1].priorityNum == queue[(2*parent)+ 2].priorityNum)
				{
					// Child Left arrive is earlier than Child right, swap left
					if(queue[(2*parent)+ 1].arrivalTime < queue[(2*parent)+ 2].arrivalTime)
					{
						parent = swapLeft((2*parent+1), (2*parent +2), parent);
					}
					
					// Child Right arrive is earlier than Child Left, swap right
					else
					{
						parent = swapRight((2*parent+1), (2*parent +2), parent);
					}
				}
				
				// If parent and children have the same priority number
				if(queue[parent].priorityNum == queue[(2*parent)+1].priorityNum && queue[(2*parent)+1].priorityNum == queue[(2*parent)+2].priorityNum)
				{
					
					// If Parent has greater than or equal arrive time as child left, and child right has an earlier arrive time than child left, swap right
					if(queue[parent].arrivalTime > queue[(2*parent)+1].arrivalTime && queue[(2*parent)+2].arrivalTime < queue[(2*parent)+1].arrivalTime)
					{
						parent = swapRight((2*parent+1), (2*parent +2), parent);
					}
					
					// If Parent has greater than or equal arrive time as child right, and child left has an earlier arrive time than child right, swap left
					else if(queue[parent].arrivalTime > queue[(2*parent)+2].arrivalTime && queue[(2*parent)+1].arrivalTime < queue[(2*parent)+2].arrivalTime)
					{
						parent = swapLeft((2*parent+1), (2*parent +2), parent);
					}
					
					// If the parent has a later arrival time that child left, and child right arrival time is equal to child left, swap left
					else if(queue[parent].arrivalTime > queue[(2*parent)+1].arrivalTime && queue[(2*parent)+2].arrivalTime == queue[(2*parent)+1].arrivalTime)
					{
						parent = swapLeft((2*parent+1), (2*parent +2), parent);
					}
					
					// If parent arrival time is equal to both children, do nothing
					else if(queue[parent].arrivalTime == queue[(2*parent)+2].arrivalTime && queue[(2*parent)+1].arrivalTime == queue[(2*parent)+2].arrivalTime)
					{
						break;
					}
					
					
					// If parent parent arrival time is less than the children arrival times, do nothing
					else
					{
						break;
					}
					
				}
			}
			
			
		} // while loop
			
			
		// Call increase priority when completed
		increasePriority();

	} // swapDown()


	// Decrements an elements priority number to
	// give them a higher priority. It also prevents
	// an element from not getting service
	public void increasePriority()
	{
		for(int i = 0; i < size; i++)
		{

			if(queue[i].priorityNum == 1) 
			{
				// Priority number cannot be less than 1
				break;
			}
			else
			{
				queue[i].priorityNum--;
			}
		}
	}
	
	
	// Swaps the parent with the left child
	public int swapLeft(int left, int right, int parent)
	{
		patient temp = queue[left];
		queue[left] = queue[parent];
		queue[parent] = temp;
		parent = left;
		return parent;
	}
	
	// Swaps parent with the right child
	public int swapRight(int left, int right, int parent)
	{
		patient temp = queue[right];
		queue[right] = queue[parent];
		queue[parent] = temp;
		parent = right;
		return parent;
	}

	// Overrides toString to print out the queue
	public String toString()
	{
		for(int i = 0; i<15; i++)
		{
			System.out.println(queue[i]);
		}
		return "";
	}

	// This method returns the size of the
	// priority queue
	public int getSize()
	{
		return size;
	}


	// Data members
	private patient[] queue;
	int size;
	int capacity = 15;
	boolean isEmpty;


} // End






