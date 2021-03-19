
class EmployeeNode{
	EmployeeNode next;
	Employee details;
	EmployeeNode(Employee det){
		next = null;
		details = det;
	}
}

public class EmployeeList {
	EmployeeNode head;
	EmployeeNode sortedHead;
	EmployeeList(){
		head = null;
		this.addEmployee(new Employee(1,"Shubham",10000,20));
		this.addEmployee(new Employee(2,"Soni",20000,25));
		this.addEmployee(new Employee(3,"Rahul",100000,26));
		this.addEmployee(new Employee(4,"Vikas",10000,27));
	}
	
	/**
	 * Function add new Employee in LinkedList
	 * @param newEmp
	 */
	void addEmployee(Employee newEmp){
		if(head == null){
			head = new EmployeeNode(newEmp);
		}
		else{
			EmployeeNode temp = head;
			while(temp.next != null){
				temp = temp.next;
			}
			temp.next = new EmployeeNode(newEmp);
		}
	}
	
	/**
	 * Print Employee LinkedList
	 */
	void printList(){
		EmployeeNode temp = head;
		while(temp != null){
			System.out.println("\nId :- "+temp.details.empId + "\nName:- "+temp.details.empName+"\nSalary:- "+temp.details.empSalary + "\nAge:- "+temp.details.empAge);
			temp = temp.next;
		}
	}
	
	/**
	 * Print Sorted Employee LinkedList
	 */
	void printSortedList(){
		EmployeeNode temp = sortedHead;
		while(temp != null){
			System.out.println("\nId :- "+temp.details.empId + "\nName:- "+temp.details.empName+"\nSalary:- "+temp.details.empSalary + "\nAge:- "+temp.details.empAge);
			temp = temp.next;
		}
	}
	
	/**
	 * Sort The Employee LinkedList
	 */
	public void sort()  
    { 
        // Initialize sorted linked list 
        sortedHead = null; 
        EmployeeNode current = head; 
        
        // Traverse the given linked list and insert every 
        // node to sorted 
        while (current != null)  
        { 
            // Store next for next iteration 
        	EmployeeNode next = current.next; 
            // insert current in sorted linked list 
            sortedInsert(current); 
            // Update current 
            current = next; 
        } 
        // Update head_ref to point to sorted linked list 
        head = sortedHead; 
    }

	/**
	 * Helper Function
	 * @param newNode
	 */
	private void sortedInsert(EmployeeNode newNode) {
		 /* Special case for the head end */
	    if (sortedHead == null || sortedHead.details.empSalary < newNode.details.empSalary)  
	    { 
	        newNode.next = sortedHead; 
	        sortedHead = newNode; 
	    } 
	    else 
	    { 
	    	EmployeeNode current = sortedHead; 
	        /* Locate the node before the point of insertion */
	        while (current.next != null && (current.next.details.empSalary > newNode.details.empSalary || 
	        		(current.next.details.empSalary == newNode.details.empSalary  && newNode.details.empAge > current.next.details.empAge )))  
	        { 
	            current = current.next; 
	        } 
	        newNode.next = current.next; 
	        current.next = newNode; 
	    } 
		
	} 
}
