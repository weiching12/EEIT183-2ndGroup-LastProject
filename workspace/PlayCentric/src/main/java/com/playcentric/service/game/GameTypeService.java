package com.playcentric.service.game;

import java.util.List;

import com.playcentric.model.game.primary.GameTypeLib;
import com.playcentric.model.game.primary.GameTypeLibRepository;

public class GameTypeService {
	
	private GameTypeLibRepository rep;
	
	//獲取所有遊戲分類
	public List<GameTypeLib> findAll() {
		return rep.findAll();
	}
	
	public GameTypeLib findById(Integer id) {
		return rep.findById(id).get();
	}
		
}
