package spring.service;

import java.util.List;

import spring.entity.User;
import spring.entity.UserCheatDTO;

public interface UserService {

	public int insertUser(User dto);
	public User selectUser(User dto);
	public List<UserCheatDTO> getAllUser();
	public List<User> getAllUserList();
	public int activeUser(int id);
	public int deleteUser(int id);
	public User selectUser(int id);
}
