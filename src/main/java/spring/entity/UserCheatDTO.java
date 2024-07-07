package spring.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserCheatDTO {

	private User user;
	private long cheatsheetCount;

	public UserCheatDTO(User user, long cheatsheetCount) {
		this.user = user;
		this.cheatsheetCount = cheatsheetCount;
	}
}
