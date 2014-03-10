package models.ebeans;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;

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
	public String rarity;		// 희귀등급. rarity
	public String image;
	public String sound;
	public Integer price;
	
	public Float damage;
	public Float shootingRate;
	public Float criticalRate;
	public Float velocity;

	
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false)
	public Date createDate;
	

	public static Weapon get(Long id) {
		return find.byId(id);
	}
	
	public static List<Weapon> list() {
//		return find.where().findPagingList(10).getPage(0).getList();
		return find.findList();
	}
	
	public static Finder<Long, Weapon> find = new Finder<Long, Weapon>(Long.class, Weapon.class);


}
