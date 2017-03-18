package org.cts.dashboard.utility;

@SuppressWarnings("serial")
public class DashboardSystemException extends Exception{

	public DashboardSystemException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DashboardSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DashboardSystemException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DashboardSystemException(Throwable cause) {
		super(cause);
	}
	
	

}
