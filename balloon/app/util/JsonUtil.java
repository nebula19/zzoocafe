package util;

import models.ZzooResult;
import play.libs.Json;
import play.mvc.Content;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtil {
	
	
	/**
	 * 
	 * @param result API result
	 * @param message
	 * @param model	
	 * @return
	 */
	public static JsonNode getJsonResult(int result, String message, Object model) {
		ZzooResult jsonResult = new ZzooResult(result, message, model);
		return Json.toJson(jsonResult);
	}

	public static JsonNode getJsonResult(int resultCode) {
		ZzooResult jsonResult = new ZzooResult(resultCode);
		return Json.toJson(jsonResult);
	}
	

}
