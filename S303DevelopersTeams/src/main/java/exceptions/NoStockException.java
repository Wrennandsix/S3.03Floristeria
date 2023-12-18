package exceptions;

public class NoStockException extends Exception{
	
	private static final long serialVersionUID = 1L;


	public NoStockException(){
	    }
	    
	    
	    public String getMessage(){
	        return "error! Stock Insuficient.";
	    }

}
