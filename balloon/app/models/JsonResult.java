package models;


public class JsonResult {
	public int result;
	public String message;
	
	public Object data;

	
	public JsonResult(int result2, String message2, Object model) {
		this.result = result2;
		this.message = message2;
		this.data = model;
	}

}
