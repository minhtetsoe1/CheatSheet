package spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.entity.Section;
import spring.repository.SectionRepository;

@Service
public class SectionServiceImpl implements SectionService{

	public SectionServiceImpl(SectionRepository repo) {
		super();
		this.repo = repo;
	}
	private final SectionRepository repo;
	@Override
	public int addSection(Section dto,int id) {
		return repo.addSection(dto,id);
	}
	@Override
	public List<Section> getSectionById(int id ) {
		return repo.getSectionsByCheatSheetId(id);
	}
	@Override
	public Section getSingleSectionById(int id) {
		return repo.getSectionById(id);
	}
	@Override
	public int updateSection(Section dto) {
		return repo.updateSection(dto);
	}
}
