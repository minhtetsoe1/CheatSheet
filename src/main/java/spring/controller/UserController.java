package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.bean.UserBean;
import spring.dto.UserDTO;
import spring.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserRepository userrepo; 
	
	@Autowired
	public UserController(UserRepository userrepo) {
		super();
		this.userrepo = userrepo;
	}

	
	
	@GetMapping(value="/register")
	public ModelAndView showRegister() {
		return new ModelAndView("registerForm", "user", new UserBean());
	}
	
	@PostMapping(value="/adduser")
		public String addUser(@ModelAttribute("user")UserBean bean,RedirectAttributes ra) {
		UserDTO dto = new UserDTO();
		dto.setName(bean.getName());
		dto.setEmail(bean.getEmail());
		dto.setPassword(bean.getPassword());
		int i = userrepo.insertUser(dto);
		if(i>0) {
			ra.addFlashAttribute("insertSuccess", "Register Successful.");
			return "redirect:/";
		}else {
			ra.addFlashAttribute("insertError" , "Register Fail!");
			return "redirect:register";
		}
		
	}
	
	@GetMapping(value="/showusers")
	public String showAllUser(Model m ) {
		List<UserDTO> userList  = userrepo.showAllUser();
		m.addAttribute("userlst", userList);
		return "userList";
	}
}
