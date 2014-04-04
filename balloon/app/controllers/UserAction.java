package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.List;

import models.ZzooResult;
import models.ebeans.User;
import models.ebeans.UserWeapon;
import models.ebeans.Weapon;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.JsonUtil;



public class UserAction extends Controller {

	static Form<User> userForm = Form.form(User.class);
	
    public static Result get(Long id) {
    	return ok(JsonUtil.getJsonResult(ZzooResult.OK, "ok", User.getAccountInfo(id)));
    }

    public static Result list() {
        return ok(JsonUtil.getJsonResult(ZzooResult.OK, "ok", User.list()));
    }
    
    public static List<UserAction> all() {
      return new ArrayList<UserAction>();
    }
    
    public static Result create() {
    	Form<User> userForm = form(User.class).bindFromRequest();
    	if (userForm.hasErrors()) {
    		badRequest(JsonUtil.getJsonResult(ZzooResult.BAD_REQUEST));
    	}
    	
    	System.out.println("success." + "User " + userForm.get().username + " has been created");
    	
    	// 사용자를 DB에 저장.
    	User user = userForm.get();
    	user.save();

    	// 기본무기 2개 추가해주기.
    	User.purchaseWeaon(user, 1L);
    	User.purchaseWeaon(user, 2L);
    	
    	flash("success", "User " + userForm.get().username + " has been created");
    	return ok(JsonUtil.getJsonResult(ZzooResult.OK));
    }
    
    public static Result delete(Long id) {
    	return ok();
    }

    
    public static Result update() {
    	Form<User> userForm = form(User.class).bindFromRequest();
    	if (userForm.hasErrors()) {
    		badRequest(JsonUtil.getJsonResult(ZzooResult.BAD_REQUEST));
    	}
    	
    	System.out.println("success." + "User " + userForm.get().username + " updating");
    	userForm.get().update();
    	flash("success", "User " + userForm.get().username + " has been updated.");
    	return ok(JsonUtil.getJsonResult(ZzooResult.OK));
    }
    

    

}
