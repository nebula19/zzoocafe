package models.ebeans;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
import play.db.ebean.Transactional;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;

@Entity
@Table(name="t_weapon") 
public class Weapon extends Model  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1871290198344390261L;


	// UID
	@Id
	public Long id;
	
	public String name;
	public String type;
	public Integer grade;		// 희귀등급. rarity
	public String image;
	public String sound;
	public Integer price;
	
	public Float power;
	public Float shootingRate;
	public Float criticalRate;
	public Float velocity;

	
	@Column(columnDefinition = "timestamp not null default '0000-00-00 00:00:00'", insertable = false, updatable = false)
	public Date createDate;
	
	@Column(columnDefinition = "timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
	public Date modifyDate;


	public static Weapon get(Long id) {
		return find.byId(id);
	}
	
	public static List<Weapon> list() {
//		return find.where().findPagingList(10).getPage(0).getList();
		return find.findList();
	}
	
	public static Finder<Long, Weapon> find = new Finder<Long, Weapon>(Long.class, Weapon.class);


	public static void deleteAll() {
		Ebean.createSqlUpdate("truncate table t_weapon").execute();
		Ebean.getServerCacheManager().clearAll();
	}

	@Transactional
	public static void replaceAll(List<Weapon> newWeaponList) {
//		deleteAll();
		for (Weapon weapon : newWeaponList) {
			weapon.update();
		}
		Ebean.getServerCacheManager().clearAll();
	}


}
