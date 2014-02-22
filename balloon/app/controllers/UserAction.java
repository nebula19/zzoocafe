package controllers;

import java.util.ArrayList;
import java.util.List;

import models.ebeans.User;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.JsonUtil;
import views.html.*;

public class UserAction extends Controller {

	
	static Form<UserAction> userForm = Form.form(UserAction.class);
	
    public static Result get(Long id) {
//        return ok(index.render("Your new application is ready."));
//    	return ok(JsonUtil.getJsonResult(0, "ok", User.get(id)));
    	//return ok(user.render(User.get(id)));
    	return ok(user.render(JsonUtil.getJsonResult(0, "ok", User.get(id))));
    }

    public static Result list() {
    	
        return ok(JsonUtil.getJsonResult(0, "ok", User.list()));
    }
    
    public static List<UserAction> all() {
      return new ArrayList<UserAction>();
    }
    
    public static Result create(User task) {
    	return ok();
    }
    
    public static Result delete(Long id) {
    	return ok();
    }


}
