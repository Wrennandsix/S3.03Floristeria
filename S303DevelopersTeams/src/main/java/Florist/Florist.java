package Florist;



import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.j256.simplecsv.common.CsvColumn;
import com.j256.simplecsv.processor.CsvProcessor;

import exceptions.NoStockException;
import productsHierarchy.Decor;
import input.Input;
import productsHierarchy.Flower;
import productsHierarchy.Tree;
import ticket.Ticket;

public class Florist {
	
	private static Scanner sc = new Scanner(System.in); 
	
	@CsvColumn(columnName = "name")
	private String name;
	private List<Tree> treeList;
	private List<Flower> flowerList;
	private List<Decor> decorList;
	private List<Ticket> ticketList;
	@CsvColumn(columnName = "id")
	private int id;
	private static int nextId = 0;
	
	public Florist() {
		this.id = nextId++;
		this.ticketList = new ArrayList<Ticket>();
	}
	public Florist(String name) {
		this.name = name;
		this.treeList = new ArrayList<Tree>();
		this.flowerList = new ArrayList<Flower>();
		this.decorList = new ArrayList<Decor>();
		//this.ticketList = new ArrayList<Ticket>();
		this.id = ++nextId;
	}
	
	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
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

	public void readTrees() throws IOException, ParseException{

		CsvProcessor<Tree> csvProcessor = new CsvProcessor<Tree>(Tree.class);

        String absolutePath = new File("").getAbsolutePath();
        String outputFile = absolutePath + "." + this.name + "treeDataBase.txt";
        File csvFile = new File(outputFile);
        //List<Florist> floristsA = csvProcessor.readAll(csvFile, null);
        treeList = (ArrayList<Tree>) csvProcessor.readAll(csvFile, null);
        treeList.forEach(t -> System.out.println(t.toString()));

    }
	
	public void readFlowers() throws IOException, ParseException{

		CsvProcessor<Flower> csvProcessor = new CsvProcessor<Flower>(Flower.class);

        String absolutePath = new File("").getAbsolutePath();
        String outputFile = absolutePath + "." + this.name + "flowerDataBase.txt";	
        File csvFile = new File(outputFile);
        flowerList = (ArrayList<Flower>) csvProcessor.readAll(csvFile, null);
        flowerList.forEach(f -> System.out.println(f.toString()));

    }
	
	public void readDecors() throws IOException, ParseException{

		CsvProcessor<Decor> csvProcessor = new CsvProcessor<Decor>(Decor.class);

        String absolutePath = new File("").getAbsolutePath();
        String outputFile = absolutePath + "." + this.name + "decorDataBase.txt";
        File csvFile = new File(outputFile);
        decorList = (ArrayList<Decor>) csvProcessor.readAll(csvFile, null);
        decorList.forEach(f -> System.out.println(f.toString()));

    }
	
	public void readTickets() throws IOException, ParseException {
		
		CsvProcessor<Ticket> csvProcessor = new CsvProcessor<Ticket>(Ticket.class);

        String absolutePath = new File("").getAbsolutePath();
        String outputFile = absolutePath + "." + this.name + "ticketDataBase.txt";
        File csvFile = new File(outputFile);
        ticketList = (ArrayList<Ticket>) csvProcessor.readAll(csvFile, null);
        ticketList.forEach(f -> System.out.println(f.toString()));	
	}
	
