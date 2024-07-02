package com.playcentric.service.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playcentric.model.game.primary.Game;
import com.playcentric.model.game.primary.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gRepo;
	
	//新增遊戲
	public Game insert(Game game) {
		return gRepo.save(game); 
	}
	
	//id找遊戲
	public Game findById(Integer id) {
		return gRepo.findById(id).get();
	}
	
	//取全部遊戲
	public List<Game> findAll() {
		return gRepo.findAll();
	}
	
	//更新以id找到的遊戲
	public Game update(Integer id,Game game) {
	}
	
}
