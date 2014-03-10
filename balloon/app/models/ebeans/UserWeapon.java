package models.ebeans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.ebean.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_user_weapon")
public class UserWeapon extends Model {

	public UserWeapon(User user, Long weaponId) {
		this.user = user;
		this.weaponId = weaponId;
	}
	
	@Id
	@GeneratedValue
	public Long id;
	
	@JsonIgnore
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	public User user;
	
//	@ManyToOne(targetEntity = Weapon.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "weapon_id")
	public Long weaponId;
	
	public Float damage = 0.0F;
	public Float shootingRate = 0.0F;
	public Float criticalRate = 0.0F;
	public Float velocity = 0.0F;
	
	public Integer position = 0;		// 장착위치. 0:미장착, 1:왼쪽, 2:오른쪽.

	private static final long serialVersionUID = -3281989327266459016L;
	public static Finder<Long, UserWeapon> find = new Finder<Long, UserWeapon>(Long.class, UserWeapon.class);


}
