package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import Florist.Florist;
import productsHierarchy.Decor;
import productsHierarchy.Flower;
import productsHierarchy.Tree;
import ticket.Ticket;

import java.util.List;
import java.util.Map;

public class Reader {

	    public static ArrayList<Florist> readText(String inputFile) {
	        ArrayList<Florist> florists = new ArrayList<>();

	        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                Florist florist = parseFlorist(line);
	                if (florist != null) {
	                    florists.add(florist);
	                }
	            }

	            System.out.println("Data successfully read from the file: " + inputFile);
	        } catch (IOException e) {
	            System.out.println("An error occurred while reading from the file: " + e.getMessage());
	        }

	        return florists;
	    }

	    @SuppressWarnings("unchecked")
		private static Florist parseFlorist(String line) {
	        String content = line.replaceAll("^.*?\\[", "").replaceAll("\\].*$", "");

	        String[] keyValuePairs = content.split(", ");

	        Map<String, List<?>> propertyMap = new HashMap<>();
	        String name = null;
	        int id = 0;


	        for (String pair : keyValuePairs) {
	            String[] parts = pair.split("=");
	            if (parts.length == 2) {
	                String key = parts[0];
	                String value = parts[1];

	                if ("name".equals(key)) {
	                    name = value;
	                } else if ("id".equals(key)) {
	                    id = Integer.parseInt(value);
	                } else {
	                    List<?> propertyList = parsePropertyList(value);
	                    propertyMap.put(key, propertyList);
	                }
	            } else {
	                System.out.println("Invalid key-value pair: " + pair);
	            }
	        }

	        return new Florist(name,
	                (List<Tree>) propertyMap.getOrDefault("treeList", Collections.emptyList()),
	                (List<Flower>) propertyMap.getOrDefault("flowerList", Collections.emptyList()),
	                (List<Decor>) propertyMap.getOrDefault("decorList", Collections.emptyList()),
	                (List<Ticket>) propertyMap.getOrDefault("ticketsList", Collections.emptyList()),
	                id);
	    }
	    private static List<?> parsePropertyList(String value) {
	        if (value.startsWith("Tree")) {
	            return parseTreeList(value);
	        } else if (value.startsWith("Flower")) {
	            return parseFlowerList(value);
	        } else if (value.startsWith("Decor")) {
	            return parseDecorList(value);
	        } else if (value.startsWith("Ticket")) {
	            return parseTicketList(value);
	        } else {
	            return Collections.emptyList();
	        }
	    }

	    private static List<Decor> parseDecorList(String value) {
	        value = value.substring(1, value.length() - 1);
	        String[] decorStrings = value.split(", ");
	        List<Decor> decorList = new ArrayList<>();

	        for (String decorString : decorStrings) {
	            decorList.add(parseDecor(decorString));
	        }

	        return decorList;
	    }

	    private static Decor parseDecor(String decorString) {
	        String type = extractStringValue(decorString, "type");
	        String name = extractStringValue(decorString, "name");
	        float price = extractFloatValue(decorString, "price");
	        int id = extractId(decorString);

	        return new Decor(name, price, type, id);
	    }

	    private static List<Flower> parseFlowerList(String value) {
	        value = value.substring(1, value.length() - 1);
	        String[] flowerStrings = value.split(", ");
	        List<Flower> flowerList = new ArrayList<>();

	        for (String flowerString : flowerStrings) {
	            flowerList.add(parseFlower(flowerString));
	        }

	        return flowerList;
	    }

	    private static Flower parseFlower(String flowerString) {
	        String colour = extractStringValue(flowerString, "colour");
	        String name = extractStringValue(flowerString, "name");
	        float price = extractFloatValue(flowerString, "price");
	        int id = extractId(flowerString);

	        return new Flower(name, price, colour, id);
	    }

	    private static List<Tree> parseTreeList(String value) {
	        value = value.substring(1, value.length() - 1);
	        String[] treeStrings = value.split(", ");
	        List<Tree> treeList = new ArrayList<>();

	        for (String treeString : treeStrings) {
	            treeList.add(parseTree(treeString));
	        }

	        return treeList;
	    }

	    private static Tree parseTree(String treeString) {
	        float height = extractFloatValue(treeString, "height");
	        String name = extractStringValue(treeString, "name");
	        float price = extractFloatValue(treeString, "price");
	        int id = extractId(treeString);

	        return new Tree(name, price, height, id);
	    }

	    private static List<Ticket> parseTicketList(String value) {
	        value = value.substring(1, value.length() - 1);
	        String[] ticketStrings = value.split(", ");
	        List<Ticket> ticketList = new ArrayList<>();

	        for (String ticketString : ticketStrings) {
	            ticketList.add(parseTicket(ticketString));
	        }

	        return ticketList;
	    }

	    private static Ticket parseTicket(String ticketString) {
	        String name = extractStringValue(ticketString, "name");
	        String productsList = extractStringValue(ticketString, "productsList");
	        float price = extractFloatValue(ticketString, "Price");
	        int id = extractId(ticketString);

	        return new Ticket(name, productsList, price, id);
	    }

	    private static float extractFloatValue(String input, String key) {
	        String[] parts = input.split(key + "=");
	        if (parts.length > 1) {
	            String value = parts[1].split(",")[0].trim();
	            return Float.parseFloat(value);
	        } else {
	            return 0.0f;
	        }
	    }

	    private static String extractStringValue(String input, String key) {
	        String[] parts = input.split(key + "=");
	        if (parts.length > 1) {
	            String value = parts[1].split(",")[0].trim();
	            return value.substring(1, value.length() - 1);
	        } else {
	            return null;
	        }
	    }

	    private static int extractId(String input) {
	        String[] parts = input.split("id=");
	        if (parts.length > 1) {
	            String idPart = parts[1].split("[^0-9]")[0];
	            try {
	                return Integer.parseInt(idPart);
	            } catch (NumberFormatException e) {
	                System.out.println("Error parsing id: " + e.getMessage());
	                return -1;
	            }
	        } else {
	            return -1;
	        }
	    }
	}

	
