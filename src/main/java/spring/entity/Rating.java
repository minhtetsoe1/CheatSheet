package spring.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="pay_rate")
	private int payRate;
	@JoinColumn(name = "user_id")
	@ManyToOne(cascade =CascadeType.ALL)
	private User user;
	@JoinColumn(name = "cheat_sheet_id")
	@ManyToOne(cascade =CascadeType.ALL)
	private CheatSheet cheatSheeet;
}
