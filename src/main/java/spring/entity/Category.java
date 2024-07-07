package spring.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="type")
	private String type;
	
	@Column(name = "status", columnDefinition = "TINYINT")
	private int status ;
	@ManyToMany(mappedBy="category")
	private List<CheatSheet> cheatSheet;
	@PrePersist
    protected void onCreate() {
        if (this.status == 0) {
            this.status = 1;
        }
    }
}
