package spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.bean.UserBean;
import spring.bean.AllCheatSheetBean;
import spring.bean.AllUserBean;
import spring.bean.CategoryBean;
import spring.bean.CheatSheetBean;
import spring.bean.SingleProfileBean;
import spring.entity.Category;
import spring.entity.CheatSheet;
import spring.entity.User;
import spring.entity.UserCheatDTO;
import spring.repository.FileRepository;
import spring.service.CategoryServiceImpl;
import spring.service.CheatSheetServiceImpl;
import spring.service.UserServiceImpl;

@Controller
public class UserController {

	@Autowired
	ModelMapper mapper;

	public final CheatSheetServiceImpl chsv;
	public final CategoryServiceImpl catSer;
	public final UserServiceImpl urs;

	public UserController(UserServiceImpl urs, CheatSheetServiceImpl chsv,
			CategoryServiceImpl catSer) {
		this.urs = urs;
		this.chsv = chsv;
this.catSer = catSer;
	}

	@ModelAttribute("registerBean")
	public UserBean getRegisterBean() {
		UserBean bean = new UserBean();
		return bean;
	}

	@ModelAttribute("allCheatSheet")
	public List<AllCheatSheetBean> getAllCheatSheet() {
		List<CheatSheet> cheatSheets = chsv.getAllCheatSheet();
		List<AllCheatSheetBean> allCheatSheetBean = cheatSheets.stream()
				.map(dto -> mapper.map(dto, AllCheatSheetBean.class)).collect(Collectors.toList());
		return allCheatSheetBean;
	}

	@GetMapping(value = "/")
	public String showLogin() {
		return "index";
	}

	@PostMapping(value = "/register")
	public String addUser(@ModelAttribute("registerBean") UserBean bean, RedirectAttributes ra) {
		User dto = mapper.map(bean, User.class);
		int result = urs.insertUser(dto);
		if (result > 0) {
			ra.addFlashAttribute("insertSuccess", "Register Successful.");
			ra.addFlashAttribute("loginError", true);
			return "redirect:/";
		} else {
			ra.addFlashAttribute("insertError", "Register Fail!");
			ra.addFlashAttribute("registerError", true);
			ra.addFlashAttribute("registerBean", bean);
			ra.addFlashAttribute("emailError", "Email must not be duplicate.");
			return "redirect:/";
		}
	}
	
	@ModelAttribute("categorylst")
	public List<CategoryBean> showAllCategoryBean() {
		List <Category> categoryLstDTO = catSer.selectAllCategory();
		List<CategoryBean> categorylst = categoryLstDTO.stream()
				.map(dto -> mapper.map(dto, CategoryBean.class))
				.collect(Collectors.toList());
		return categorylst;
	}

	@PostMapping(value = "/login")
	public String getLogin(@ModelAttribute("registerBean") UserBean bean, RedirectAttributes ra, HttpSession session) {
		User dto = mapper.map(bean, User.class);
		User result = urs.selectUser(dto);
		if (result != null) {
			session.setAttribute("sessionUId", result.getId());
			session.setAttribute("result", true);
			if (result.getRole().equals("user")) {
				return "redirect:/";
			} else {
				return "redirect:admin-view";
			}

		} else {
			ra.addFlashAttribute("loginResult", "Login is fail!");
			ra.addFlashAttribute("loginError", true);
			ra.addFlashAttribute("loginBean", bean);
			return "redirect:/";
		}
	}

	@GetMapping(value="/admin-view")
	public String showAdmin() {
		return "adminView";
	}
	
	@GetMapping(value = "/show-all-user")
	public String showAllUser() {

		return "showAllUser";
	}

	@ModelAttribute("allUser")
	public List<AllUserBean> getAllUser() {
		List<User> user = urs.getAllUserList();
		List<AllUserBean> bean = user.stream().map(dto -> mapper.map(dto, AllUserBean.class))
				.collect(Collectors.toList());
		return bean;
	}

	@GetMapping(value="/delete/{id}")
	public String deleteUser(@PathVariable("id")int id ,RedirectAttributes ra) {
		int result = urs.deleteUser(id);
		if(result>0) {
			ra.addFlashAttribute("message","Block user is successful");
		}else {
			ra.addFlashAttribute("message","Block user is fail!");
		}
		ra.addFlashAttribute("plan", true);
		return "redirect:/show-all-user";
	}
	
	@GetMapping(value="/active/{id}")
	public String activeUser(@PathVariable("id")int id ,RedirectAttributes ra) {
		int result = urs.activeUser(id);
		if(result>0) {
			ra.addFlashAttribute("message","Active user is successful");
		}else {
			ra.addFlashAttribute("message","Active user is fail!");
		}
		ra.addFlashAttribute("plan", true);
		return "redirect:/show-all-user";
	}
	
	@GetMapping(value="/profile")
	public String showProfile() {	
			return "profile";	
	}
	
	@ModelAttribute("profileBean")
	public SingleProfileBean getProfile(HttpSession session) {
		SingleProfileBean bean = null;
		try {
			int id = (int)session.getAttribute("sessionUId");
			User user = urs.selectUser(id);
			 bean = mapper.map(user, SingleProfileBean.class);
		} catch (Exception e) {
			System.out.println("getting id : " + e.getMessage());
		}
		return bean;
	}
	
	@ModelAttribute("LatestCheatSheet")
	public List<AllCheatSheetBean> getCheatSheet(){
		List<CheatSheet> cheatSheet = chsv.getCheatSheetUser();
		List<AllCheatSheetBean> CheatSheetBean = cheatSheet.stream()
				.map(dto -> mapper.map(dto, AllCheatSheetBean.class))
				.collect(Collectors.toList());
		return CheatSheetBean;
	}
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@ModelAttribute("cheatLstById")
	public List<CheatSheet> getCheatLstById(HttpSession session){
		List<CheatSheet> lst =null;
		try {
			int sessionId =(int) session.getAttribute("sessionUId");
			 if (sessionId != 0) {
		            lst = chsv.getCheatListById(sessionId);
		        }
		} catch (Exception e) {
			System.out.println("login  : " + e.getMessage());
		}
		return lst;
	}
	@GetMapping(value="/home")
	public String killCheatSheet(HttpSession session) {
		session.removeAttribute("cheatSheetId");
		return "redirect:/";
	}
}
