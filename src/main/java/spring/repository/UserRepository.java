package spring.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import spring.dto.LoginDTO;
import spring.dto.UserDTO;

@Repository
public class UserRepository {

	public int insertUser(UserDTO dto) {
		int result = 0;
		Connection con = ConnectionClass.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into user (name,email,password) values(?,?,?)");
			ps.setString(1,dto.getName());
			ps.setString(2,dto.getEmail());
			ps.setInt(3, dto.getPassword());
			result = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("user insert : " + e.getMessage());		
			}
		return result;
	}
	
	public List<UserDTO> showAllUser(){
		Connection con = ConnectionClass.getConnection();
		List <UserDTO>  userList = new ArrayList<UserDTO>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from user");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setPassword(rs.getInt("password"));
				userList.add(dto);
			}
		} catch (Exception e) {
			System.out.println("show user : "+ e.getMessage());
		}
		return userList;
	}
	
	public UserDTO checkLogin(LoginDTO dto) {
		Connection con = ConnectionClass.getConnection();
		UserDTO udto = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from user where email=? and password=?");
			ps.setString(1, dto.getEmail());
			ps.setInt(2, dto.getPassword());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				udto = new UserDTO();
				udto.setId(rs.getInt("id"));
				udto.setName(rs.getString("name"));
				udto.setEmail(rs.getString("email"));
				udto.setPassword(rs.getInt("password"));
			}
		} catch (Exception e) {
			System.out.println("Get Login : "+e.getMessage());
		}
		return udto;
	}
}
