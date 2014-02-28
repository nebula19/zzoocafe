package controllers;

import models.ebeans.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.JsonUtil;

public class BuffAction extends Controller {

	static Form<BuffAction> buffForm = Form.form(BuffAction.class);
	
    public static Result update(Long id) {
    	return ok(JsonUtil.getJsonResult(0, "ok", new User()));
    }

    public static Result get() {
        return ok(JsonUtil.getJsonResult(0, "ok", new User()));
    }
}
