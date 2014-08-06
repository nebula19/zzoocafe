package controllers;

import static play.data.Form.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.ZzooResult;
import models.ebeans.User;
import models.ebeans.UserWeapon;
import models.ebeans.Weapon;

import org.springframework.util.StringUtils;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.ConvUtil;
import util.JsonUtil;

public class GameAction extends Controller {


	/**
	 * 게임 결과를 현재 정보에 더하여 업데이트.
	 * 
	 * @param id
	 * @return
	 */
	public static Result updatePlayResult(Long id) {
		Form<User> userForm = form(User.class).bindFromRequest("score", "gold");

		System.out.println("UpdatePlayResult." + "User id:" + id);

		if (userForm.hasErrors()) {
			badRequest(JsonUtil.getJsonResult(ZzooResult.BAD_REQUEST));
		}

		User dbUser = User.find.byId(id);
		User user = userForm.get();

		if (dbUser.score < user.score) {
			dbUser.score = user.score;
		}

		dbUser.gold += user.gold;

		dbUser.update();
		return ok(JsonUtil.getJsonResult(0, dbUser));
	}

	// PUT /api2/user/:id/weapons/:weaponUid/upgrade
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

	
	/**
	 * 전체 사용자 랭킹.
	 * 10명단위 paging
	 * @param pageNum
	 * @return
	 */
	public static Result getRanking(Integer pageNum) {
		List<User> userList = User.listOrderByScore(pageNum);
		
		if (pageNum<0) {
			return badRequest(JsonUtil.getJsonResult(ZzooResult.CLIENT_ERROR));
		}
		
		if (userList != null) {
			return ok (JsonUtil.getJsonResult(ZzooResult.OK, userList));
		}
		else {
			return internalServerError(JsonUtil.getJsonResult(ZzooResult.SERVER_ERROR));
		}
	}
	
	
	
	
	/*
	 * =======================================
	 * 아래는 2014.08.07 이전까지 쓰던 것들.
	 * =======================================
	 */
	
	
	
	
	public static Result getPlayData(Long id) {
		return ok(JsonUtil.getJsonResult(0, User.getPlayData(id)));
	}

	
	/**
	 * 단말에서 올린 정보로 게임 정보를 덮어쓴다.
	 * 
	 * @param id
	 * @return
	 */
	public static Result replacePlayData(Long id) {
		Form<User> userForm = form(User.class).bindFromRequest("score", "gold");

		if (userForm.hasErrors()) {
			badRequest(JsonUtil.getJsonResult(ZzooResult.BAD_REQUEST));
		}
		userForm.data().put("user_id", String.valueOf(id));

		User user = User.find.byId(id);
		try {
			String param = null;

			param = userForm.data().get("score");
			if (!StringUtils.isEmpty(param)) {
				user.score = ConvUtil.asInt(param);
			}

			param = userForm.data().get("gold");
			if (!StringUtils.isEmpty(param)) {
				user.gold = ConvUtil.asInt(param);
			}

			user.update(id);
			return ok(JsonUtil.getJsonResult(ZzooResult.OK));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return badRequest(JsonUtil.getJsonResult(ZzooResult.BAD_REQUEST));
	}
	public static Result userWeaponList(Long id) {
		// List<UserWeapon> userWeaponList = User.find.byId(id).userWeapons;
		return ok(JsonUtil.getJsonResult(0, getUserWeaopnList(id)));
	}

	private static List<UserWeapon> getUserWeaopnList(Long userId) {
		return UserWeapon.find.where("user_id=" + userId)
				.orderBy("position desc").findList();
	}


	// PUT /api2/user/:id/weapons/:weaponUid/equip
	public static Result equipWeapon(Long userId, Long userWeaponUid,
			Integer position) {
		User user = User.equipWeapon(userId, userWeaponUid, position);

		Map<String, List<UserWeapon>> resultMap = new HashMap<String, List<UserWeapon>>();
		resultMap.put("user_weapons", getUserWeaopnList(userId));

		return ok(JsonUtil.getJsonResult(0, resultMap));
	}

	public static Result purchaseWeapon(Long userId, Long weaponId) {
		User user = User.find.setId(userId).fetch("userWeapons", "weaponId")
				.findUnique();

		if (user == null) {
			return ok(JsonUtil.getJsonResult(400, "사용자가 존재하지 않습니다.", null));
		}

		if (user.gold <= 10000) {
			return ok(JsonUtil.getJsonResult(0, "골드가 부족합니다.", null));
		} else {
			user.gold -= 10000;
			user.update();
		}

		User.purchaseWeaon(user, weaponId);

		User dbUser = User.find.byId(userId);

		return ok(JsonUtil.getJsonResult(0, dbUser));
	}

	public static Result gambleWeapon(Long id) {

		// choose Random weapon
		long r = (long) (Math.random() * 21) + 1;
		System.out.println("new weapon id : " + r);

		return purchaseWeapon(id, r);
	}

	public static Result selllWeapon(Long userId, Long userWeaponId) {
		User user = User.find.byId(userId);
		if (user == null) {
			return ok(JsonUtil.getJsonResult(400, "사용자가 존재하지 않습니다.", null));
		}

		// UserWeapon.find.where("user_id = " + userId + " and weapon_id = " +
		// weaponId).findUnique();

		UserWeapon userWeapon = UserWeapon.find.byId(userWeaponId);
		if (userWeapon == null) {
			return ok(JsonUtil.getJsonResult(400, "소유하지 않은 무기입니다.", null));
		}

		Weapon weapon = Weapon.find.byId(userWeapon.weaponId);
		if (weapon == null) {
			return ok(JsonUtil.getJsonResult(400, "존재하지 않는 무기입니다.", null));
		}

		if (userWeapon.position == 1 || userWeapon.position == 2) {
			return ok(JsonUtil.getJsonResult(400, "장착중인 무기는 판매할 수 없습니다.", null));
		}

		user.gold = user.gold + (int) (weapon.price * 0.5);
		user.update();

		userWeapon.delete();

		return ok(JsonUtil.getJsonResult(0, user));
	}

	/**
	 * 무기 전체 목록.
	 * 
	 * @return
	 */
	public static Result getWeaponList() {
		return ok(JsonUtil.getJsonResult(0, Weapon.list()));
	}

}
