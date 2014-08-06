package controllers;

import models.ebeans.User;
import play.mvc.Controller;
import play.mvc.Result;
import util.JsonUtil;

public class SocialAction extends Controller {

	public static Result friendList(Long id) {
		return ok(JsonUtil.getJsonResult(0, User.listOrderByScore(0)));
	}
    
}
