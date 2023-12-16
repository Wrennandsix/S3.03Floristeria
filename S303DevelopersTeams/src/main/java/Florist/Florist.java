package Florist;

import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

import exceptions.NoStockException;
import productsHierarchy.Decor;
import input.Input;
import productsHierarchy.Flower;
import productsHierarchy.Product;
import productsHierarchy.Tree;
import ticket.Ticket;

public class Florist {
	
	private static Scanner sc = new Scanner(System.in); 
	
	
	private String name;
	private List<Tree> treeList;
	private List<Flower> flowerList;
	private List<Decor> decorList;
	private List<Ticket> ticketList;
	private int id;
	private static int nextId = 0;
	
	
	public Florist(String name) {
		this.name = name;
		this.treeList = new ArrayList<Tree>();
		this.flowerList = new ArrayList<Flower>();
		this.decorList = new ArrayList<Decor>();
		this.ticketList = new ArrayList<Ticket>();
		this.id = ++nextId;
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

	/*public int requestTree() {

		int id;

		System.out.println("Introdueix el id del arbre existent a la base de dades al cual vols afegir stock.");
		id = sc.nextInt();
		return id;

	}

	public String requestNewTreeName() {

		String name;
		System.out.println("Introdueix el nom del arbre nou per comprobar que no existeix a la base de dades.");
		name = sc.next();
		sc.nextLine();
		return name;

	}
	public float requestNewTreeHeight() {

		float height;
		System.out.println("Introdueix l'alçada del arbre nou per comprobar que no existeix a la base de dades.");
		height = sc.nextFloat();
		sc.nextLine();
		return height;

	}*/


	//cambiar de clase
	public <T> int findIndex(List<T> list, Object obj) {
		
		Optional<Integer> index = IntStream.range(0, list.size())
                .filter(i -> list.get(i).equals(obj))
                .boxed()
                .findFirst();
		
		return index.orElse(-1);
	}
	

	public void addTree() throws Exception {

		String name;
		float price = 0;
		float height = 0;
		int stock = 0;

		name = Input.readString("Introdueix el nom del arbre:");

		price = Input.readFloat("Introdueix el preu del arbre:");

		height = Input.readFloat("Introdueix l' alçada de l'arbre");

		stock = Input.readInt("Introdueix la quantitat d'abres a afegir:");

		Tree tree = new Tree(name, price, height, stock);
		if (findTree(tree) == null) {
			treeList.add(tree);
			System.out.println("Abre afegit: " + tree);
		} else {
			System.out.println("Abre ja existent!");
			int index = findIndex(treeList, tree);
			addStockTree(treeList.get(index), stock);
		}
		sc.nextLine();

	}

	public Tree findTree(Tree tree) {

		Optional<Tree> treeFound = treeList.stream()
				.filter(t -> t.getName().equalsIgnoreCase(tree.getName()) && t.getHeight() == tree.getHeight())
				.findFirst();

		return treeFound.orElse(null);
	}

	public void addStockTree(Tree tree, int stock) {

		if (tree == null) {
			System.out.println("L'arbre introduit no existeix en la base de dades, registral com a nou");
		} else {
			tree.setStock(tree.getStock() + stock);
			System.out.println("L'stock atualitzat es de: " + tree.getStock() + " del arbre: " + tree.getName()
			+ " amb una alçada de: " + tree.getHeight() + " cm");
		}
	}
	
	public void showTrees() {

		System.out.println("Arbres actuals a la base de dades:");

		treeList.forEach(tree -> System.out.println("ID:" + tree.getId() + "	Nom: " + tree.getName() + "	Alçada: "
				+ tree.getHeight() + "	Stock: " + tree.getStock()));

	}

	public void addFlower() throws Exception {

		String name;
		float price = 0;
		String colour;
		int stock = 0;
		name = Input.readString("Introdueix el nom de la flor:");
		price = Input.readFloat("Introdueix el preu de la flor:");

		colour = Input.readString("Introdueix el color de la flor:");

		stock = Input.readInt("Introdueix la quantitat de flors a afegir:");

		Flower flower = new Flower(name, price, colour, stock);
		if (findFlower(flower) == null) {
			flowerList.add(flower);
			System.out.println("Flor afegida: " + flower);
		} else {
			System.out.println("Flor ja existent!");
			int index = findIndex(flowerList, flower);
			addStockFlower(flowerList.get(index), stock);
		}
		sc.nextLine();

	}

	public Flower findFlower(Flower flower) {

		Optional<Flower> flowerFound = flowerList.stream()
				.filter(f -> f.getName().equalsIgnoreCase(flower.getName()) && f.getColour().equals(flower.getColour()))
				.findFirst();

		return flowerFound.orElse(null);
		
	}
	
	public void addStockFlower(Flower flower, int stock) {

		if (flower == null) {
			System.out.println("La flor introduïda no existeix en la base de dades, registral com a nou");
		} else {
			flower.setStock(flower.getStock() + stock);
			System.out.println("L'stock atualitzat es de: " + flower.getStock() + " de la flor: " + flower.getName()
			+ " amb un color de: " + flower.getColour());
		}
	}
	
	public void showFlowers() {

		System.out.println("Flors actuals a la base de dades:");

		flowerList.forEach(flower -> System.out.println("ID:" + flower.getId() + "	Nom: " + flower.getName() + "	Color: "
				+ flower.getColour() + "	Stock: " + flower.getStock()));

	}
	
	public void addDecor() throws Exception {

		String name;
		float price = 0;
		String material;
		int stock = 0;
		name = Input.readString("Introdueix el nom de la decoració:");

		price = Input.readFloat("Introdueix el preu de la decoració:");

		material = Input.readString("Introdueix el material de la decoració:");

		stock = Input.readInt("Introdueix la quantitat de objectes decoratius a afegir:");

		Decor decor = new Decor(name, price, material, stock);
		if (findDecor(decor) == null) {
			decorList.add(decor);
			System.out.println("Decoració afegida: " + decor);
		} else {
			System.out.println("Decoració ja existent!");
			int index = findIndex(decorList, decor);
			addStockDecor(decorList.get(index), stock);
		}
		sc.nextLine();

	}
	



	public Decor findDecor(Decor decor) {

		Optional<Decor> decorFound = decorList.stream()
				.filter(d-> d.getName().equalsIgnoreCase(decor.getName()) && d.getMaterial().equals(decor.getMaterial()))
				.findFirst();

		return decorFound.orElse(null);
		
	}
	
	public void addStockDecor(Decor decor, int stock) {

		if (decor == null) {
			System.out.println("La decoració introduïda no existeix en la base de dades, registral com a nou");
		} else {
			decor.setStock(decor.getStock() + stock);
			System.out.println("L'stock atualitzat es de: " + decor.getStock() + " de la decoració: " + decor.getName()
			+ " amb un color de: " + decor.getMaterial());
		}
	}
	
	public void showDecors() {

		System.out.println("Decoracions actuals a la base de dades:");

		decorList.forEach(decor -> System.out.println("ID:" + decor.getId() + "	Nom: " + decor.getName() + "	Material: "
				+ decor.getMaterial() + "	Stock: " + decor.getStock()));

	}
		

	public Tree withdrawTree() throws NoStockException, Exception {
		
		int id;
		int quantity;
		Tree tree;
		
		showTrees();
		
		id = Input.readInt("Introdueix el id de l'abre:");
		
		quantity = Input.readInt("Quantitat");
		
		if((treeList.get(id -1).getStock() - quantity) < 0) {
			throw new NoStockException();
		}else if((treeList.get(id -1).getStock() - quantity) == 0) {
			tree = new Tree(treeList.get(id- 1));
			treeList.remove(id -1);
			return tree;
		}else {
			treeList.get(id -1).setStock(treeList.get(id -1).getStock() - quantity);
			tree = new Tree(treeList.get(id- 1));
			tree.setStock(quantity);
			return tree;
		}
		
	}
	public Flower withdrawFlower() throws NoStockException, Exception {
		
		int id;
		int quantity;
		Flower flower;
		
		showFlowers();
		
		id = Input.readInt("Introdueix el id de la flor:");
		
		quantity = Input.readInt("Quantitat");
		
		if((flowerList.get(id -1).getStock() - quantity) < 0) {
			throw new NoStockException();
		}else if((flowerList.get(id -1).getStock() - quantity) == 0) {
			flower = new Flower(flowerList.get(id -1));
			flowerList.remove(id -1);
			return flower;
		}else {
			flower = new Flower(flowerList.get(id -1));
			flowerList.get(id -1).setStock(flowerList.get(id -1).getStock() - quantity);
			flower.setStock(quantity);
			return flower;
		}
		
		
	}
	public Decor withdrawDecor() throws NoStockException, Exception {
		
		int id;
		int quantity;
		Decor decor;
		
		showDecors();
		
		id = Input.readInt("Introdueix el id de la decoració:");
		
		quantity = Input.readInt("Quantitat");
		
		if((decorList.get(id -1).getStock() - quantity) < 0) {
			throw new NoStockException();
		}else if((decorList.get(id-1).getStock() - quantity) == 0) {
			decor = new Decor(decorList.get(id -1));
			decorList.remove(id -1);
			return decor;
		}else {
			decor = new Decor(decorList.get(id -1));
			decorList.get(id -1).setStock(decorList.get(id -1).getStock() - quantity);
			decor.setStock(quantity);
			return decor;
		}
		
	}
	public void printStock() {
		System.out.println("Abres:");
		treeList.forEach(tree -> System.out.println(tree.toString()));
		
		System.out.println("\nFlors:");
		flowerList.forEach(flor -> System.out.println(flor.toString()));
		
		System.out.println("\nDecoració:");
		decorList.forEach(decor -> System.out.println(decor.toString()));
	}
	
	public void addTicket() {
		
		Ticket ticket = new Ticket("Ticket" + this.name, this);
		
		try {
			ticket.addProducts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ticketList.add(ticket);
	}
	
	
	public float valueTotal() {
		
		float treeValueTotal = (float) treeList.stream()
                .mapToDouble(tree -> tree.getPrice() * tree.getStock())
                .sum();
		
		float flowerValueTotal = (float) flowerList.stream()
                .mapToDouble(flower -> flower.getPrice() * flower.getStock())
                .sum();
		
		float decorValueTotal = (float) decorList.stream()
                .mapToDouble(decor -> decor.getPrice() * decor.getStock())
                .sum();
		
		return treeValueTotal + flowerValueTotal + decorValueTotal;
	}
	public void showOldBuys() {
		System.out.println("Historial de compres de la floristeria: "+name);
		ticketList.forEach(ticket -> System.out.println(ticket.toString()));
		
	}
	public float totalProfit() {
		
		float valueTotal;
	
		valueTotal = (float) ticketList.stream()
		.mapToDouble(t -> t.calculatePrice())
		.sum();
		
		return valueTotal;
	}
	
	@Override
	public String toString() {
		return "Florist [name=" + name + ", treeList=" + treeList + ", flowerList=" + flowerList + ", decorList="
				+ decorList + ", ticketsList=" + ticketList + ", id=" + id + "]";
	}

}
