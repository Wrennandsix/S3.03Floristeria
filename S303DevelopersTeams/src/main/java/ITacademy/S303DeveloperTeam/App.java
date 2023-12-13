package ITacademy.S303DeveloperTeam;

import Florist.Florist;
import Florist.FloristUtils;
import exceptions.NoStockException;
import productsHierarchy.Decor;
import productsHierarchy.Flower;
import productsHierarchy.Tree;

public class App {
	
    public static void main( String[] args ) {
    
    	
    	Florist f = new Florist("Floristeria 1");
    	
    	f.getTreeList().add(new Tree("abre1", 2, 12, 2));
    	f.getTreeList().add(new Tree("abre2", 2, 12, 2));
    	f.getFlowerList().add(new Flower("flor1", 2, "blau", 2));
    	f.getFlowerList().add(new Flower("flor1", 2, "blau", 5));
    	f.getDecorList().add(new Decor("flor1", 2, "fusta", 2));
    	
    	System.out.println("preu total: " + f.valueTotal() + "€");
    	
    	try {
			f.removeTree();
		} catch (NoStockException e) {
			e.printStackTrace();
		}

    }
}
