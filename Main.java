import java.util.*;


public class Main {
	public static void main(String[] args) throws Exception{
		MyDictionary dict = new MyDictionary();
		List<Pair> result = dict.getBetween("20","60");
		for(int i=0;i<result.size();i++){
			System.out.println(result.get(i).key + " " + result.get(i).value);
		}
		
		
		EmployeeList empL = new EmployeeList();
		empL.sort();
		empL.printSortedList();
	}
}
