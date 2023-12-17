package ITacademy.S303DeveloperTeam;


import java.util.ArrayList;
import Florist.Florist;
import firstMenu.FirstMenu;
import input.Input;
import productsHierarchy.Decor;
import productsHierarchy.Flower;
import productsHierarchy.Product;
import productsHierarchy.Tree;
import ticket.Ticket;

public class App {
	
	public static void main(String[] args) {

		
		//Florist f = createDataBase();
		Florist f = FirstMenu.firstMenu();
		
		
		int choice;

		do {
			choice = menu();

			switch (choice) {
			case 2:
				try {
					f.addTree();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					f.addFlower();
				} catch (Exception e) {
				
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					f.addDecor();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 5:
				try {
					f.removeTree();
				} catch (Exception e) {
					System.err.println("Error! l'arbre amb aquest id no existeix actualment a la base de dades.");
					e.printStackTrace();
				}
				break;
			case 6:
				try {
					f.removeFlower();
				} catch (Exception e) {
					System.err.println("Error! la flor amb aquest id no existeix actualment a la base de dades.");
					e.printStackTrace();
				}
				break;
			case 7:
				try {
					f.removeDecor();
				} catch (Exception e) {
					System.err.println("Error! la decoració amb aquest id no existeix actualment a la base de dades.");
					e.printStackTrace();
				}
				break;
			case 8:
				f.addTicket();
				break;
			case 9:
				f.printStock();
				break;
			case 10:
				f.printStockQuantities();
				break;
			case 11:
				f.printValueTotal();
				break;
			case 12:
				f.showOldBuys();
				break;
			case 13:
				f.printTotalProfit();
				break;
			case 0:
				System.out.println("Estas sortint de l'aplicacio");
				break;

			default:
				System.out.println("Introdueix un numero valid del menu!!.");
			}
		} while (choice != 0);
	}

	public static int menu() {
		
		int option = 0;
		try {
			option = Input.readInt("*** Welcome to the florist manager ***\n"
					+ "2- Afegir un arbre a la base de dades.\n"
					+ "3- Afegir una flor a la base de dades.\n"
					+ "4- Afegir una decoració a la base de dades.\n"
					+ "5- Retirar un arbre a la base de dades.\n"
					+ "6- Retirar una flor a la base de dades.\n"
					+ "7- Retirar una decoració a la base de dades.\n"
					+ "8- Generar un ticket de venda.\n"
					+ "9- Mostrar l'stock total de la floristeria.\n"
					+ "10- Mostrar l'stock total de la floristeria amb les quantitats.\n"
					+ "11- Mostrar el valor total de l'stock de la floristeria.\n"
					+ "12- Mostrar totes les compres antigues de la floristeria.\n"
					+ "13- Mostrar el total guanyat amb totes les vendes de la floristeria.\n"
					+ "0- Sortir de l'aplicació\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return option;
	}
	public static Florist  createDataBase() {
		
		Florist f = new Florist("Floristeria 1");
		
		f.getTreeList().add(new Tree("arbre1", 33, 12, 3));
		f.getTreeList().add(new Tree("arbre2", 23.1f, 13, 1));
		f.getTreeList().add(new Tree("arbre3", 2.6f, 14, 1));
		f.getTreeList().add(new Tree("arbre4", 2.6f, 15, 1));
		f.getTreeList().add(new Tree("arbre5", 27.5f, 16, 1));
		f.getTreeList().add(new Tree("arbre6", 28.6f, 17, 1));
		f.getFlowerList().add(new Flower("flor1", 2, "blau", 2));
		f.getFlowerList().add(new Flower("flor2", 2, "blau", 5));
		f.getDecorList().add(new Decor("decor1", 2, "fusta", 2));

		//System.out.println("preu total: " + f.valueTotal() + "€");
		
		
//		// escribe en un txt las flotisterias.
//		ArrayList <Florist> allFlorist = new ArrayList<Florist>();
//		allFlorist.add(f);
//		String absolutePath = new File("").getAbsolutePath();
//		String outputFile = absolutePath + ".dataBase.txt";
//		Writter.writeText(allFlorist, outputFile);
		
		//simula una venta con la creacion de su ticket
		ArrayList <Product> testSell = new ArrayList<Product>();		
		testSell.add(f.getTreeList().get(0));
		testSell.add(f.getTreeList().get(1));	
		Ticket ticket = new Ticket(f.getName(), f/*,testSell*/);
		
		System.out.println(ticket);
		
		return f;

		
	}
}

