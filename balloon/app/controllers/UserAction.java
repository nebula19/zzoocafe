package controllers;

import static play.data.Form.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.ZzooResult;
import models.ebeans.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.JsonUtil;



public class UserAction extends Controller {

	static Form<User> userForm = Form.form(User.class);
	
	
	/**
	 * 사용자가 앱을 깔고 처음 balloon 서버에 접속할 때 호출.
	 * facebook id를 가지고 접속해야 함.
	 * 기 존재하는 facebook 계정은 기존 사용자 정보를 그대로 사용하고
	 * 기존에 서버에 등록되지 않았던 facebook 계정은 사용자를 새로 등록한다.
	 *  
	 * @return
	 */
	public static Result subscribe() {
		
		String socialId = form().bindFromRequest().get("socialAccountId");
		
		if (socialId == null  ) {
			return badRequest(JsonUtil.getJsonResult(ZzooResult.BAD_REQUEST, "a social id is required."));
		}
		else {
			User user = User.getAccountInfoBySocialId(socialId);
			if (user != null) {
				return ok(JsonUtil.getJsonResult(ZzooResult.OK, user));
			}
			else {
				return createNewUser();
			}
		}
//		return internalServerError(JsonUtil.getJsonResult(ZzooResult.SERVER_ERROR, "subscription falied."));
	}
	

    private static Result createNewUser() {
    	Form<User> userForm = form(User.class).bindFromRequest("socialAccountId");
    	if (userForm.hasErrors()) {
    		badRequest(JsonUtil.getJsonResult(ZzooResult.BAD_REQUEST));
    	}
    	
    	User user = userForm.get();
    	user.createDate = new Date();
    	// 사용자를 DB에 저장.
    	user.save();

    	// 기본무기 2개 추가해주기.
    	User.purchaseWeaon(user, 1L);
    	User.purchaseWeaon(user, 2L);
    	
    	flash("success", "User " + userForm.get().username + " has been created");
    	return ok(JsonUtil.getJsonResult(ZzooResult.OK, user));
    }
    
	
    /**
     * 사용자 정보 조회. 기본 정보 + 게임정보.
     * 
     * @param id
     * @return
     */
    public static Result get(Long id) {
    	return ok(JsonUtil.getJsonResult(ZzooResult.OK, "ok", User.getAccountInfo(id)));
    }

    /**
     * 전체 사용자 랭킹. 10명씩 페이징하도록 되어 있음.
     * @param pageNum
     * @return
     */
    public static Result list(int pageNum) {
        return ok(JsonUtil.getJsonResult(ZzooResult.OK, "ok", User.list(pageNum)));
    }
    

    
    
    /*
     * ========================
     * 아래 함수들은 사용안하기로 함.
     * 일부 함수는 미구현상태임
     * ========================
     */
    
    public static Result getBySocialId(String socialId) {
    	
    	User user = User.getAccountInfoBySocialId(socialId);
    	
    	if (user == null) {
        	Form<User> userForm = form(User.class).bindFromRequest();
        	if (userForm.hasErrors()) {
        		badRequest(JsonUtil.getJsonResult(ZzooResult.BAD_REQUEST));
        	}
        	user = userForm.get();
        	user.username = socialId;
        	user.socialAccountId = socialId;
        	user.save();
    	}
    	
    	return ok(JsonUtil.getJsonResult(ZzooResult.OK, "ok", user));
    }

    
    public static List<UserAction> all() {
      return new ArrayList<UserAction>();
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
