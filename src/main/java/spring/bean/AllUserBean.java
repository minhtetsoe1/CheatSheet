package spring.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllUserBean {

	private int id;
	private String name;
	private String email;
	private int status;
	private Date updatedAt;
}
