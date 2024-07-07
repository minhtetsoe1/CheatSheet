package spring.controller;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpSession;

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

import spring.bean.SectionBean;
import spring.entity.Section;
import spring.service.SectionServiceImpl;

@Controller
@RequestMapping("/section")
public class SectionController {

	public SectionController(SectionServiceImpl secser) {
		super();
		this.secser = secser;
	}
	private final SectionServiceImpl secser;

	@Autowired
	ModelMapper mapper;
	
	@ModelAttribute("sectionBean")
	public SectionBean getSectionBean() {
		SectionBean bean = new SectionBean();
		return bean;
	}
	
	@GetMapping(value="/show-section-form")
	public String showSection() {
		return "addSection";
	}
	
	@PostMapping(value="/add-section")
	public String addSection(@ModelAttribute("sectionBean")SectionBean bean,RedirectAttributes ra) {
		Section dto = mapper.map(bean, Section.class);
		int result = secser.addSection(dto,bean.getCheatSheetId());
		ra.addFlashAttribute("session",true);
		ra.addFlashAttribute("setsec",false);
		ra.addFlashAttribute("sectionBean",bean);
		if(result>0) {
			ra.addFlashAttribute("message","Adding section is successful.");
		}else {
			ra.addFlashAttribute("message","Adding section is fail!");
		}
		return "redirect:show-section-form";
	}
	@GetMapping(value="/select/{id}")
	public String getSectionById(@PathVariable("id")int id,Model m) {
		Section section = secser.getSingleSectionById(id);
		SectionBean sectionBean = mapper.map(section, SectionBean.class);
		m.addAttribute("sectionBean",sectionBean);
		m.addAttribute("url",true);
		return "updateSection";
	}
	
	@PostMapping(value="/update-section")
	public String updateSection(@ModelAttribute("sectionBean")SectionBean bean,RedirectAttributes ra) {
		Section dto = mapper.map(bean, Section.class);
		int result = secser.updateSection(dto);
		int id = bean.getCheatSheetId();
		if(result>0) {
			ra.addFlashAttribute("message","Adding section is successful.");
		}else {
			ra.addFlashAttribute("message","Adding section is fail!");
		}
		return "redirect:/cheat-sheet/show/" + id;
	} 
}
