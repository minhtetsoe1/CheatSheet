package spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.eclipse.persistence.internal.oxm.schema.model.All;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.bean.AllCheatSheetBean;
import spring.bean.CategoryBean;
import spring.bean.CheatSheetBean;
import spring.bean.CheatSheetSection;
import spring.bean.CommentBean;
import spring.bean.SectionBean;
import spring.bean.UserBean;
import spring.entity.Category;
import spring.entity.CheatSheet;
import spring.entity.Comment;
import spring.entity.Section;
import spring.repository.FileRepository;
import spring.repository.SectionRepository;
import spring.service.CategoryServiceImpl;
import spring.service.CheatSheetServiceImpl;
import spring.service.SectionServiceImpl;

@Controller
@RequestMapping("/cheat-sheet")
public class CheatSheetController {

	private final FileRepository filerepo;
	private final CheatSheetServiceImpl chsv;
	private final CategoryServiceImpl catSer;
	private final SectionServiceImpl sec;
	public CheatSheetController(CheatSheetServiceImpl chsv,CategoryServiceImpl catSer,FileRepository filerepo,SectionServiceImpl sec) {
		this.chsv = chsv;
		this.catSer = catSer;
		this.filerepo = filerepo;
		this.sec = sec;
	}

	@Autowired
	ModelMapper mapper;
	
	@ModelAttribute("cheatSheetBean")
	public CheatSheetBean getCheatSheetBean() {
		CheatSheetBean bean = new CheatSheetBean();
		return bean;
	}
	@ModelAttribute("registerBean")
	public UserBean getRegisterBean() {
		UserBean bean = new UserBean();
		return bean;
	}

	@GetMapping(value = "/show-cheat-sheet")
	public String showCheatSheet(Model m) {
		m.addAttribute("url",true);
		return "cheatsheet";
	}

	@GetMapping(value = "/cheatsheet-create")
	public String showCheatSheetForm(HttpSession session,RedirectAttributes ra) {
		try {
			int sessionId =(int) session.getAttribute("sessionUId");
			if(sessionId>0 ) {
				return "addCheatSheet";
				
			}
		} catch (Exception e) {
			System.out.println("login  : " + e.getMessage());
		}
		ra.addFlashAttribute("loginError", true);
		return "redirect:/";
		
	}

	@PostMapping(value = "/add-cheatsheet")
	public String addCheatSheet(@ModelAttribute("cheatSheetBean") CheatSheetBean bean,HttpSession session,RedirectAttributes ra) {
		String imagePath = filerepo.fileUpload1(bean.getFile());
		bean.setImage(imagePath);
		
		CheatSheet dto = mapper.map(bean, CheatSheet.class);
		
		List<Category> categories = catSer.findCategoriesById(bean.getCategory());
		dto.setCategory(categories);
		int userId = (int) session.getAttribute("sessionUId");
		int result  = chsv.addCheatSheet(dto,userId);
		session.setAttribute("cheatSheetId", result);
		if(result>0) {
			return "redirect:/section/show-section-form";
		}else {
			return "redirect:cheatsheet-create";
		}
		
	}
	@ModelAttribute("sectionBean")
	public SectionBean showSectionBean() {
		SectionBean bean = new SectionBean();
		return bean;
	}
	
	@ModelAttribute("categorylst")
	public List<CategoryBean> showAllCategory() {
		List <Category> categoryLstDTO = catSer.selectAllCategory();
		List<CategoryBean> categorylst = categoryLstDTO.stream()
				.map(dto -> mapper.map(dto, CategoryBean.class))
				.collect(Collectors.toList());
		return categorylst;
	}
	
	@ModelAttribute("allCheatSheet")
	public List<AllCheatSheetBean> getAllCheatSheet(){
		List<CheatSheet> cheatSheets = chsv.getAllCheatSheet();
		List<AllCheatSheetBean> allCheatSheetBean = cheatSheets.stream()
				.map(dto -> mapper.map(dto, AllCheatSheetBean.class))
				.collect(Collectors.toList());
		return allCheatSheetBean;
	}
	

	
	@GetMapping(value="/show-cheat-list")
	public String showAllCheatSheetList() {
		return "showAllCheatSheet";
	}
	
