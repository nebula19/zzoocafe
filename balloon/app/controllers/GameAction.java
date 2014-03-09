package controllers;

import static play.data.Form.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.ZzooResult;
import models.ebeans.User;
import models.ebeans.UserWeapon;
import models.ebeans.Weapon;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.JsonUtil;

public class GameAction extends Controller {

	
	public static Result getPlayData(Long id) {
		return ok(JsonUtil.getJsonResult(0, User.getPlayData(id)));
	}
	
	
	/**
	 * 게임 결과 정보 저장.
	 * 
	 * @param id
	 * @return
	 */
	public static Result updatePlayData(Long id) {
		Form<User> userForm = form(User.class).bindFromRequest("score", "gold", "oil", "diamond");
		
    	if (userForm.hasErrors()) {
    		badRequest(JsonUtil.getJsonResult(ZzooResult.BAD_REQUEST));
    	}
    	userForm.data().put("user_id", String.valueOf(id));

    	
		userForm.get().update(id);
		return ok(JsonUtil.getJsonResult(0));
	}


	// PUT		/api2/user/:id/weapons/:weaponId/upgrade
	public static Result upgradeWeapon(Long id, Long weaponId) {
		// TODO implement.
		return ok(JsonUtil.getJsonResult(0));
	}

	// PUT		/api2/user/:id/weapons/:weaponId/equip
	public static Result equipWeapon(Long id, Long weaponId) {
		// TODO implement needed
		return ok(JsonUtil.getJsonResult(0));
	}
	
	
	public static Result purchaseWeapon(Long id) {
		//  currently same with gamble weapon.
		return gambleWeapon(id);
	}

	
	public static Result gambleWeapon(Long id) {
		long r = (long)(Math.random() * 3) + 1;
		System.out.println("new weapon id : " + r);
		
		User.purchaseWeaon(id, r);
		
		Map<String, List<UserWeapon>> weaponList = new HashMap<String, List<UserWeapon>>();
		weaponList.put("user_weapons", User.find.byId(id).weapons);

		return ok(JsonUtil.getJsonResult(0, weaponList));
	}

	
	
	
	public static Result getWeaponList() {
		return ok(JsonUtil.getJsonResult(0, Weapon.list()));
	}
	
    
}
