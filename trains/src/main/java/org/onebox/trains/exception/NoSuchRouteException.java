package org.onebox.trains.exception;

public class NoSuchRouteException extends RuntimeException {
	
private static final long serialVersionUID = 3506807800012105824L;
	
	private String error;

	public NoSuchRouteException() {
		super();
		error = "unknown";
	}

	public NoSuchRouteException(String error) {
		super(error); 
		this.error = error; 
	}

	public String getError() {
		return error;
	}
}
