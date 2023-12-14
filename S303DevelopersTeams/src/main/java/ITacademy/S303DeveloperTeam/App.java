package ITacademy.S303DeveloperTeam;

import java.util.Scanner;

import Florist.Florist;
import Florist.FloristUtils;
import exceptions.NoStockException;
import productsHierarchy.Decor;
import productsHierarchy.Flower;
import productsHierarchy.Tree;

public class App {
	
	public static void main(String[] args) {

	
		/*try {
			f.removeTree();
		} catch (NoStockException e) {
			System.out.println(e.getMessage());
		}*/
		Florist f = createDataBase();
		
		int opcio;

		do {
			opcio = menu();

			switch (opcio) {
			case 1:
				f.showTree();
				String name = f.requestNewTreeName();
				float height = f.requestNewTreeHeight();
				Tree tree = f.findTree(name,height);
				f.addNewTree(tree);
				break;
			case 2:
				f.showTree();
				int id2 = f.requestTree();
				Tree tree2 = f.findTree(id2);
				f.addStockTree(tree2);
				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			case 7:

				break;
			case 8:

				break;
			case 0:
				System.out.println("Estas sortint de l'aplicacio");
				break;

			default:
				System.out.println("Introdueix un numero valid del menu!!.");
			}
		} while (opcio != 0);
	}

	public static int menu() {
		
		Scanner sc = new Scanner(System.in);
		
		int option = 0;
		System.out.println("*** Welcome to the florist manager ***\n" + "1- Afegir un nou arbre a la base de dades.\n"
				+ "2- Afegir stock a un arbre ja existent a la base de dades\n"
				+ "3- Show the lowest priced items from all the sellers in a city\n"
				+ "4- Show all items of an item type ordered by price.\n" + "5- Buy an item from another Person.\n"
				+ "6- Create person in our database\n" + "7- Add new item to a Person\n" + "8- Shut down the app");
		
		option = sc.nextInt();	
		return option;
	}
	public static Florist  createDataBase() {
		
		Florist f = new Florist("Floristeria 1");
		
		f.getTreeList().add(new Tree("arbre1", 2, 12, 2));
		f.getTreeList().add(new Tree("arbre2", 2, 12, 2));
		f.getFlowerList().add(new Flower("flor1", 2, "blau", 2));
		f.getFlowerList().add(new Flower("flor1", 2, "blau", 5));
		f.getDecorList().add(new Decor("flor1", 2, "fusta", 2));

		//System.out.println("preu total: " + f.valueTotal() + "€");
		
		return f;

		
	}
}

