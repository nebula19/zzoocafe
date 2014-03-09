package models.ebeans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class UserWeapon extends Model  {

	public UserWeapon(Long userId, Long weaponId) {
		this.userId = userId;
		this.weaponId = weaponId;
	}
	
	@Id
	public User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Long userId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Long weaponId;
	
	public Float damage;
	public Float shootingRate;
	public Float criticalRate;
	public Float velocity;
	
	public Integer position;		// 장착위치. 0:미장착, 1:왼쪽, 2:오른쪽.


	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3281989327266459016L;
	public static Finder<Long, UserWeapon> find = new Finder<Long, UserWeapon>(Long.class, UserWeapon.class);


}
