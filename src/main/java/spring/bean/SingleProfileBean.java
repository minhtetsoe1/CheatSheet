package spring.bean;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleProfileBean {

	private String name;
	private String email;
	private Date createdAt;
	private Date updatedAt;
}
