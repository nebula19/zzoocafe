package models.ebeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table(name="t_user") 
public class User extends Model  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4628099529895979489L;


	// UniqueID
	@Id
	@Column(insertable = false)
	public Long id;
	
	
	// Profile
	@Column(columnDefinition = "varchar(100) not null default ''", unique=true)
	public String username;
	@Column(columnDefinition = "bigint(20) default 0", unique=true, nullable=true)
	public Long socialAccountId;
	
	@Column(columnDefinition = "varchar(250) default ''")
	public String thumbnailUrl;
	
	// Game info
	@Column(columnDefinition = "int(5) default 0", insertable = false)
	public Integer gold;
	@Column(columnDefinition = "int(5) default 0", insertable = false)
	public Integer oil;
	@Column(columnDefinition = "int(5) default 0", insertable = false)
	public Integer diamond;

	
	@Column(columnDefinition = "int(11) default 0", insertable = false)
	public Integer score;
	
	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP", insertable = false, updatable = false)
	public Date createDate;
	
//	@Column(columnDefinition = "timestamp DEFAULT now() ON UPDATE now()", insertable = false, updatable = false)
//	public Date modifyDate;
	
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	public List<UserWeapon> userWeapons = new ArrayList<UserWeapon>();
	
	

	public static User getAccountInfo(Long id) {
		return find.byId(id);
	}
	
	

	public static User getPlayData(Long id) {
		return find.select("score, gold, oil, diamond").where("id = " + id).findUnique();
	}

	
	
	
	public static List<User> list() {
//		return find.where().findPagingList(10).getPage(0).getList();
		return find.select("username, create_time").findList();
	}

	
	public static List<User> listOrderByScore() {
//		return find.where().findPagingList(10).getPage(0).getList();
		return find.select("id, user_id, username, score").where().orderBy("score desc").findPagingList(10).getPage(0).getList();
	}
	
	
    public static void purchaseWeaon(Long userId, Long weaponId) {
        User user = User.find.setId(userId).fetch("userWeapons", "weaponId").findUnique();
        
        Weapon weapon = Weapon.find.ref(weaponId);
        UserWeapon userWeapon = new UserWeapon(user, weaponId);
        userWeapon.save();
        
        user.userWeapons.add( userWeapon );
        
        user.saveManyToManyAssociations("userWeapons");
    }
	
    
    
    
    
	public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);


}
