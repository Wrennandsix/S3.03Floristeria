package exceptions;

public class NoStockException extends Exception{
	
	 public NoStockException(){
	    }
	    
	    
	    public String getMessage(){
	        return "error! Stock Insuficient.";
	    }

}
