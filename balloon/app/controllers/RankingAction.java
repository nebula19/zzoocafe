package controllers;

import models.ebeans.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.JsonUtil;

public class RankingAction extends Controller {

	static Form<RankingAction> buffForm = Form.form(RankingAction.class);
	
    public static Result getTopRanks(Long id) {
    	return ok(JsonUtil.getJsonResult(0, "ok", new User()));
    }

    public static Result get() {
        return ok(JsonUtil.getJsonResult(0, "ok", new User()));
    }
    
}
