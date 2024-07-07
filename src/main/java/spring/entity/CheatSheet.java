package spring.entity;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CheatSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "content")
	private String content;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "create_at",updatable = false)
	private Date createdAt;
	@Column(name = "update_at")
	private Date updatedAt;
	@Column(name = "image")
	private String image;
	@Column(name = "status", columnDefinition = "TINYINT")
	private int status = 1;
	@ManyToOne(cascade=CascadeType.MERGE)
	private User user;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "cheatsheet_catagory", joinColumns = { 
    @JoinColumn(name = "cheatsheet_id") }, 
	inverseJoinColumns = { 
    @JoinColumn(name = "category_id") })
	private List<Category> category;
	
	@PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
    }
}
