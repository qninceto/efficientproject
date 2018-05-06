package com.efficientproject.web.error;

public class UserDtoException extends RuntimeException {
	
	private static final long serialVersionUID = -1455845429036771582L;

	public UserDtoException() {
		super();
	}

	public UserDtoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserDtoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserDtoException(String message) {
		super(message);
	}

	public UserDtoException(Throwable cause) {
		super(cause);
	}
}
