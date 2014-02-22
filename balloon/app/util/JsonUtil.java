package util;

import models.JsonResult;
import play.db.ebean.Model;
import play.libs.Json;

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
		JsonResult jsonResult = new JsonResult(result, message, model);
		return Json.toJson(jsonResult);
	}
	

}
