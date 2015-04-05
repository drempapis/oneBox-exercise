package org.onebox.trains.exception;

public class ValidationInputException extends RuntimeException {

	private static final long serialVersionUID = 3506807800012105824L;

	private String error;

	public ValidationInputException() {
		super();
		error = "unknown";
	}

	public ValidationInputException(String error) {
		super(error);
		this.error = error;
	}

	public String getError() {
		return error;
	}
}
