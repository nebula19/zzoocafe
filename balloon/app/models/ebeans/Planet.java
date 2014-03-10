package models.ebeans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
/*
@Entity
@Table(name="t_planet") 
public class Planet extends Model  {
	
	
	private static final long serialVersionUID = -4628099529895979489L;


	// UID
	@Id
	@Column(insertable = false)
	public Long id;
	
	
	// Profile
	@Column(columnDefinition = "varchar(100) not null", unique=true)
	public String name;
	
	
	
	
	public static Planet get(Long id) {
		return find.byId(id);
	}
	
	public static List<Planet> list() {
//		return find.where().findPagingList(10).getPage(0).getList();
		return find.select("name, create_time").findList();
	}
	
	public static Finder<Long, Planet> find = new Finder<Long, Planet>(Long.class, Planet.class);

}

*/
