package spring.bean;

import java.sql.Date;
import java.util.List;  // Correct import for List

import org.springframework.web.multipart.MultipartFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheatSheetBean {

	private int id;
	private String title;
	private String content;
	private Date createdAt;  // Renamed to follow naming conventions
	private List<Integer> category;  // Corrected variable name
	private MultipartFile file;
	private String image;
}