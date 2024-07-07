package spring.service;

import java.util.List;

import spring.entity.Section;

public interface SectionService {

	public int addSection(Section dto,int id); 
	public List<Section> getSectionById(int id ) ;
	public Section getSingleSectionById(int id);
	public int updateSection(Section dto);
}
