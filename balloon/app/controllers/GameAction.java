package controllers;

import static play.data.Form.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.Expression;

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


	// PUT		/api2/user/:id/weapons/:weaponUid/upgrade
	public static Result upgradeWeapon(Long userId, Long weaponUid) {
		UserWeapon userWeapon = UserWeapon.find.byId(weaponUid);
		
		Form<UserWeapon> userWeaponForm = form(UserWeapon.class).bindFromRequest("power", "shootingRate");
		
		if (userWeapon == null) {
			return ok(JsonUtil.getJsonResult(BAD_REQUEST, "소지하지 않은 무기입니다"));
		}

		if (userWeaponForm.get() == null) {
			return ok(JsonUtil.getJsonResult(BAD_REQUEST, "강화수치를 파악할 수 없습니다."));
		}

		
		userWeapon.applyUpgrade(userWeaponForm.get());
		userWeapon.save();
		
		User user = User.find.byId(userId);
		
		return ok(JsonUtil.getJsonResult(0, user));
	}

	// PUT		/api2/user/:id/weapons/:weaponUid/equip
	public static Result equipWeapon(Long id, Long weaponUid) {
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
		weaponList.put("user_weapons", User.find.byId(id).userWeapons);

		return ok(JsonUtil.getJsonResult(0, weaponList));
	}

	
	
	
	public static Result getWeaponList() {
		return ok(JsonUtil.getJsonResult(0, Weapon.list()));
	}
	
    
}
