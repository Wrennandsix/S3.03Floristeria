package Florist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FloristUtils {
	
	private static Scanner sc = new Scanner(System.in); 
	private List<Florist> floristList = new ArrayList<Florist>();
	
	
	public List<Florist> getFloristList() {
		return floristList;
	}

	public void createFlorist() {
		
		String nameFlorist;
		
		System.out.println("Introduex nom floristeria");
		nameFlorist = sc.nextLine();
		
		floristList.add(new Florist(nameFlorist));
		
		System.out.println("Floristeria creada!");
		
	}

}
