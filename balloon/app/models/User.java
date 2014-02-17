package models;

import java.util.List;

import play.api.db.DB;

public class User {
	
	
	// UID
	public Long id;
	
	// Profile
	public String username;
	public String facebookId;
	public String thumbnailUrl;
	
	// Game info
	public Integer gold;
	public Integer oil;
	public Integer diamond;
	
	
	
	public static User get(Long id) {
		return null;
	}
	
	public static List<User> list() {
		return null;
	}
}
