package spring.bean;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class testBean {

	private MultipartFile file;
	private String imagePath;
}
