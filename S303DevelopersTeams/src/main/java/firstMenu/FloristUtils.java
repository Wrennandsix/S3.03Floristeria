package firstMenu;

import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import Florist.Florist;
import input.Input;
import productsHierarchy.Decor;
import productsHierarchy.Flower;
import productsHierarchy.Tree;


public class FloristUtils {
	
	private static ArrayList<Florist> florists = new ArrayList<Florist>();
	
	public void showFlorists() {

		System.out.println("**Floristeries**");
		florists.forEach(florist -> System.out.println("Name: " + florist.getName() + ", id: " + florist.getId()));

	}

	public int findFlorist(int id) {

		OptionalInt index = IntStream.range(0, florists.size())
				.filter(i -> florists.get(i).getId() == id)
				.findFirst();

		return index.orElse(-1);
	}

	public int requestId() throws Exception {

		int id = Input.readInt("Introdueix el id de la floristeria a la que vols accedir.");

		return id;
	}
	public int requestIdAdd() throws Exception {

		int id = Input.readInt("Introdueix el id de la floristeria per comprobar si ja existeix a la base de dades.");

		return id;
	}

	public void addFlorist() throws Exception {
		showFlorists();
		int id = requestIdAdd();
		
		int index = findFlorist(id);

		if (index == -1) {
			String name = Input.readString("Introdueix el nom de la floristeria que vols afegir a la base de dades");
			Florist florist = new Florist(name);
			florists.add(florist);
			System.out.println("Floristeria amb nom: "+florist.getName()+" y id: "+florist.getId()+" afegida correctament a la base de dades.");

		} else {
			System.out.println("La floristeria que intentes afegir amb: "+id+" ja existeix en la base de dades");

		}
	}

	public Florist accesFlorist() throws Exception {
	 
	    
	    Florist florist = null;

	    do {
	    	showFlorists();
	        int id = requestId();
	        int index = findFlorist(id);

	        if (index == -1) {
	            System.out.println("La floristeria amb id: " + id + ", a la que intentas accedir no existeix en la base de dades.");
	        } else {
	            florist = florists.get(index);
	        }
	    } while (florist == null);

	    return florist;
	}
 
	public static void  createDataBase() {
		
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

	    florists.add(f);

		
	}

}
