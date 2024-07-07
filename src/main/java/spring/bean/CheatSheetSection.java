package spring.bean;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CheatSheetSection {

	private int id;
	private String title;
	private String content;
	private int status;
	private Date createdAt;
	private Date updatedAt;// Renamed to follow naming conventions
	private List<CategoryBean> category;  // Corrected variable name
	private String image;
	private UserBean user;
	private List<SectionBean> section;
}
