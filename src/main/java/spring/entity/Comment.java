package spring.entity;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="content")
	private String content;
	@Column(name="create_at")
	private Date createAt;
	@Column(name="status")
	private int status;
	@JoinColumn(name = "user_id")
	@ManyToOne(cascade =CascadeType.ALL)
	private User user;
	@JoinColumn(name = "cheat_sheet_id")
	@ManyToOne(cascade =CascadeType.ALL)
	private CheatSheet cheatSheeet;
	
	@PrePersist
	protected void onCreate() {
		if(this.status==0) {
			this.status =1;
		}
	}
}
