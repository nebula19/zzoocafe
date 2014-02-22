package models.ebeans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class User extends Model  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4628099529895979489L;


	// UID
	@Id
	public Long id;
	
	
	// Profile
	@Column(columnDefinition = "varchar(100) not null default ''")
	public String username;
	@Column(columnDefinition = "bigint(20) not null default 0")
	public Long userId;
	@Column(columnDefinition = "varchar(250) default ''")
	public String thumbnailUrl;
	
	// Game info
	@Column(columnDefinition = "int(5) default 0")
	public Integer gold;
	@Column(columnDefinition = "int(5) default 0")
	public Integer oil;
	@Column(columnDefinition = "int(5) default 0")
	public Integer diamond;

	
	@Column(columnDefinition = "int(11) default 0")
	public Integer score;
	
	
	public static User get(Long id) {
		return find.byId(id);
	}
	
	public static List<User> list() {
		return find.where().between("id", 2, 4).findPagingList(1).getPage(2).getList();
	}
	
	public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

}
