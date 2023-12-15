package writter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import Florist.Florist;

public class Writter {
	
    public static void writeText(ArrayList<Florist> allFlorist, String outputFile) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write("***Floristeries***");
            writer.newLine();
            for (Florist florist: allFlorist) {
            	writer.write(florist.toString());         
                writer.newLine();
            }
            System.out.println("The data was successfully written to the file: "+outputFile);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
