package com.efficientproject.web.error;

public class OrganizationAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 6975772602532988620L;

	public OrganizationAlreadyExistException() {
		super();
	}

	public OrganizationAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrganizationAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrganizationAlreadyExistException(String message) {
		super(message);
	}

	public OrganizationAlreadyExistException(Throwable cause) {
		super(cause);
	}

}
