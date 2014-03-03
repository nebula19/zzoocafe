package models;

import com.fasterxml.jackson.annotation.JsonInclude;


public class ZzooResult {
	
	
	public static final int OK			= 0x0000;
	public static final int CLIENT_ERROR	= 0x1000;
	public static final int BAD_REQUEST	= 0x1001;
	public static final int SERVER_ERROR	= 0x2000;
	
	
	public int result;
	public String message;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public Object data;

	
	public ZzooResult(int resultCode) {
		this(resultCode, null);
	}

	public ZzooResult(int result, Object model) {
		
		this(result, getMessage(result), model);
	}
	
	public ZzooResult(int result, String message, Object model) {
		this.result = result;
		this.message = message;
		this.data = model;
	}



	
	public static String getMessage(int resultCode) {
		
		switch(resultCode) {
		case OK : 
			return "OK";
		case CLIENT_ERROR : 
			return "CLIENT_ERROR";
		case SERVER_ERROR : 
			return "SERVER_ERROR";
		case BAD_REQUEST : 
			return "BAD_REQUEST";
		default : 
			return "UNKNOWN_CODE";
		}
	}



	public static ZzooResult simpleJsonResult(int result, String message, Object model) {
		return new ZzooResult(result, message, model);
	}
}
