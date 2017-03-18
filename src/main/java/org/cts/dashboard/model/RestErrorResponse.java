package org.cts.dashboard.model;

/**
 * Custom User Portal Error Response
 * @author Dhiman Mondal
 *
 */
public class RestErrorResponse {

	private String errorCode;
	
	private String errorMessage;

	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public RestErrorResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public RestErrorResponse() {
		super();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
}