	public void readProducts() {
		ticketList.forEach(t -> {
			try {
				t.readProducts();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}
	
	//cambiar de clase
	public <T> int findIndex(List<T> list, Object obj) {
		
		Optional<Integer> index = IntStream.range(0, list.size())
                .filter(i -> list.get(i).equals(obj))
                .boxed()
                .findFirst();
		
		return index.orElse(-1);
	}
	public int findTreeIndex(List<Tree> treeList, int id) {
		
	    OptionalInt index = IntStream.range(0, treeList.size())
	            .filter(i -> treeList.get(i).getId() == id)
	            .findFirst();

	    return index.orElse(-1);
	}
	public int findFlowerIndex(List<Flower> flowerList, int id) {
		
	    OptionalInt index = IntStream.range(0, flowerList.size())
	            .filter(i -> flowerList.get(i).getId() == id)
	            .findFirst();

	    return index.orElse(-1);
	}
	public int findDecorIndex(List<Decor> decorList, int id) {
		
	    OptionalInt index = IntStream.range(0, decorList.size())
	            .filter(i -> decorList.get(i).getId() == id)
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

		System.out.println("Arbres actuals a la base de dades de la floristeria: " + name + " amb id:" + id + ":");

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

		System.out.println("Flors actuals a la base de dades de la floristeria: " + name + " amb id:" + id + ":");

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

		System.out.println("Decoracions actuals a la base de dades de la floristeria: " + name + " amb id:" + id + ":");

		decorList.forEach(decor -> System.out.println("ID:" + decor.getId() + "	Nom: " + decor.getName() + "	Material: "
				+ decor.getMaterial() + "	Stock: " + decor.getStock()));

	}

	public void removeTree() throws Exception {

		showTrees();
		if (treeList.isEmpty()) {
			System.out.println("La base de dades esta buida!.");
		} else {

			id = Input.readInt("Introdueix el id de l'abre que vols eliminar  de la base de dades:");

			int indexFound = findTreeIndex(treeList, id);

			if (id == treeList.get(indexFound).getId()) {
				System.out.println(
						"Arbre: " + treeList.get(indexFound).toString() + " ha sigut eliminat de la base de dades.");
				treeList.remove(indexFound);
			}
		}
	}

	public void removeFlower() throws Exception {

		showFlowers();
		if (flowerList.isEmpty()) {
			System.out.println("La base de dades esta buida!.");
		} else {
			id = Input.readInt("Introdueix el id de la flor que vols eliminar  de la base de dades:");

			int indexFound = findFlowerIndex(flowerList, id);
			if (id == flowerList.get(indexFound).getId()) {
				System.out.println(
						"Flor: " + flowerList.get(indexFound).toString() + " ha sigut eliminada de la base de dades.");
				flowerList.remove(indexFound);
			}
		}
	}

	public void removeDecor() throws Exception {

		showDecors();
		if (decorList.isEmpty()) {
			System.out.println("La base de dades esta buida!.");
		} else {

			id = Input.readInt("Introdueix el id de la decoració que vols eliminar  de la base de dades:");

			int indexFound = findDecorIndex(decorList, id);

			if (id == decorList.get(indexFound).getId()) {
				System.out.println("Decoració: " + decorList.get(indexFound).toString()
						+ " ha sigut eliminat de la base de dades.");
				decorList.remove(indexFound);
			}
		}
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

	public void printStockQuantities() {
		System.out.println("Abres:");
		treeList.forEach(tree -> {
			System.out.println(tree.toString() + " Stock:" + tree.getStock());
		});

		System.out.println("\nFlors:");
		flowerList.forEach(flower -> {
			System.out.println(flower.toString() + " Stock:" + flower.getStock());
		});

		System.out.println("\nDecoració:");
		decorList.forEach(decor -> {
			System.out.println(decor.toString() + " Stock:" + decor.getStock());
		});
	}

	public void addTicket() {
		
		Ticket ticket = new Ticket("Ticket" + this.name, this);
		
		try {
			ticket.addProducts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.ticketList.add(ticket);
	}
	
	public float valueTrees() {

		return (float) treeList.stream().mapToDouble(tree -> tree.getPrice() * tree.getStock()).sum();
	}

	public float valueFlowers() {

		return (float) flowerList.stream().mapToDouble(flower -> flower.getPrice() * flower.getStock()).sum();
	}

	public float valueDecors() {

		return (float) decorList.stream().mapToDouble(Decors -> Decors.getPrice() * Decors.getStock()).sum();
	}

	public float valueTotal() {

		float treeValueTotal = valueTrees();
		float flowerValueTotal = valueFlowers();
		float decorValueTotal = valueDecors();

		return treeValueTotal + flowerValueTotal + decorValueTotal;
	}

	public void printValueTotal() {
		System.out.println("El valor total del stock de la floristeria es de: " + valueTotal() + " Euros");

	}

	public void showOldBuys() {
		System.out.println("Historial de compres de la floristeria: " + name);
		ticketList.forEach(ticket -> System.out.println(ticket.toString()));

	}

	public float totalProfit() {

		float valueTotal;

		valueTotal = (float) ticketList.stream().mapToDouble(t -> t.calculatePrice()).sum();

		return valueTotal;
	}

	public void printTotalProfit() {
		System.out.println("El valor total de les vendes de la floristeria es de: " + totalProfit() + " Euros");

	}

	@Override
	public String toString() {
		return "Florist [name=" + name + ", treeList=" + treeList + ", flowerList=" + flowerList + ", decorList="
				+ decorList + ", ticketsList=" + ticketList + ", id=" + id + "]";
	}
	public void writeDecor() throws Exception {
		
		CsvProcessor<Decor> csvProcessor = new CsvProcessor<Decor>(Decor.class);
		String absolutePath = new File("").getAbsolutePath();
		String outputFile = absolutePath + ".decorDataBase.txt";
		File csvFile = new File(outputFile);
		
		csvProcessor.writeAll(csvFile, decorList, true);
		
	}
	public void writeTree() throws Exception {
		
		CsvProcessor<Tree> csvProcessor = new CsvProcessor<Tree>(Tree.class);
		String absolutePath = new File("").getAbsolutePath();
		String outputFile = absolutePath + ".treeDataBase.txt";
		File csvFile = new File(outputFile);
		
		csvProcessor.writeAll(csvFile, treeList, true);
		
	}
	public void writeFlower() throws Exception {
		
		CsvProcessor<Flower> csvProcessor = new CsvProcessor<Flower>(Flower.class);
		String absolutePath = new File("").getAbsolutePath();
		String outputFile = absolutePath + ".flowerDataBase.txt";
		File csvFile = new File(outputFile);
		
		csvProcessor.writeAll(csvFile, flowerList, true);
		
	}
	
	public void writeFloristProducts() {
		
		ticketList.forEach(t -> {
			try {
				t.writeProducts();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}

}