	@GetMapping(value="/delete/{id}")
	public String deleteCheetSheet(@PathVariable("id")int id, RedirectAttributes ra) {
		int result = chsv.deleteCheatSheet(id);
		if(result>0) {
			ra.addFlashAttribute("message","Delete CheatSheet is successful");
		}else {
			ra.addFlashAttribute("message","Dalete CheatSheet is fail!");
		}
		ra.addFlashAttribute("plan", true);
		return "redirect:/cheat-sheet/show-cheat-list";
	}
	@GetMapping(value="/active/{id}")
	public String activeCheetSheet(@PathVariable("id")int id, RedirectAttributes ra) {
		int result = chsv.activeCheatSheet(id);
		if(result>0) {
			ra.addFlashAttribute("message","Active CheatSheet is successful");
		}else {
			ra.addFlashAttribute("message","Active CheatSheet is fail!");
		}
		ra.addFlashAttribute("plan", true);
		return "redirect:/cheat-sheet/show-cheat-list";
	}
	
	@GetMapping(value="/show/{id}")
	public String showSection(@PathVariable("id")int id,Model m,HttpSession session) {
		CheatSheet cheat = chsv.getCheatSheetById(id);
		AllCheatSheetBean cheatbean = mapper.map(cheat, AllCheatSheetBean.class);
		List<Section> slst =  sec.getSectionById(id);
		List<SectionBean> sectionlst = slst.stream()
				.map(dto -> mapper.map(dto, SectionBean.class))
				.collect(Collectors.toList());
		try {
			int loginId =(int) session.getAttribute("sessionUId");
			CheatSheet cheatsheet = chsv.getCheatSheetById(id);
			int userId = cheatsheet.getUser().getId();
			if(loginId == userId) {
				m.addAttribute("sameUser",true);
			}
		} catch (Exception e) {
			System.out.println("login  : " + e.getMessage());
		}
		m.addAttribute("cheatbean",cheatbean);
		m.addAttribute("sectionlst",sectionlst);
		return "singleCheatSheet";
	}
	
	@GetMapping(value="/select/{id}")
	public String getCheatSheetById(@PathVariable("id")int id , RedirectAttributes ra) {
		CheatSheet cheeat = chsv.getCheatSheetById(id);
		CheatSheetBean cheatSheetBean = mapper.map(cheeat, CheatSheetBean.class);
		ra.addFlashAttribute("cheatSheetBean",cheatSheetBean);
		return "redirect:cheatsheet-create";
	}
	
	@GetMapping(value="/selectById/{id}")
	public String selectCheatByCategory(@PathVariable("id")int id,Model m) {
		List<CheatSheet> list = chsv.getCheatByCategory(id);
		List<AllCheatSheetBean> allCheatSheet = list.stream()
				.map(dto -> mapper.map(dto, AllCheatSheetBean.class))
				.collect(Collectors.toList());
		m.addAttribute("allCheatSheet",allCheatSheet);
		
		return "cheatsheet";
	}
	
	@GetMapping(value="/update/{id}")
	public String showUpdateCheatSheet(@PathVariable("id")int id ,Model m) {
		CheatSheet cheat = chsv.getCheatSheetById(id);
//		CheatSheetBean cheatbean = mapper.map(cheat, CheatSheetBean.class);
		m.addAttribute("cheatSheetBean",cheat);
		return "updateCheatSheet";
	}
	
	@PostMapping(value="/update-cheat-sheet")
	public String updateCheatSheet(@ModelAttribute("cheatSheetBean")CheatSheetBean bean,RedirectAttributes ra) {
		CheatSheet dto = mapper.map(bean, CheatSheet.class);
		int result = chsv.updateCheatSheetById(dto);
		if(result>0) {
			ra.addFlashAttribute("message", "Update cheat sheet is successful");
		}else {
			ra.addFlashAttribute("message", "Update cheat sheet is fail");
		}
		return "redirect:show/"+bean.getId();
	}
	
	@ModelAttribute("commentBean")
	public CommentBean getCommentBean() {
		CommentBean bean = new CommentBean();
		return bean;
	}
	
	@PostMapping(value="/add-comment")
	public String addComment(@ModelAttribute("commentBean")CommentBean bean) {
		Comment comment = mapper.map(bean, Comment.class);
		int result = chsv.addComment(comment);
		return "redirect:show/"+bean.getId();
	}
}
