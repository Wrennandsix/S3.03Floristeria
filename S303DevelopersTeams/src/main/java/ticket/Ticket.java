package ticket;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.j256.simplecsv.common.CsvColumn;
import com.j256.simplecsv.processor.CsvProcessor;

import Florist.Florist;
import input.Input;
import productsHierarchy.Product;


public class Ticket {
	@CsvColumn(columnName = "name")
	private String name;
	private Florist florist;
	private ArrayList<Product> productsList = new ArrayList<Product>();
	@CsvColumn(columnName = "price")
	private float price = 0;
	@CsvColumn(columnName = "id")
	private int id;
	private static int nextId = 0;
	
	public Ticket(){
		this.id = ++nextId;
	}
	
	public Ticket(String name, Florist florist) {
		this.florist = florist;
		this.id = ++nextId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProductsList() {
		return productsList;
	}

	public void setProductsList(ArrayList<Product> productsList) {
		this.productsList = productsList;
		
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public void addProducts() throws Exception {
		
		int opcio;

		do {
			opcio = Input.readInt("Afegir productes al ticket:"
					+ "\n1- Agegir arbre."
					+ "\n2- Afegir flor."
					+ "\n3- Afegir decoració."
					+ "\n0- Deixar d'afegir productes");

			switch (opcio) {
			case 1:
				try {
					productsList.add(florist.withdrawTree());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					productsList.add(florist.withdrawFlower());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					productsList.add(florist.withdrawDecor());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 0:
				System.out.println("Gracies per la compra, productes comprats:"+ productsList);
		
				break;

			default:
				System.out.println("Introdueix un numero valid del menu!!.");
			}
		} while (opcio != 0);
	}
		



	public float calculatePrice() {

		return (float) productsList.stream().mapToDouble(p -> p.getPrice() * p.getStock()).sum();
	}
	
	public void readProducts() throws IOException, ParseException {
		CsvProcessor<Product> csvProcessor = new CsvProcessor<Product>(Product.class);

        String absolutePath = new File("").getAbsolutePath();
        String outputFile = absolutePath + "." + id + florist.getName() + "productDataBase.txt";
        File csvFile = new File(outputFile);
        productsList = (ArrayList<Product>) csvProcessor.readAll(csvFile, null);
        productsList.forEach(p -> System.out.println(p.toString()));	
	}
	
	public void writeProducts() throws Exception {

		CsvProcessor<Product> csvProcessor = new CsvProcessor<Product>(Product.class);
		String absolutePath = new File("").getAbsolutePath();
		String outputFile = absolutePath + "." + id + florist.getName() + "productDataBase.txt";
		File csvFile = new File(outputFile);
		csvProcessor.writeAll(csvFile, productsList, true);
		System.out.println("Productes del ticket "+ name +" guardades exitosament a la base de dades.");

	}
	
	@Override
	public String toString() {
		return "Ticket [name=" + name + ", productsList=" + productsList + ", Price=" + price + ", id=" + id + "]";
	}
}
