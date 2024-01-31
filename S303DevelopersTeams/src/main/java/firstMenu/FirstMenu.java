package firstMenu;


import java.io.IOException;
import java.text.ParseException;

import Florist.Florist;
import input.Input;

public class FirstMenu {

	public static Florist firstMenu() {

		
		FloristUtils manager = new FloristUtils();		
		//FloristUtils.createDataBase();

		int choice;

		Florist florist = null;

		do {
			choice = menu();

			switch (choice) {
			case 1:
				try {
					manager.addFlorist();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					florist = manager.accesFlorist();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Introdueix un numero valid del menu!!.");
			}
		} while (choice != 2);
		return florist;
	}

	public static int menu() {

		int option = 0;
		try {
			option = Input.readInt("*** Welcome florist acces ***\n"
					+ "1- Afegir una floristeria a la base de dades.\n"
					+ "2- Accedir a una floristeria existent a la base de dades.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return option;
	}


}

