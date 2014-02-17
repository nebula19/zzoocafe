package controllers;

import java.util.ArrayList;
import java.util.List;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.user;
import views.html.user_list;

public class UserAction extends Controller {

	
	static Form<UserAction> userForm = Form.form(UserAction.class);
	
    public static Result get(Long id) {
//        return ok(index.render("Your new application is ready."));
    	return ok(user.render(User.get(id)));
    }

    public static Result list() {
    	
        return ok(user_list.render(User.list()));
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
