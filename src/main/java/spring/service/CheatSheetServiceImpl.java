package spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import spring.entity.CheatSheet;
import spring.entity.Comment;
import spring.repository.CheatSheetRepositoryImpl;
@Service
public class CheatSheetServiceImpl implements CheatSheetService{

	public CheatSheetServiceImpl(CheatSheetRepositoryImpl csrepo) {
		this.csrepo = csrepo;
	}
	private final CheatSheetRepositoryImpl csrepo;

	
	@Override
	public int addCheatSheet(CheatSheet dto,int userId) {
		return csrepo.addCheatSheet(dto,userId);
		
	}
	
	
	@Override
	public List<CheatSheet> getAllCheatSheet(){
		return csrepo.getAllCheatSheet();
	}
	@Override
	public int activeCheatSheet(int id) {
		return csrepo.activeCheetSheet(id);
	}
	@Override
	public int deleteCheatSheet(int id) {
		return csrepo.deleteCheetSheet(id);
	}
	
	@Override
	public List<CheatSheet> getCheatSheetUser(){
		return csrepo.getCheatSheetUser();
	}
	@Override
	public CheatSheet getCheatSheetById(int id) {
		return csrepo.getCheatSheetById(id);
	}
	@Override
	public List<CheatSheet> getCheatListById(int userId){
		return csrepo.getCheatListById(userId);
	}
	@Override
	public List<CheatSheet> getCheatByCategory(int cheatId){
		return csrepo.getCheatByCategory(cheatId);
	}
	@Override
	public int updateCheatSheetById(CheatSheet dto) {
		return csrepo.updateCheatSheetById(dto);
	}
	@Override
	public int addComment(Comment dto) {
		return csrepo.addComment(dto);
	}
}
