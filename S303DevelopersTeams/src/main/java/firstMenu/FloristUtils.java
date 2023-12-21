package firstMenu;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import com.j256.simplecsv.processor.CsvProcessor;
import Florist.Florist;
import input.Input;
import productsHierarchy.Decor;
import productsHierarchy.Flower;
import productsHierarchy.Tree;
import ticket.Ticket;


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
		
		Florist f = new Florist("FloristeriaNunito");


		f.getTreeList().add(new Tree("Pi", 12.4f, 3, 33.9f));
		f.getFlowerList().add(new Flower("Tulipa", 2, 2, "Groc"));
		f.getDecorList().add(new Decor("Test", 2.50f, 2, "Plastic"));

		florists.add(f);
	
	}

	public static void readFlorists() throws IOException, ParseException{

        CsvProcessor<Florist> csvProcessor = new CsvProcessor<Florist>(Florist.class);

        String absolutePath = new File("").getAbsolutePath();
        String outputFile = absolutePath + ".floristDataBase.txt";
        File csvFile = new File(outputFile);
        florists = (ArrayList<Florist>) csvProcessor.readAll(csvFile, null);
        florists.forEach(f -> {
			try {
				f.readTrees();
				f.readFlowers();
				f.readDecors();
				f.readTickets();
				f.readProducts();
			} catch (IOException | ParseException e) {	
				e.printStackTrace();
			}
		});
        System.out.println("Floristerias carregades correctament.");
        //florists.forEach(f -> System.out.println(f.toString()));
    }
	public static void writeFlorists() throws Exception {
		
		CsvProcessor<Florist> csvProcessor = new CsvProcessor<Florist>(Florist.class);
		String absolutePath = new File("").getAbsolutePath();
		String outputFile = absolutePath + ".floristDataBase.txt";
		File csvFile = new File(outputFile);
		csvProcessor.writeAll(csvFile, florists, true);
		System.out.println("Floristeries guardades exitosament a la base de dades.");
	}

	public static void writeDecors() throws Exception {

		for (Florist florist : florists) {
			CsvProcessor<Decor> csvProcessor = new CsvProcessor<Decor>(Decor.class);
			String absolutePath = new File("").getAbsolutePath();
			String outputFile = absolutePath + "."+florist.getName()+"decorDataBase.txt";
			File csvFile = new File(outputFile);
			csvProcessor.writeAll(csvFile, florist.getDecorList(), true);
			System.out.println("Decoracions de la floristeria "+florist.getName() +" guardades exitosament a la base de dades.");
		}				
	}
	public static void writeTrees() throws Exception {

		for (Florist florist : florists) {
			CsvProcessor<Tree> csvProcessor = new CsvProcessor<Tree>(Tree.class);
			String absolutePath = new File("").getAbsolutePath();
			String outputFile = absolutePath + "."+florist.getName()+"treeDataBase.txt";
			File csvFile = new File(outputFile);
			csvProcessor.writeAll(csvFile, florist.getTreeList(), true);
			System.out.println("Arbres de la floristeria "+florist.getName() +" guardats exitosament a la base de dades.");
		}				
	}

	public static void writeFlowers() throws Exception {

		for (Florist florist : florists) {
			CsvProcessor<Flower> csvProcessor = new CsvProcessor<Flower>(Flower.class);
			String absolutePath = new File("").getAbsolutePath();
			String outputFile = absolutePath + "."+florist.getName()+"flowerDataBase.txt";
			File csvFile = new File(outputFile);
			csvProcessor.writeAll(csvFile, florist.getFlowerList(), true);
			System.out.println("Flors de la floristeria "+florist.getName() +" guardades exitosament a la base de dades.");
		}				
	}
	public static void writeTickets() throws Exception {

		for (Florist florist : florists) {
			CsvProcessor<Ticket> csvProcessor = new CsvProcessor<Ticket>(Ticket.class);
			String absolutePath = new File("").getAbsolutePath();
			String outputFile = absolutePath + "."+florist.getName()+"ticketDataBase.txt";
			File csvFile = new File(outputFile);
			csvProcessor.writeAll(csvFile, florist.getTicketList(), true);
			System.out.println("Tickets de la floristeria "+florist.getName() +" guardades exitosament a la base de dades.");
		}				
	}
	
	public static void writeProducts() {
		
		florists.forEach(f -> f.writeFloristProducts());
	}
	
}