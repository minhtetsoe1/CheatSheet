package spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

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

import spring.bean.CategoryBean;
import spring.entity.Category;
import spring.repository.FileRepository;
import spring.service.CategoryServiceImpl;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	ModelMapper mapper;

	public CategoryController(CategoryServiceImpl catsev, FileRepository filerepo) {
		super();
		this.catsev = catsev;
		this.filerepo = filerepo;
	}
	private final FileRepository filerepo;
	private final CategoryServiceImpl catsev;

	@ModelAttribute("categoryBean") 
	public CategoryBean showAllCatagories(){
	  CategoryBean bean = new CategoryBean();
	  return bean;
	}

	@GetMapping(value = "/show-category-form")
	public String showCategoryForm() {
		return "addCategory";
	}

	@PostMapping(value = "add-category")
	public String addCategory(@ModelAttribute("category") CategoryBean bean) {
		
		Category dto = mapper.map(bean, Category.class);
		catsev.createCategory(dto);
		return "redirect:/admin-view";
	}
	
	@ModelAttribute("categorylst")
	public List<CategoryBean> showAllCategoryBean() {
		List <Category> categoryLstDTO = catsev.selectAllCategory();
		List<CategoryBean> categorylst = categoryLstDTO.stream()
				.map(dto -> mapper.map(dto, CategoryBean.class))
				.collect(Collectors.toList());
		return categorylst;
	}
	
	@GetMapping(value="/show-all-category")
	public String showAllCategory() {
		return "showAllCategory";
	}
	
	@GetMapping(value="/active/{id}")
	public String activeCategory(@PathVariable("id")int id , RedirectAttributes ra) {
		int result = catsev.activeCategory(id);
		if(result>0) {
			ra.addFlashAttribute("message","Active category is successful");
		}else {
			ra.addFlashAttribute("message","Active category is fail!");
		}
		ra.addFlashAttribute("plan", true);
		return "redirect:/category/show-all-category";
	}
	
	@GetMapping(value="/delete/{id}")
	public String deleteCategory(@PathVariable("id")int id , RedirectAttributes ra) {
		int result = catsev.deleteCategory(id);
		if(result>0) {
			ra.addFlashAttribute("message","Delete category is successful");
		}else {
			ra.addFlashAttribute("message","Dalete category is fail!");
		}
		ra.addFlashAttribute("plan", true);
		return "redirect:/category/show-all-category";
	}
	
	@GetMapping(value="/select/{id}")
	public String selectCategory(@PathVariable("id")int id,Model m) {
		Category  dto = catsev.selectCategory(id);
		CategoryBean bean = mapper.map(dto, CategoryBean.class);
		m.addAttribute("catBean",bean);
		return "updateCategory";
	}
	
	@PostMapping(value="/update")
	public String updateCategory(@ModelAttribute("catBean")CategoryBean bean,RedirectAttributes ra) {
		Category dto = mapper.map(bean, Category.class);
		int result = catsev.updateCategory(dto);
		if(result>0) {
			ra.addFlashAttribute("message","Update category is successful");
		}else {
			ra.addFlashAttribute("message","Update category is fail!");
		}
		ra.addFlashAttribute("plan", true);
		return "redirect:/category/show-all-category";
	}
	
	
}
