package collector;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Access(AccessType.FIELD)
public class Domain {
	@Id
	private String id;

	private String name;

	private String place;

	public Domain() {

	}

	public Domain(String name, String place) {
		this.name = name;
		this.place = place;
		this.id = System.currentTimeMillis() + "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Map<String, Object> toRep() {
		Map<String, Object> rep = new HashMap<>();
		rep.put("id", id);
		rep.put("name", name);
		rep.put("place", place);
		return rep;
	}

}
