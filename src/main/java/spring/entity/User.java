package spring.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private Integer password;
	@Column(name = "create_at")
	private Date createdAt;
	@Column(name = "update_at")
	private Date updatedAt;
	@Column(name = "role")
	private String role = "user";
	@Column(name = "status", columnDefinition = "TINYINT")
	private int status = 1;

	@OneToMany(mappedBy = "user")
	private List<CheatSheet> cheatSheets;
	
	@PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
    }
}
