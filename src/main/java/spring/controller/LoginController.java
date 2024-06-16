package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import spring.bean.LoginBean;
import spring.bean.UserBean;
import spring.dto.LoginDTO;
import spring.dto.UserDTO;
import spring.repository.UserRepository;

@Controller
public class LoginController {

	private final UserRepository userrepo;
	@Autowired
	public LoginController(UserRepository userrepo) {
		super();
		this.userrepo = userrepo;
	}

	@ModelAttribute("loginBean")
	public LoginBean getLoginBean(){
		LoginBean bean = new LoginBean();
		return bean;
	}

	@ModelAttribute("registerBean")
	public UserBean getRegisterBean(){
		UserBean bean = new UserBean();
		return  bean;
	}

	@GetMapping(value="/")
	public String showLogin() {
		return "index";
	}
}
