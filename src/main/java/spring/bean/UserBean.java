package spring.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {
	
	private String name;
	private String email;
	private Integer password;
	private Integer conPassword;
}
