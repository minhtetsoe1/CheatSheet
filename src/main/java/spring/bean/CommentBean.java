package spring.bean;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentBean {

	private int id;
	private String content;
	private Date createdAt;
	private int status;
	private UserBean user;
	private CheatSheetBean cheatSheet;
}
