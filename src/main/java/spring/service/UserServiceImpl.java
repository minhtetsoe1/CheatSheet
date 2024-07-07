package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import spring.entity.CheatSheet;
import spring.entity.User;
import spring.entity.UserCheatDTO;
import spring.repository.UserRepositoryImpl;

@Component
public class UserServiceImpl implements UserService {

	private final UserRepositoryImpl userrepo;

	public UserServiceImpl(UserRepositoryImpl userrepo) {
		this.userrepo = userrepo;
	}

	@Override
	public int insertUser(User dto) {
		return userrepo.insertUser(dto);
	}

	@Override
	public User selectUser(User dto) {
		// TODO Auto-generated method stub
		return userrepo.selectUser(dto);
	}

	@Override
	public List<UserCheatDTO> getAllUser() {
		return userrepo.getAllUser();
	}
	@Override
	public List<User> getAllUserList(){
		return userrepo.getAllUserList();
	}
	@Override
	public int activeUser(int id) {
		return userrepo.activeUser(id);
	}
	@Override
	public int deleteUser(int id) {
		return userrepo.deleteUser(id);
	}
	@Override
	public User selectUser(int id) {
		// TODO Auto-generated method stub
		return userrepo.selectUser(id);
	}
}
