package spring.service;

import java.util.List;

import spring.entity.CheatSheet;
import spring.entity.Comment;

public interface CheatSheetService {

	public int addCheatSheet(CheatSheet dto,int userId);
	public List<CheatSheet> getAllCheatSheet();
	
	public int activeCheatSheet(int id);
	public int deleteCheatSheet(int id);
	public List<CheatSheet> getCheatSheetUser();
	public CheatSheet getCheatSheetById(int id);
	public List<CheatSheet> getCheatListById(int userId);
	public List<CheatSheet> getCheatByCategory(int cheatId);
	public int updateCheatSheetById(CheatSheet dto);
	public int addComment(Comment dto);
}
