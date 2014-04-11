package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.WeaponInfoService;
import util.JsonUtil;

public class AdminAction extends Controller {

	
	public static Result updateWeaponDBFromGoogleDoc() {
		boolean result = WeaponInfoService.updateWeaponInfoDB();
		if (result) {
			return ok(JsonUtil.getJsonResult(0));
		}
		else {
			return ok(JsonUtil.getJsonResult(INTERNAL_SERVER_ERROR, "donno - nebula", null));
		}
	}
    
}
