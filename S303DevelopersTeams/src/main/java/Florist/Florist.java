package Florist;

import java.util.ArrayList;
import java.util.List;

import productsHierarchy.Decor;
import productsHierarchy.Flower;
import productsHierarchy.Tree;

public class Florist {
	
	private String name;
	private List<Tree> treeList;
	private List<Flower> flowerList;
	private List<Decor> decorList;
	//private List<Ticket> ticketsList;
	private int id;
	private static int nextId;
	
	
	public Florist(String name) {
		this.name = name;
		this.treeList = new ArrayList<Tree>();
		this.flowerList = new ArrayList<Flower>();
		this.decorList = new ArrayList<Decor>();
		//this.ticketsList = new Arraylist<Ticket>();
		this.id = nextId++;
	}
	
	
	
	public String getName() {
		return name;
	}



	public List<Tree> getTreeList() {
		return treeList;
	}



	public List<Flower> getFlowerList() {
		return flowerList;
	}



	public List<Decor> getDecorList() {
		return decorList;
	}



	public int getId() {
		return id;
	}



	public void addTree() {
		
	}
	public void addFlower() {
		
	}
	public void addDecor() {
		
	}
	public void removeTree() {
		
	}
	public void removeFlower() {
		
	}
	public void removeDecor() {
		
	}
	public void printStock() {
		
	}
	public void printValueTotal() {
		
	}
	public void showOldBuys() {
		
	}
	public float totalProfit() {
		return 0f;
	}
	
	
}
