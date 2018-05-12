package com.efficientproject.web.error;

public class OrganizationException extends RuntimeException {
	
	private static final long serialVersionUID = -1455845429036771582L;

	public OrganizationException() {
		super();
	}

	public OrganizationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrganizationException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrganizationException(String message) {
		super(message);
	}

	public OrganizationException(Throwable cause) {
		super(cause);
	}
}
