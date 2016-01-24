package board.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Board {

	@Id
	@GeneratedValue
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Timestamp posteddate;
	private String name;
	private String message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getPosteddate() {
		return posteddate;
	}

	public void setPosteddate(Timestamp posteddate) {
		this.posteddate = posteddate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
