package es.udc.paproject.backend.model.exceptions;

@SuppressWarnings("serial")
public class InvalidParameterException extends InstanceException {

    public InvalidParameterException(String name, Object key) {
    	super(name, key); 	
    }
    
}
