package ITacademy.S303DeveloperTeam;


import java.util.ArrayList;
import Florist.Florist;
import firstMenu.FirstMenu;
import firstMenu.FloristUtils;
import input.Input;
import productsHierarchy.Decor;
import productsHierarchy.Flower;
import productsHierarchy.Product;
import productsHierarchy.Tree;
import ticket.Ticket;

public class App {
	
	public static void main(String[] args) {


		try {
			FloristUtils.readFlorists();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//FloristUtils.createDataBase();
		Florist f = FirstMenu.firstMenu();

		
	
		int choice;

		do {
			choice = menu();

			switch (choice) {
			case 1:
				try {
					f.addTree();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					f.addFlower();
				} catch (Exception e) {
				
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					f.addDecor();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					f.removeTree();
				} catch (Exception e) {
					System.err.println("Error! l'arbre amb aquest id no existeix actualment a la base de dades.");
					e.printStackTrace();
				}
				break;
			case 5:
				try {
					f.removeFlower();
				} catch (Exception e) {
					System.err.println("Error! la flor amb aquest id no existeix actualment a la base de dades.");
					e.printStackTrace();
				}
				break;
			case 6:
				try {
					f.removeDecor();
				} catch (Exception e) {
					System.err.println("Error! la decoració amb aquest id no existeix actualment a la base de dades.");
					e.printStackTrace();
				}
				break;
			case 7:
				f.addTicket();
				break;
			case 8:
				f.printStock();
				break;
			case 9:
				f.printStockQuantities();
				break;
			case 10:
				f.printValueTotal();
				break;
			case 11:
				f.showOldBuys();
				break;
			case 12:
				f.printTotalProfit();
				break;
			case 0:
				try {
					FloristUtils.writeDecors();
					FloristUtils.writeTrees();
					FloristUtils.writeFlowers();
					FloristUtils.writeFlorists();
					FloristUtils.writeTickets();
					FloristUtils.writeProducts();
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("Fins aviat maco!");
				break;

			default:
				System.out.println("Introdueix un numero valid del menu!!.");
			}
		} while (choice != 0);
	}

	public static int menu() {
		
		int option = 0;
		try {
			option = Input.readInt("*** Welcome to the florist app ***\n"
					+ "1- Afegir un arbre a la base de dades.\n"
					+ "2- Afegir una flor a la base de dades.\n"
					+ "3- Afegir una decoració a la base de dades.\n"
					+ "4- Retirar un arbre a la base de dades.\n"
					+ "5- Retirar una flor a la base de dades.\n"
					+ "6- Retirar una decoració a la base de dades.\n"
					+ "7- Generar un ticket de venda.\n"
					+ "8- Mostrar l'stock total de la floristeria.\n"
					+ "9- Mostrar l'stock total de la floristeria amb les quantitats.\n"
					+ "10- Mostrar el valor total de l'stock de la floristeria.\n"
					+ "11- Mostrar totes les compres antigues de la floristeria.\n"
					+ "12- Mostrar el total guanyat amb totes les vendes de la floristeria.\n"
					+ "0- Sortir de l'aplicació");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return option;
	}

}

