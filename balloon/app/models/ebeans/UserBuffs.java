package models.ebeans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class UserBuffs extends Model{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1231192383169886402L;


	// UID
	@Id
	public Long userUid;
	

	// BUFFs
	@Column(columnDefinition = "float not null default 0.0")
	private float hero_power;
	@Column(columnDefinition = "float default 0.0")
	private float hero_shoot_speed;
	@Column(columnDefinition = "float default 0.0")
	private float hero_critical_ratio ;
	@Column(columnDefinition = "float default 0.0")
	private float hero_critical_damage ;

	@Column(columnDefinition = "float default 0.0")
	private float mob_red_create_ratio;
	@Column(columnDefinition = "float default 0.0")
	private float mob_red_hp;
	@Column(columnDefinition = "float default 0.0")
	private float mob_red_item_ratio;
	@Column(columnDefinition = "float default 0.0")
	private float mob_red_point;

	@Column(columnDefinition = "float default 0.0")
	private float mob_green_create_ratio;
	@Column(columnDefinition = "float default 0.0")
	private float mob_green_hp;
	@Column(columnDefinition = "float default 0.0")
	private float mob_green_item_ratio;
	@Column(columnDefinition = "float default 0.0")
	private float mob_green_point;

	@Column(columnDefinition = "float default 0.0")
	private float mob_blue_create_ratio;
	@Column(columnDefinition = "float default 0.0")
	private float mob_blue_hp;
	@Column(columnDefinition = "float default 0.0")
	private float mob_blue_item_ratio;
	@Column(columnDefinition = "float default 0.0")
	private float mob_blue_point;

	@Column(columnDefinition = "float default 0.0")
	private float mob_yellow_create_ratio;
	@Column(columnDefinition = "float default 0.0")
	private float mob_yellow_hp;
	@Column(columnDefinition = "float default 0.0")
	private float mob_yellow_item_ratio;
	@Column(columnDefinition = "float default 0.0")
	private float mob_yellow_point;

	@Column(columnDefinition = "float default 0.0")
	private float mob_black_create_ratio;
	@Column(columnDefinition = "float default 0.0")
	private float mob_black_hp ;
	@Column(columnDefinition = "float default 0.0")
	private float mob_black_item_ratio;
	@Column(columnDefinition = "float default 0.0")
	private float mob_black_point;
	
	@Column(columnDefinition = "float default 0.0")
	private float pz_reload_speed ;

	@Column(columnDefinition = "float default 0.0")
	private float item_shoot_velocity;
	@Column(columnDefinition = "float default 0.0")
	private float item_drop_acceleration;

	@Column(columnDefinition = "float default 0.0")
	private float game_play_time;
	
	
	
	public static UserBuffs get(Long id) {
		return new UserBuffs();
	}
	
	public static List<UserBuffs> list() {
		return null;
	}
	
	public static Finder<Long, UserBuffs> find = new Finder<Long, UserBuffs>(Long.class, UserBuffs.class);
}
