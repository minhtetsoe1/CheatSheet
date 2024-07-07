package spring.entity;

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
public class Section {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="content")
	private String content;
	@Column(name = "status", columnDefinition = "TINYINT")
	private int status ;
	@JoinColumn(name = "cheat_sheet_id")
	@ManyToOne(cascade =CascadeType.MERGE)
	private CheatSheet cheatSheet;
	
	@PrePersist
    protected void onCreate() {
        if (this.status == 0) {
            this.status = 1;
        }
    }
}
