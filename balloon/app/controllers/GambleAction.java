package controllers;

import models.ebeans.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.JsonUtil;

public class GambleAction extends Controller {

	static Form<GambleAction> scoreForm = Form.form(GambleAction.class);
	
    public static Result update(Long id) {
    	return ok(JsonUtil.getJsonResult(0, "ok", User.get(id)));
    }

    public static Result list() {
        return ok(JsonUtil.getJsonResult(0, "ok", User.list()));
    }
    
}
