package models.ebeans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table(name="t_user") 
public class User extends Model  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4628099529895979489L;


	// UID
	@Id
	@Column(insertable = false)
	public Long id;
	
	
	// Profile
	@Column(columnDefinition = "varchar(100) not null default ''", unique=true)
	public String username;
	@Column(columnDefinition = "bigint(20) not null default 0", unique=true, nullable=true)
	public Long userId;
	
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
	
	
	public static User get(Long id) {
		return find.byId(id);
	}
	
	public static List<User> list() {
//		return find.where().findPagingList(10).getPage(0).getList();
		return find.select("username, create_time").findList();
	}
	
	public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);


}
